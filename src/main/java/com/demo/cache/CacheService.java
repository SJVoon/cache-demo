package com.demo.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CacheService {

    @Cacheable("CACHE_TIME")
    public String getCachedTime(){
        return new Date().toString();
    }
}
