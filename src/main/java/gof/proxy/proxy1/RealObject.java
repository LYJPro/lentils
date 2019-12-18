package gof.proxy.proxy1;

public class RealObject implements Interface {

	@Override
	public void doSomething() {
		System.err.println("doSomething");
	}

	@Override
	public void somethingElse(String arg) {
		System.err.println("somethineElse " + arg);
	}

}
