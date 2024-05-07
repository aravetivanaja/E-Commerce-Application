package com.retail.ecommerce.Cache;

import java.time.Duration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheStore<T> {
	
	
	private Cache<String, T> cache;

	public CacheStore(int maxAge) {
		this.cache = CacheBuilder.newBuilder()
				.expireAfterWrite(Duration.ofMinutes(maxAge))
				.concurrencyLevel(Runtime.getRuntime().availableProcessors())
				.build();
	}
	//to add data to cached memory
	
	public void add(String key,T value)
	{
		cache.put(key, value);
	}
	//get the cached data
	
	public T get(String key)
	{
		return cache.getIfPresent(key);
	}
	
	//to remove the cached data
	
	public void remove(String key)
	{
		cache.invalidate(key);
	}

}
