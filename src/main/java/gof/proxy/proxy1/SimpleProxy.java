package gof.proxy.proxy1;

public class SimpleProxy implements Interface {
	private Interface proxied;
	
	public SimpleProxy() {
		this.proxied = new RealObject();
	}

	@Override
	public void doSomething() {
		System.err.println("before proxied doSomething");
		
		proxied.doSomething();
		
		System.err.println("after proxied doSomething");
	}

	@Override
	public void somethingElse(String arg) {
		System.err.println("before proxied somethingElse ");
		
		proxied.somethingElse(arg);
		
		System.err.println("after proxied somethingElse");
	}

}
