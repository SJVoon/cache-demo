# v1 - Using spring boot starter cache

1. add spring-boot-starter-cache dependency to pom
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```
2. add `@EnableCaching` annotation at main method class/configuration class
3. add `@Cacheable("YOUR_CACHE_NAME")` at method you would like to cache the result

