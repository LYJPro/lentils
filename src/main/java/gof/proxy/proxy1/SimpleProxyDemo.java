package gof.proxy.proxy1;

public class SimpleProxyDemo {
	public static void main(String[] args) {
		Interface inf = new SimpleProxy();
		consumer(inf);
		
		Interface realInf = new RealObject();
		consumer(realInf);
	}
	
	public static void consumer(Interface inf) {
		inf.doSomething();
		inf.somethingElse("boon");
	}
}
