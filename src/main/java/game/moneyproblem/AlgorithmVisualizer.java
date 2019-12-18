package game.moneyproblem;

import java.awt.EventQueue;
import java.util.Arrays;

public class AlgorithmVisualizer {
	private final static int DELAY = 10;
	
	private int[] money; // 数据
	private AlgorithmFrame frame; // 视图

	public AlgorithmVisualizer(int sceneWidth, int sceneHeight) {

		// 初始化数据
		money = new int[100];
		for (int i = 0; i < money.length; i++) {
			money[i] = 100;
		}

		// 初始化视图
		EventQueue.invokeLater(() -> {
			frame = new AlgorithmFrame("分钱模拟", sceneWidth, sceneHeight);
			new Thread(() -> {
				run();
			}).start();
		});
	}

	// 动画逻辑
	private void run() {
		while (true) {
			Arrays.sort(money);
			
			frame.render(money);
			AlgorithmVisHelper.pause(DELAY);
			
			for (int k = 0; k < 50; k++) {
				for (int i = 0; i < money.length; i++) {
					int j = (int) (Math.random() * money.length);
					money[i] -= 1;
					money[j] += 1;
				}
			}
		}
	}

	public static void main(String[] args) {
		int sceneWidth = 800;
		int sceneHeight = 1000;

		new AlgorithmVisualizer(sceneWidth, sceneHeight);
	}
}
