package com.example.demo;

interface MyRepository {
	String getItem(String key);
	void putItem(String key, String value);
}
