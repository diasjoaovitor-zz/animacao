import java.awt.Color;
import java.awt.Graphics2D;

public class Square extends Shape {
	public void design(Graphics2D g2d) {
		g2d.setColor(getColor());
		g2d.fillRect(getX() - getArea(), getY() - getArea(), getArea()*2, getArea()*2);
		
		//Borda
		g2d.setColor(Color.black);
		g2d.drawRect(getX() - getArea(), getY() - getArea(), getArea()*2, getArea()*2);
	}
}
