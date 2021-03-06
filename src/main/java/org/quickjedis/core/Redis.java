package org.quickjedis.core;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.quickjedis.impl.RedisHash;
import org.quickjedis.impl.RedisKey;
import org.quickjedis.impl.RedisList;
import org.quickjedis.impl.RedisSet;
import org.quickjedis.impl.RedisSortedSet;
import org.quickjedis.impl.RedisString;

import redis.clients.jedis.Tuple;

public abstract class Redis extends CacheBase
		implements RedisKey, RedisString, RedisHash, RedisList, RedisSet, RedisSortedSet {
	public Redis(final String name) {
		super(name);
	}

	@Override
	public abstract <T> List<T> GetList(final String key, final Class<T> className);

	@Override
	public abstract <T> T Get(final String key, final Class<T> className);

	@Override
	public abstract String GetString(final String key);

	@Override
	public abstract byte[] GetBytes(final String key);

	@Override
	public abstract <T> boolean Set(final String key, final List<T> ListObject);

	@Override
	public abstract <T> boolean Set(final String key, final List<T> ListObject, final int cacheMinutes);

	@Override
	public abstract <T> boolean Set(final String key, final T targetObject);

	@Override
	public abstract <T> boolean Set(final String key, final T targetObject, final int cacheMinutes);

	@Override
	public abstract boolean Set(final String key, final String text);

	@Override
	public abstract boolean Set(final String key, final String text, final int cacheMinutes);

	@Override
	public abstract boolean Set(final String key, final byte[] bytes);

	@Override
	public abstract boolean Set(final String key, final byte[] bytes, final int cacheMinutes);

	@Override
	public abstract boolean Expire(final String key, final int seconds);

	@Override
	public abstract long TTL(final String key);

	@Override
	public abstract long Del(final String key);

	@Override
	public abstract <T> T Hget(final String key, final String field, final Class<T> className);

	@Override
	public abstract String HgetString(final String key, final String field);

	@Override
	public abstract byte[] HgetBytes(final String key, final String field);

	@Override
	public abstract boolean Hset(final String key, final String field, final byte[] value);

	@Override
	public abstract boolean Hset(final String key, final String field, final String value);

	@Override
	public abstract long HincrBy(final String key, final String field, final long increment);

	@Override
	public abstract Set<Tuple> ZrangeWithScores(final String key, final long start, final long stop);

	@Override
	public abstract <T> Set<T> Zrange(final String key, final long start, final long stop, final Class<T> className);

	@Override
	public abstract Set<byte[]> Zrange(final String key, final long start, final long stop);

	@Override
	public abstract <T> HashMap<Double, T> ZrangeWithScores(final String key, final long start, final long stop,
			final Class<T> className);

	abstract boolean Exists(final String key);

	// T Get<T>(final String key, Func<T> initItemFunc, final int cacheMinutes =
	// 0);

	abstract long LLen(final String queueId);

	// long Push<T>(final String queueId, final T value);

	abstract long Push(final String queueId, final String value);

	// T Pop<T>(final String queueId);

	abstract String Pop(final String queueId);

	abstract String LIndex(final String listId, final int listIndex);

	abstract boolean Remove(final String key);

	abstract long SCARD(final String setid);

	abstract long SADD(final String setid, final String member);

	abstract long SREM(final String setid, final String member);

	abstract String SPOP(final String setid);

	abstract List<String> SMEMBERS(final String setid);

	abstract long SISMEMBER(final String setid, final String member);

	abstract void SMOVE(final String setid, final String toSetid, final String member);

	abstract long ZCARD(final String setid);

	abstract long ZADD(final String setid, final String member, final int score);

	abstract long ZREM(final String setid, final String member);

	abstract double ZSCORE(final String setid, final String member);

	abstract double ZINCRBY(final String setid, final String member, final int increment);

	abstract List<Tuple> ZRANGE(final String setid, final int start, final int stop, boolean withScore);

	abstract List<Tuple> ZREVRANGE(final String setid, final int start, final int stop, boolean withScore);

	abstract List<Tuple> ZRANGEBYSCORE(final String setid, double min, double max, final int skip, final int take,
			boolean withScore);

	abstract long ZREMRANGEBYRANK(final String setid, final int min, final int max);

	abstract long HIncrby(final String hashId, final String field, final int incrementBy);

	abstract long HSet(final String hashId, final String field, final String value);

	abstract void HMSet(final String hashId, final List<String> keyList, final List<String> valueList);

	abstract List<String> HMGet(final String hashId, final List<String> keyList);

	abstract String HGet(final String hashId, final String field);

	abstract List<String> HGetValues(final String hashId, final List<String> keys);

	abstract Dictionary<String, String> HGetAll(final String hashId);

	abstract long HDel(final String hashId, final String field);

	abstract long HLen(final String hashId);

	abstract long Incr(final String key);

	abstract long Decr(final String key);

	abstract long Increment(final String key, final int amount);

	abstract long Decrement(final String key, final int amount);

	abstract long IncrementValueInHash(final String hashId, final String key, final int incrementBy);

	abstract Dictionary<String, String> Info();

	abstract boolean Ping();

	abstract List<String> SearchKeys(final String pattern);
}
