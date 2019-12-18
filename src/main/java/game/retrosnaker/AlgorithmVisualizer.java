package game.retrosnaker;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class AlgorithmVisualizer {
	private Snake snake; // 蛇的身体
	private Food food; // 食物
	private AlgorithmFrame frame; // 视图
	
	private int sceneWidth;
	private int sceneHeight;
	
	/**
	 * @param sceneWidth 画布宽度 
	 * @param sceneHeight 画布高度
	 */
	public AlgorithmVisualizer(int sceneWidth, int sceneHeight) {
		this.sceneWidth = sceneWidth;
		this.sceneHeight = sceneHeight;
		
		// 初始化蛇
		snake = initSnake();
		
		// 初始化食物
		food = createFood();

		// 初始化视图
		EventQueue.invokeLater(() -> {
			frame = new AlgorithmFrame("Retro Snaker", sceneWidth, sceneHeight);
			frame.addKeyListener(new AlgoKeyListener());
			new Thread(() -> {
				while (true) {
					run();
				}
			}).start();
		});
	}
	
	private class AlgoKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent keyEvent) {
			Direction direction = snake.getDir();
			int keyCode = keyEvent.getKeyCode();
			
			if (direction == Direction.DOWN || direction == Direction.UP) {
				if (keyCode == KeyEvent.VK_LEFT) {
					snake.setDir(Direction.LEFT);
				}
				if (keyCode == KeyEvent.VK_RIGHT) {
					snake.setDir(Direction.RIGHT);
				}
			} else {
				if (keyCode == KeyEvent.VK_UP) {
					snake.setDir(Direction.UP);
				}
				if (keyCode == KeyEvent.VK_DOWN) {
					snake.setDir(Direction.DOWN);
				}
			}
			
		}
	}
	
	private void move() {
		LinkedList<Food> body = snake.getBody();
		
		snake.getDir();
		Food head = body.getFirst();
		if (snake.getDir() == Direction.DOWN) {
			body.addFirst(new Food(head.getX(), head.getY() + 10));
		} else if (snake.getDir() == Direction.UP) {
			body.addFirst(new Food(head.getX(), head.getY() - 10));
		} else if (snake.getDir() == Direction.LEFT) {
			body.addFirst(new Food(head.getX() - 10, head.getY()));
		} else if (snake.getDir() == Direction.RIGHT) {
			body.addFirst(new Food(head.getX() + 10, head.getY()));
		}
		
		if (!eat(food, head)) {
			body.removeLast();
		} else {
			food = createFood();
		}
		snake.setBody(body);
		AlgorithmVisHelper.pause(100);
	}
	
	/**
	 * 是否已经吃到食物
	 * @param food
	 * @param head
	 * @return
	 */
	private boolean eat(Food food, Food head) {
		return (food.getX() * 10) == head.getX() &&
			(food.getY() * 10) == head.getY();
	}

	/**
	 * 动画
	 */
	private void run() {
		move();
		frame.render(snake, food);
	}
	
	/**
	 * 生成食物
	 */
	private Food createFood() {
		int width = sceneWidth/10;
		int height = sceneHeight/10;
		
		Random random = new Random();
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		if (contain(x, y)) {
			return createFood();
		}
		return new Food(x, y);
	}
	
	/**
	 * 生成的食物不能在蛇身体上
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean contain(int x, int y) {
		for (Food b : snake.getBody()) {
			if (b.getX() == (x * 10) && b.getY() == (y * 10)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 重置蛇身体
	 */
	private Snake initSnake() {
		Snake snake = new Snake();
		LinkedList<Food> foods = new LinkedList<>();
		for (int i = 2; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				foods.add(new Food(i * 10, j * 10));
			}
		}
		snake.setBody(foods);
		snake.setDir(Direction.DOWN);
		return snake;
	}

	public static void main(String[] args) {
		new AlgorithmVisualizer(200, 200);
	}
}
