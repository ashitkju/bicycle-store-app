# bicyclestore
This app is a bundle of zookeeper, kafka and the spring boot app running in containers.

What application offers?
1. Set of APIs to perform CRUD as asked in problem statement.
2. Swagger UI support to test and use the APIs.
3. H2 DB used for persistance.
4. Basic username/password based Authentication support.
5. Basic Spring boot Caching support over find, update and delete APIs.
6. Aspect oriented approach to send traces of API usage to Kafka topic.
7. Dockerized App and simple one command approach to deploy the whole setup.
8. Modular & layered structure of the project.
9. Basic test to depict the intent.
---
So, to run the setup, please navigate to the root folder and
run the **docker-compose.yml** file with below command
> docker-compose up --build

Once all the three containers are up. Open any browser and navigate to below url
> http://localhost:8080/swagger-ui/index.html

You would be prompted to enter credentials.
Please use below credentials
> username: bicycle
> 
> password: bicycle

The Swagger ui will show all supported apis available.

Please use the format of the payload suggested by swagger and try out the apis.

Thank you for assessing me.
Have a nice day!