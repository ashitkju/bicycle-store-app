package com.demo.bicyclestore.aspect;

import com.demo.bicyclestore.client.KafkaClient;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Component
@Aspect
@Slf4j
public class ApiTracerAspect {
    @Autowired
    private KafkaClient kafkaClient;
    @Before("execution(* com.demo.bicyclestore.controller.impl.BicycleApiImpl.*(..))")
    public void traceMethodCalls(JoinPoint jp) throws ExecutionException, InterruptedException {
        String message = jp.getSignature().toShortString() + Arrays.toString(jp.getArgs());
        log.info(message);
        kafkaClient.sendMessage(message);
    }
}