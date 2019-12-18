package gof.simplefactory;

public class VideoFactory {
	public Video getVideoByType(String type) {
		if ("java".equals(type)) {
			return new JavaVideo();
		} else if ("python".equals(type)) {
			return new PythonVideo();
		} else {
			return null;
		}
	}
	
	// 工厂不用变化
	public <T> Video getVideoByClass(Class<T> cls) {
		try {
			return (Video) Class.forName(cls.getName()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
