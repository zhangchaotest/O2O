package com.site.redis;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import redis.clients.jedis.Jedis;

import com.site.json.JsonConvertUtils;

@Service
public class RedisService {

	private final static Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

	/** 毫秒与毫微秒的换算单位 1毫秒 = 1000000毫微秒 */
	private static final long MILLI_NANO_CONVERSION = 1000 * 1000L;

	/** 默认超时时间（毫秒） */
	private static final long DEFAULT_TIME_OUT = 1000;

	/** 锁的超时时间（秒），过期删除 */
	private static final int EXPIRE = 1 * 60;

	private static final String LOCK_KEY = "LOCK";
	private static final String LOCK_VALUE = "LOCKED";

	@Autowired(required = false)
	@Qualifier("jedisConnectionFactory")
	private JedisConnectionFactory jedisConnectionFactory;

	/**
	 * 加锁
	 * 
	 * @param timeout 超时时间
	 * @return 成功或失败标志
	 */
	public RedisLock lock(String appId, String key, long timeout) {

		if (appId == null || key == null || timeout < 0) {
			LOGGER.error("Locking params is not right:" + appId + "," + key + "," + timeout);
			throw new RuntimeException("Locking params is not right");
		}

		key = appId + "_" + key;
		LOGGER.info(key + "start to redis lock");
		long nano = System.nanoTime();
		timeout *= MILLI_NANO_CONVERSION;
		try {
			while ((System.nanoTime() - nano) < timeout) {
				LOGGER.info(key + "in redis lock");
				if (this.setnx(key, String.valueOf(System.nanoTime())) == 1) {
					RedisLock redisLock = new RedisLock();
					redisLock.setKey(key);
					String lockKey = appId + "_" + LOCK_KEY + "_" + UUID.randomUUID();
					redisLock.setLockKey(lockKey);
					this.setex(lockKey, EXPIRE, LOCK_VALUE);
					this.expire(key, EXPIRE);
					return redisLock;
				}
				String s = get(key);
				if (s == null) {
					continue;
				}
				long setnxNano = Long.parseLong(s);
				if ((System.nanoTime() - setnxNano) / MILLI_NANO_CONVERSION / 1000 >= EXPIRE) {
					this.del(key);
				}
				// 短暂休眠，避免出现活锁
				Thread.sleep(300);
			}
		} catch (Exception e) {
			LOGGER.error("Locking error:" + appId + "," + key + "," + timeout, e);
			throw new RuntimeException("Locking error", e);
		}
		return null;
	}

	public void setex(final String key, final int seconds, final String value) {
		Jedis jedis = getJedis();
		try {
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			LOGGER.error("redisService setex error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
	}
	
	public void setex(final byte[] key,final byte[] value,final int seconds) {
		Jedis jedis = getJedis();
		try {
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			LOGGER.error("redisService setex error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
	}

	public void setex(final String key, final int seconds, final Object value) {
	    Jedis jedis = getJedis();
	    try {
	      jedis.setex(key, seconds, JsonConvertUtils.toJson(value));
	    } catch (Exception e) {
	      LOGGER.error("redisService setex error2", e);
	    } finally {
	      jedis.close();
	    }
	}
	
	public <T> T get(String key, Class<T> class1) {
	    Jedis jedis = getJedis();
	    try {
	      return JsonConvertUtils.toObject(jedis.get(key), class1);
	    } catch (Exception e) {
	      LOGGER.error("redisService get error", e);
	    } finally {
	      jedis.close();
	    }
	    return null;
	}
	
	public Object getObj(byte[] key) {
	    Jedis jedis = getJedis();
	    try {
	    	  byte[] bytes = jedis.get(key);
	    	  return (bytes == null || bytes.length == 0) ? null : SerializationUtils.deserialize(bytes);
	    } catch (Exception e) {
	      LOGGER.error("redisService get error", e);
	      return null;
	    } finally {
	      jedis.close();
	    }
	}
	
	public byte[] getBytes(String key) {
		Jedis jedis = getJedis();
		try {
	      return jedis.get(key.getBytes());
	    } catch (Exception e) {
	      LOGGER.error("redisService get error", e);
	    } finally {
	      jedis.close();
	    }
	    return null;
	}
	
	public void expire(final String key, final int seconds) {
		Jedis jedis = getJedis();
		try {
			jedis.expire(key, seconds);
		} catch (Exception e) {
			LOGGER.error("redisService expire error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
	}

	public RedisLock lock(String appId, String key) {
		return lock(appId, key, DEFAULT_TIME_OUT);
	}

	/**
	 * 解锁
	 */
	public void unlock(RedisLock redisLock) {
		try {
			if (redisLock != null && redisLock.getKey() != null && redisLock.getLockKey() != null) {
				this.del(redisLock.getKey());
				this.del(redisLock.getLockKey());
			}
		} catch (Exception e) {
			LOGGER.error("redisService unlock error!", e);
		}
	}

	/**
	 * 获取redis value (String)
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		Jedis jedis = getJedis();
		try {

			return jedis.get(key);
		} catch (Exception e) {
			LOGGER.error("redisService get error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
		return null;
	}

	public Long del(String key) {
		Jedis jedis = getJedis();
		try {

			return jedis.del(key);
		} catch (Exception e) {
			LOGGER.error("redisService del error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
		return 0l;
	}
	
	public Long hset(String key,String field,String value) {
		Jedis jedis = getJedis();
		try {
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			LOGGER.error("redisService hset error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
		return 0L;
	}

	public Map<String, String> hgetAll(String key) {
		Jedis jedis = getJedis();
		try {
			return jedis.hgetAll(key);
		} catch (Exception e) {
			LOGGER.error("redisService hget error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
		return null;
	}
	
	public boolean exists(String key){
		Jedis jedis = getJedis();
		try {
			return jedis.exists(key);
		} catch (Exception e) {
			LOGGER.error("redisService exists error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
		return false;
	}
	
	public boolean exists(byte[] key){
		Jedis jedis = getJedis();
		try {
			return jedis.exists(key);
		} catch (Exception e) {
			LOGGER.error("redisService exists error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
		return false;
	}

	public Long setnx(String key, String value) {
		Jedis jedis = getJedis();
		try {
			return jedis.setnx(key, value);
		} catch (Exception e) {
			LOGGER.error("redisService setnx error", e);
		} finally {
			if (jedis != null) jedis.close();
		}
		return 0l;
	}

	/**
	 * 获取一个jedis 客户端
	 * 
	 * @return
	 */
	private Jedis getJedis() {
		return this.jedisConnectionFactory.getConnection().getNativeConnection();
	}
}
