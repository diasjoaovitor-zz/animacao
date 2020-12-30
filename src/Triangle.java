import java.awt.Color;
import java.awt.Graphics2D;

public class Triangle extends Shape {
	public void design(Graphics2D g2d) {
		int[] xPoints = {getX() - getArea(), getX(), getX() + getArea()};
		int[] yPoints = {getY() + getArea(), getY() - getArea(), getY() + getArea()};
		
		g2d.setColor(getColor());
		g2d.fillPolygon(xPoints, yPoints, 3);
		
		//Borda
		g2d.setColor(Color.black);
		g2d.drawPolygon(xPoints, yPoints, 3);
	}
}
