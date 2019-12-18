package gof.chain;

public abstract class Handler {
	private Handler nextHandler;

	public abstract void handleRequest();

	public Handler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}
}
