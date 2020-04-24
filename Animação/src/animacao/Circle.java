package animacao;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Shape {
	public int sumRay, distCenter;
	
	public Circle() {
		sumRay     = 0;
		distCenter = 0;
	}
	
	public boolean collision(Circle c) {
		sumRay = getArea() + c.getArea();
		distCenter = (int)Math.round(Math.sqrt(Math.pow(c.getX() - getX(), 2) + Math.pow(c.getY() - getY(), 2)));
		
		return (distCenter <= sumRay);
	}
	
	public void design(Graphics2D g2d) {
		g2d.setColor(getColor());
		g2d.fillOval(getX() - getArea(), getY() - getArea(), getArea()*2, getArea()*2);
		
		//Borda
		g2d.setColor(Color.black);
		g2d.drawOval(getX() - getArea(), getY() - getArea(), getArea()*2, getArea()*2);
		
	}
}
