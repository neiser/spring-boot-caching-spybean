package com.example.demo;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
class MyRepositoryImpl implements MyRepository {

	private Map<String, String> storage = new HashMap<>();

	@Override
	@Cacheable("items")
	public String getItem(String key) {
		return storage.get(key);
	}

	@Override
	@CacheEvict(cacheNames = "items", key = "#key")
	public void putItem(String key, String value) {
		storage.put(key, value);
	}
}
