package game.retrosnaker;

import java.util.LinkedList;

public class Snake {
	private LinkedList<Food> body;
	private Direction dir;

	public LinkedList<Food> getBody() {
		return body;
	}

	public void setBody(LinkedList<Food> body) {
		this.body = body;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

}
