# SpringBoot Redis with Jedis

Basic application that has CRUD with Redis and a JSON Restful API.

Has a manifest.yml to deploy in VMware Tanzu Application Service PCF.

Specific Jedis config for Redis Enterprise cluster failover scenario.

By default connect on Redis localhost:6379, else can discover Redis Enterprise cluster thru the PCF VCAP_SERVICES env and service binding.

## Run locally


```
./mvnw package spring-boot:run

or 
java -jar target/rsapidemo-1.0.0.jar
```


## Redis in Docker

docker run -it -p 6379:6379 --name redis1 redis

./mvnw package spring-boot:run





