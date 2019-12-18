package gof.proxy.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
	private Object proxied;
	
	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.err.println("**** proxy : " + proxy.getClass() + ", method : " +  method + ", args : " + args);
		
		if (args != null) {
			for (Object arg : args) {
				System.err.println(" " + arg);
			}
		}
		System.err.println(method.invoke(proxied, args));
		return null;
	}

}
