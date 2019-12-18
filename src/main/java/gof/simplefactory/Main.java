package gof.simplefactory;

public class Main {
	public static void main(String[] args) {
		// 不用工厂
		Video video = new JavaVideo();
		video.produce();
		
		// 工厂不使用反射
		VideoFactory videoFactory = new VideoFactory();
		Video video2 = videoFactory.getVideoByType("java");
		video2.produce();
		
		// 工厂使用反射
		VideoFactory videoFactory2 = new VideoFactory();
		Video video3 = videoFactory2.getVideoByClass(JavaVideo.class);
		video3.produce();
	}
}
