package gof.factorymethod;

public class Main {
	public static void main(String[] args) {
		VideoFactory videoFactory = new JavaVideoFactory();
		Video video = videoFactory.getVideo();
		video.produce();
	}
}
