package gof.proxy.redisPoolProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.commands.AdvancedJedisCommands;
import redis.clients.jedis.commands.BasicCommands;
import redis.clients.jedis.commands.ClusterCommands;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.commands.ModuleCommands;
import redis.clients.jedis.commands.MultiKeyCommands;
import redis.clients.jedis.commands.ScriptingCommands;
import redis.clients.jedis.commands.SentinelCommands;

public class RedisProxyHandler implements InvocationHandler {
	public static final int TIMEOUT = 60000;
	private final static Logger LOGGER = LoggerFactory.getLogger(RedisProxyHandler.class);
	
	private JedisPool proxiedJedisPool;
	private Jedis jedis;
	
	public RedisProxyHandler(int db) {
		try {
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setMaxTotal(50);
			this.proxiedJedisPool = new JedisPool(poolConfig, "192.168.91.145", 6379, 60000, "111111", db);
			this.jedis = proxiedJedisPool.getResource();
		} catch (Exception exception) {
			LOGGER.error("Redis有异常：" + ExceptionUtils.getStackTrace(exception));
		}
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(jedis, args);
		return result;
	}
	
	public JedisCommands getJedisCommands() {
		return (JedisCommands) Proxy.newProxyInstance(JedisCommands.class.getClassLoader(),
				new Class[] { JedisCommands.class }, this);
	}
	    
	public MultiKeyCommands getMultiKeyCommands() {
		return (MultiKeyCommands) Proxy.newProxyInstance(MultiKeyCommands.class.getClassLoader(),
				new Class[] { MultiKeyCommands.class }, this);
	}

	public AdvancedJedisCommands getAdvancedJedisCommands() {
		return (AdvancedJedisCommands) Proxy.newProxyInstance(AdvancedJedisCommands.class.getClassLoader(),
				new Class[] { AdvancedJedisCommands.class }, this);
	}

	public ScriptingCommands getScriptingCommands() {
		return (ScriptingCommands) Proxy.newProxyInstance(ScriptingCommands.class.getClassLoader(),
				new Class[] { ScriptingCommands.class }, this);
	}

	public BasicCommands getBasicCommands() {
		return (BasicCommands) Proxy.newProxyInstance(BasicCommands.class.getClassLoader(),
				new Class[] { BasicCommands.class }, this);
	}

	public ClusterCommands getClusterCommands() {
		return (ClusterCommands) Proxy.newProxyInstance(ClusterCommands.class.getClassLoader(),
				new Class[] { ClusterCommands.class }, this);
	}

	public SentinelCommands getSentinelCommands() {
		return (SentinelCommands) Proxy.newProxyInstance(SentinelCommands.class.getClassLoader(),
				new Class[] { SentinelCommands.class }, this);
	}

	public ModuleCommands getModuleCommands() {
		return (ModuleCommands) Proxy.newProxyInstance(ModuleCommands.class.getClassLoader(),
				new Class[] { ModuleCommands.class }, this);
	}

}
