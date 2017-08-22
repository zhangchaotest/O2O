package com.site.redis;

public class RedisLock {

	private String key;
	
	private String lockKey;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLockKey() {
		return lockKey;
	}
	public void setLockKey(String lockKey) {
		this.lockKey = lockKey;
	}
}
