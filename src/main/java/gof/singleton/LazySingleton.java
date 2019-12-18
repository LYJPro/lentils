package gof.singleton;

public class LazySingleton {
	// volatile 防止重排序
	private volatile static LazySingleton lazySingleton;
	
	private LazySingleton() {}
	
	public synchronized static LazySingleton getInstance() {
		if (lazySingleton == null) {
			synchronized (LazySingleton.class) {
				if (lazySingleton == null) {
					lazySingleton = new LazySingleton();
				}
			}
		}
		return lazySingleton;
	}
}
