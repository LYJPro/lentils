package gof.proxy.proxy2;

import java.lang.reflect.Proxy;

import gof.proxy.proxy1.Interface;
import gof.proxy.proxy1.RealObject;

public class SimpleDynamicProxyDemo {
	public static void consumer(Interface inf) {
		inf.doSomething();
		inf.somethingElse("boon");
	}
	
	public static void main(String[] args) {
		Interface realInf = new RealObject();
		consumer(realInf);
		
		System.err.println();
		
		Interface proxyInf = (Interface)Proxy.newProxyInstance(
				Interface.class.getClassLoader(), 
				new Class[] {Interface.class}, 
				new DynamicProxyHandler(realInf));
		consumer(proxyInf);
	}
}
