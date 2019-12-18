package game.retrosnaker;

public enum Direction {
	UP(0), DOWN(1), LEFT(2), RIGHT(3);

	private int code;

	private Direction(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
