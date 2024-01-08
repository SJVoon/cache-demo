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

---
*To have more control on cache mechanism, consider using cache library*

# v2 - Using Caffeine cache

1. add dependency
```
<dependency>
  <groupId>com.github.ben-manes.caffeine</groupId>
  <artifactId>caffeine</artifactId>
</dependency>
```
(if you do not need to have any customization on cache setup, spring boot will default use the external library 
that you have added the dependency, no extra code on configuration needed)
2. add CacheConfig class (if you need customization)
```
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .initialCapacity(100)
                        .maximumSize(100)
                        .expireAfterWrite(5, TimeUnit.MINUTES)
                        .expireAfterAccess(5,TimeUnit.MINUTES)
                        .recordStats()
        );
        return cacheManager;
    }

}
```
3. autowire CacheManager and print its class name to verify which cache is used  
code :
`System.out.println(cacheManager.getClass());`  
log : 
`class org.springframework.cache.caffeine.CaffeineCacheManager`