package com.redislabs.riot.redis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redislabs.lettusearch.search.AddOptions;

import io.lettuce.core.ScoredValue;
import io.lettuce.core.ScriptOutputType;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.StreamEntryID;

public class JedisClusterCommands implements RedisCommands<JedisCluster> {

	@Override
	public Object del(JedisCluster redis, String... keys) {
		return redis.del(keys);
	}

	@Override
	public Object geoadd(JedisCluster redis, String key, double longitude, double latitude, String member) {
		return redis.geoadd(key, longitude, latitude, member);
	}

	@Override
	public Object hmset(JedisCluster redis, String key, Map<String, String> map) {
		return redis.hmset(key, map);
	}

	@Override
	public Object zadd(JedisCluster redis, String key, double score, String member) {
		return redis.zadd(key, score, member);
	}

	@Override
	public Object zadd(JedisCluster redis, String key, List<ScoredValue<String>> scoredValues) {
		Map<String, Double> scoreMembers = new HashMap<>();
		scoredValues.forEach(v -> scoreMembers.put(v.getValue(), v.getScore()));
		return redis.zadd(key, scoreMembers);
	}

	@Override
	public Object xadd(JedisCluster redis, String key, Map<String, String> map) {
		return redis.xadd(key, null, map);
	}

	@Override
	public Object xadd(JedisCluster redis, String key, String id, Map<String, String> map) {
		return redis.xadd(key, new StreamEntryID(id), map);
	}

	@Override
	public Object xadd(JedisCluster redis, String key, Map<String, String> map, long maxlen,
			boolean approximateTrimming) {
		return redis.xadd(key, null, map, maxlen, approximateTrimming);
	}

	@Override
	public Object xadd(JedisCluster redis, String key, String id, Map<String, String> map, long maxlen,
			boolean approximateTrimming) {
		return redis.xadd(key, new StreamEntryID(id), map, maxlen, approximateTrimming);
	}

	@Override
	public Object xadd(JedisCluster redis, String key, List<String> ids, List<Map<String, String>> maps) {
		Object last = null;
		for (int index = 0; index < ids.size(); index++) {
			last = xadd(redis, key, ids.get(index), maps.get(index));
		}
		return last;
	}

	@Override
	public Object set(JedisCluster redis, String key, String value) {
		return redis.set(key, value);
	}

	@Override
	public Object sadd(JedisCluster redis, String key, String... members) {
		return redis.sadd(key, members);
	}

	@Override
	public Object rpush(JedisCluster redis, String key, String... members) {
		return redis.rpush(key, members);
	}

	@Override
	public Object lpush(JedisCluster redis, String key, String... members) {
		return redis.lpush(key, members);
	}

	@Override
	public Object expire(JedisCluster redis, String key, long timeout) {
		return redis.expire(key, Math.toIntExact(timeout));
	}

	@Override
	public Object evalsha(JedisCluster redis, String sha, ScriptOutputType type, String[] keys, String[] args) {
		return redis.evalsha(sha, Arrays.asList(keys), Arrays.asList(args));
	}

	@Override
	public Object restore(JedisCluster redis, String key, byte[] value, long ttl, boolean replace) {
		return redis.restore(key, Math.toIntExact(ttl), value);
	}

	@Override
	public Object ftadd(JedisCluster redis, String index, String docId, double score, Map<String, String> map,
			AddOptions options) {
		throw new UnsupportedOperationException("Jedis Cluster not supported with RediSearch");
	}

	@Override
	public Object ftadd(JedisCluster redis, String index, String docId, double score, Map<String, String> map,
			AddOptions options, String payload) {
		throw new UnsupportedOperationException("Jedis Cluster not supported with RediSearch");
	}

	@Override
	public Object sugadd(JedisCluster redis, String index, String string, double score, boolean increment) {
		throw new UnsupportedOperationException("Jedis Cluster not supported with RediSearch");
	}

	@Override
	public Object sugadd(JedisCluster redis, String index, String string, double score, boolean increment,
			String payload) {
		throw new UnsupportedOperationException("Jedis Cluster not supported with RediSearch");
	}

}
