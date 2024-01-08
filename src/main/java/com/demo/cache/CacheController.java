package com.demo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @Autowired
    CacheService cacheService;

    @GetMapping("/cache")
    public String getCachedDate(){
        return cacheService.getCachedTime();
    }

}
