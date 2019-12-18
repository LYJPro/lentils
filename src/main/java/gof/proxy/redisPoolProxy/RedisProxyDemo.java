package gof.proxy.redisPoolProxy;

import redis.clients.jedis.commands.JedisCommands;

public class RedisProxyDemo extends Thread {
	@Override
	public void run() {
		RedisProxyHandler handler = DoRedis.getInstance();

		JedisCommands jedisCommands = handler.getJedisCommands();
		jedisCommands.set("", "");
	}
}
