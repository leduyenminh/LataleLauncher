package com.springLataleLauncher.demo.cacheConfig;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public class CustomKeyGenerator implements KeyGenerator {

		//Re-implement the caching key generating default strategy 
		@NonNull
    public Object generate(@NonNull Object target, @NonNull Method method, @NonNull Object... params) {
        return target.getClass().getSimpleName() + "_" + method.getName() + "_"
                + StringUtils.arrayToDelimitedString(params, "_");
    }
}