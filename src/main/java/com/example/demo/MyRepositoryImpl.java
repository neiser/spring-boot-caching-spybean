package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MyRepositoryImpl implements MyRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyRepositoryImpl.class);

	private Map<String, String> storage = new HashMap<>();

	@Override
	@Cacheable(cacheNames = "items", key = "#key.id")
	public String getItem(String key) {
		LOGGER.info("Storage get {}", key);
		return storage.get(key);
	}

	@Override
	@CacheEvict(cacheNames = "items", key = "#key.id")
	public void putItem(String key, String value) {
		storage.put(key, value);
	}
}
