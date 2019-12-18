package gof.proxy.redisPoolProxy;

public class DoRedis {
	private final static int DATABASE = 1;
	private static RedisProxyHandler instance = null;

	public synchronized static RedisProxyHandler getInstance() {
		if (instance == null) {
			synchronized (RedisProxyHandler.class) {
				instance = new RedisProxyHandler(DATABASE);
			}
		}
		return instance;
	}
}
