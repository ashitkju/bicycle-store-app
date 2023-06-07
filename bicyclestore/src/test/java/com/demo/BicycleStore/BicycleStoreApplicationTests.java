package com.demo.BicycleStore;

import com.demo.bicyclestore.controller.impl.BicycleApiImpl;
import com.demo.bicyclestore.dto.BicycleDto;
import com.demo.bicyclestore.service.BicycleCrudService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BicycleApiImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class BicycleStoreApplicationTests {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private BicycleCrudService crudService;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void save1Test() throws Exception {
		var dto = getDummyDto();
		Mockito.doReturn(BicycleDto.toEntity(dto)).when(crudService).save(Mockito.any(BicycleDto.class));
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/bicycle/save")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
		Assertions.assertEquals("10", mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void findAllTest() throws Exception {
		Mockito.doReturn(List.of(new BicycleDto(), new BicycleDto(), new BicycleDto()))
				.when(crudService).findAllBicyclesPaginated(0, 5);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/bicycle/all")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		List<BicycleDto> actual = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<>() {});
		Assertions.assertEquals(3, actual.size());
	}
	private BicycleDto getDummyDto() {
		var dto = new BicycleDto();
		dto.setId(10);
		dto.setBrand("BSA");
		dto.setPrice(50.99);
		dto.setConfigId(0);
		dto.setFrameSize(20);
		dto.setWheelSize(29);
		dto.setColor("black");
		return dto;
	}
}