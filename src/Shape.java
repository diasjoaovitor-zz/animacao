import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape {
	private int x, y, area, speedX, speedY;
	private Color c;
	
	public Shape() {
		x 	   = 0;
		y 	   = 0;
		area   = 0;
		speedX = 0;
		speedY = 0;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setArea(int area) {
		this.area = area;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getArea() {
		return area;
	}
	
	public Color getColor() {
		return c;
	}
	
	public int getSpeedX() {
		return speedX;
	}
	
	public int getSpeedY() {
		return speedY;
	}
	
	public void animation() {
		setX(getX() + getSpeedX());
		setY(getY() + getSpeedY());
	}
	
	abstract public void design(Graphics2D g2d);
}
