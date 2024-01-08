package com.demo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @Autowired
    CacheService cacheService;

    @Autowired
    CacheManager cacheManager;

    @GetMapping("/cache")
    public String getCachedDate(){
        System.out.println(cacheManager.getClass());
        return cacheService.getCachedTime();
    }

}
