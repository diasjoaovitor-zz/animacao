import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Design extends JPanel {
	private List <Circle> listCircle = new ArrayList<>();
	private List <Square> listSquare = new ArrayList<>();
	private List <Triangle> listTriangle = new ArrayList<>();
	private Shape shape;
	private Circle current;
	private Circle next;
	private int area, r, g, b, speed, animation, a;
	private char k;
	private Color c;
	private Timer timer;
	private final int FPS = 20;
	private Boolean antialiasing;
	
	public Design() {
		shape   	 = null;
		current		 = null;
		next   		 = null;
		timer  		 = null;
		
		antialiasing = false;
		
		area  	  	 = 0;
		r     	  	 = 0;
		g     	  	 = 0;
		b     	   	 = 0;
		speed 	  	 = 0;
		animation	 = 0;
		a     	  	 = 0; //Seletor do Antialiasing 
		k     	   	 = 'c'; //Seletor de Formas
		
		setBackground(Color.white);
		setFocusable(true);
	
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);
				
				if(k == 'c') {
					shape = new Circle();
					listCircle.add((Circle) shape);
				}
					
				if(k == 's') {
					shape = new Square();
					listSquare.add((Square) shape);
				}
					
				if(k == 't') {
					shape = new Triangle();
					listTriangle.add((Triangle) shape);
				}
					
				shape.setX(me.getX());
				shape.setY(me.getY());
				shape.setArea(getRandomArea());
				shape.setColor(getRandomColor());
				shape.setSpeedX(getRandomSpeed());
				shape.setSpeedY(getRandomSpeed());
				
				repaint();
			}
		});
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				super.keyPressed(ke);
				
				if(ke.getKeyChar() == 'c') //Desenha Circulo
					k = 'c';
				if(ke.getKeyChar() == 's') //Desenha Quadrado
					k = 's';
				if(ke.getKeyChar() == 't') //Desenha Triângulo
					k = 't';
				
				//Inicia ou Pausa Animação 
				if(ke.getKeyChar() == 'p') {
					if(animation%2 == 0)
						timer.start();
					else
						timer.stop();
					
					animation++;
				}
				
				//Antialiasing
				if (ke.getKeyChar() == 'a') {
					if(a%2 == 0) {
						antialiasing = true;
						System.out.println("Antialiasing Ativado!");
						a++;
					} else {
	                	antialiasing = false;
						System.out.println("Antialiasing Desativado!");
	                    a++;
	                }
					
					repaint();
                }
			}
		});
		
		timer = new Timer(1000/FPS, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Shape shape : listCircle) {
					if(shape.getX() + shape.getArea() >= getWidth())
						shape.setSpeedX(-shape.getSpeedX());
					if(shape.getX() - shape.getArea() <= 0)
						shape.setSpeedX(-shape.getSpeedX());
					if(shape.getY() + shape.getArea() >= getHeight())
						shape.setSpeedY(-shape.getSpeedY());
					if(shape.getY() - shape.getArea() <= 0)
						shape.setSpeedY(-shape.getSpeedY());
					
					shape.animation();
				}
				
				for(Shape shape : listSquare) {
					if(shape.getX() + shape.getArea() >= getWidth())
						shape.setSpeedX(-shape.getSpeedX());
					if(shape.getX() - shape.getArea() <= 0)
						shape.setSpeedX(-shape.getSpeedX());
					if(shape.getY() + shape.getArea() >= getHeight())
						shape.setSpeedY(-shape.getSpeedY());
					if(shape.getY() - shape.getArea() <= 0)
						shape.setSpeedY(-shape.getSpeedY());
					
					shape.animation();
				}
				
				for(Shape shape : listTriangle) {
					if(shape.getX() + shape.getArea() >= getWidth())
						shape.setSpeedX(-shape.getSpeedX());
					if(shape.getX() - shape.getArea() <= 0)
						shape.setSpeedX(-shape.getSpeedX());
					if(shape.getY() + shape.getArea() >= getHeight())
						shape.setSpeedY(-shape.getSpeedY());
					if(shape.getY() - shape.getArea() <= 0)
						shape.setSpeedY(-shape.getSpeedY());
					
					shape.animation();
				}
				
				//Colisão do Circulo
				for(int i = 0; i < listCircle.size(); i++) 
					for(int j = listCircle.size() - 1; j > 0; j--) 
						if(j != i) {
							current = listCircle.get(i);
							next = listCircle.get(j);

							if(current.collision(next)) {
								if(current.getX() + current.getArea() < next.getX() + next.getArea()) {
									current.setSpeedX(-getRandomSpeed());
									next.setSpeedX(getRandomSpeed());
								} else {
									current.setSpeedX(getRandomSpeed());
									next.setSpeedX(-getRandomSpeed());
								}
								
								if(current.getY() + current.getArea() < next.getY() + next.getArea()) {
									current.setSpeedY(-getRandomSpeed());
									next.setSpeedY(getRandomSpeed());
								} else {
									current.setSpeedY(getRandomSpeed());
									next.setSpeedY(-getRandomSpeed());
								}
							}
						}
						
				repaint();
			}
		});
	}
		
	public int getRandomArea() {
		area = 25 + (int)(Math.random() * 50);

		return area;
	}
	
	public Color getRandomColor() {
		r = (int)(Math.random() * 255);
		g = (int)(Math.random() * 255);
		b = (int)(Math.random() * 255);
		c = new Color(r, g, b);
		
		return c;
	}
	
	public int getRandomSpeed() {
		speed = 5 + (int)(Math.random() * 15);
	
		return speed;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		if (antialiasing) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        else {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
	
		for(Shape shape : listCircle) 
			shape.design(g2d);
		
		for(Shape shape : listSquare) 
			shape.design(g2d);
		
		for(Shape shape : listTriangle) 
			shape.design(g2d);
	}
}

public class Window extends JFrame {
	public Window() {
		Design d = new Design();
		add(d);
		setTitle("Animação");
		setSize(720,480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Window w = new Window();
		System.out.println("Click para criar uma forma\nDefault/c: Cria circulo\ns: cria quadrado\nt: cria triangulo\na: ativa/desativa antialliasing\np: play/pause");
	}
}
