package game.sort.select;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AlgorithmFrame extends JFrame {
	private int canvasWidth;
	private int canvasHeight;

	public AlgorithmFrame(String title, int canvasWidth, int canvasHeight) {

		super(title);

		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;

		AlgoCanvas canvas = new AlgoCanvas();
		setContentPane(canvas);

		setResizable(false);
		pack(); // 在setResizable(false)后进行pack()，防止在Windows下系统修改frame的尺寸
				// 具体参见：http://coding.imooc.com/learn/questiondetail/26420.html

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public AlgorithmFrame(String title) {
		this(title, 1024, 768);
	}

	public int getCanvasWidth() {
		return canvasWidth;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	private int[] data;
	public void render(int[] data) {
		this.data = data;
		repaint();
	}

	private class AlgoCanvas extends JPanel {

		public AlgoCanvas() {
			// 双缓存
			super(true);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;

			// 抗锯齿
			RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2d.addRenderingHints(hints);

			// 具体绘制
			AlgorithmVisHelper.setColor(g2d, AlgorithmVisHelper.Blue);
			
			if (data == null) {
				return;
			}
			
			int w = canvasWidth / data.length;
			for (int i = 0; i < data.length; i++) {
				AlgorithmVisHelper.fillRectangle(g2d, i * w + 1, 0, w - 1, data[i]);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(canvasWidth, canvasHeight);
		}
	}
}
