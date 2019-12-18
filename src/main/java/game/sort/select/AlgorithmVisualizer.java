package game.sort.select;

import java.awt.EventQueue;
import java.util.Random;

public class AlgorithmVisualizer {
	private int size;
	private int[] data; // 数据
	private AlgorithmFrame frame; // 视图

	public AlgorithmVisualizer(int size, int sceneWidth, int sceneHeight) {
		// 初始化数据
		this.size = size;
		random();
		
		// 初始化视图
		EventQueue.invokeLater(() -> {
			frame = new AlgorithmFrame("选择排序", sceneWidth, sceneHeight);
			new Thread(() -> {
				run();
			}).start();
		});
	}

	// 动画逻辑
	private void run() {
		for (int i = 0; i < data.length; i++) {
			int minIndex = data[i];
			for (int j = i; j < data.length; j++) {
				if (data[j] < minIndex) {
					minIndex = data[j];
					data[j] = data[i];
					data[i] = minIndex;
				}
			}
			frame.render(data);
			AlgorithmVisHelper.pause(40);
		}
	}
	
	private void random() {
		data = new int[size];
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < size; i++) {
			data[i] = random.nextInt(600);
		}
	}

	public static void main(String[] args) {
		int size = 500;
		int sceneWidth = 1000;
		int sceneHeight = 800;
		new AlgorithmVisualizer(size, sceneWidth, sceneHeight);
	}
}
