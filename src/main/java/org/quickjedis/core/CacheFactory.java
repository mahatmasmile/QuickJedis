package org.quickjedis.core;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.quickjedis.config.ConfigManager;

public class CacheFactory {
	private static Lock lock = new ReentrantLock();
	static {
		ConfigManager.InitCacheConfig();
	}

	public static Redis CreateRedis(String cacheName, String server, Boolean isConvertSameCache) {
		Redis redis1 = (Redis) null;
		String index = cacheName.toLowerCase();
		if (!isConvertSameCache) {
			Lock lock = CacheFactory.lock;
			Boolean lockTaken = false;
			try {
				lockTaken = lock.tryLock();
				if (ConfigManager.CurrentConfig.RedisPool.containsKey(index))
					redis1 = (Redis) ConfigManager.CurrentConfig.RedisPool.get(index);
			} finally {
				if (lockTaken)
					lock.unlock();
			}
			if (redis1 != null)
				return redis1;
		}
		Redis redis2 = (Redis) new RedisCache(index, server, "0");
		if (ConfigManager.CurrentConfig.RedisPool.containsKey(index))
			ConfigManager.CurrentConfig.RedisPool.remove(index);
		ConfigManager.CurrentConfig.RedisPool.put(index, (Redis) redis2);
		return redis2;
	}

	public static Redis CreateRedis(String cacheName, String server, int defaultDb, Boolean isConvertSameCache) {
		Redis redis1 = (Redis) null;
		String index = cacheName.toLowerCase();
		if (!isConvertSameCache) {
			Lock lock = CacheFactory.lock;
			Boolean lockTaken = false;
			try {
				lockTaken = lock.tryLock();
				if (ConfigManager.CurrentConfig.RedisPool.containsKey(index))
					redis1 = (Redis) ConfigManager.CurrentConfig.RedisPool.get(index);
			} finally {
				if (lockTaken)
					lock.unlock();
			}
			if (redis1 != null)
				return redis1;
		}
		Redis redis2 = (Redis) new RedisCache(index, server, String.valueOf(defaultDb));
		if (ConfigManager.CurrentConfig.RedisPool.containsKey(index))
			ConfigManager.CurrentConfig.RedisPool.remove(index);
		ConfigManager.CurrentConfig.RedisPool.put(index, (Redis) redis2);
		return redis2;
	}

	public static Redis GetRedis(String cacheName) {
		String key = cacheName.toLowerCase();
		Lock lock = CacheFactory.lock;
		Boolean lockTaken = false;
		try {
			lockTaken = lock.tryLock();
			if (ConfigManager.CurrentConfig.RedisPool.containsKey(key))
				return (Redis) ConfigManager.CurrentConfig.RedisPool.get(key);
		} finally {
			if (lockTaken)
				lock.unlock();
		}
		InnerLogger.Warn("GetRedis '" + key + "' 失败, 不存在该缓存对象");
		Unity.CreateException("GetRedis '" + key + "' 失败, 不存在该缓存对象");
		return (Redis) null;
	}

}
