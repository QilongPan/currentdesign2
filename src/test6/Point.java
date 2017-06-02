package test6;

public class Point {
	private int x ;
	private int y ;
	private int direction;        
	/*
	 *
	 * 1代表向左
	 * 4代表向下
	 * 3代表向右
	 * 2代表向上
	 */

	public Point() {
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.direction = 1;         
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void addDirection() {
		this.direction++;
	}

	public Point(int x, int y, int direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

}
