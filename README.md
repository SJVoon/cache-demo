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

# v3 Using JSR-107 standard Ehcache 3

1. add dependency
```
<dependency>
    <groupId>org.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <classifier>jakarta</classifier>
</dependency>
```
2. add this field to application.properties  
```
spring.cache.jcache.config=classpath:ehcache.xml
```
3. add ehcache.xml file at /resource following where the classpath above defined
```
<config
        xmlns='http://www.ehcache.org/v3'
        xmlns:jsr107='http://www.ehcache.org/v3/jsr107'>

    <service>
        <jsr107:defaults enable-statistics="true"/>
    </service>

    <cache alias="CACHE_TIME">
        <expiry>
            <ttl unit="seconds">300</ttl>
        </expiry>
        <resources>
            <offheap unit="MB">100</offheap>
        </resources>
    </cache>
</config>
```
(cache alias, expiry, and resources is the required field for a cache to be defined)  
*expiry is optional but recommended for ease of documentation the lifetime of the cache*

4. Remove the `CacheConfig` class that was written for Caffeine  
*caffeine will be used if CacheConfig class is defined to use Caffeine as CacheManager,  
otherwise if caffeine and JSR-107 cache dependency is exist together  
without configuration class, JSR-107 cache is prioritise*