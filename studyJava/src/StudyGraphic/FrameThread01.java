package StudyGraphic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class PanelTy extends JPanel {
	
	PointTy pointTy = new PointTy(50, 50, Color.BLUE);
	int[] sizePt = new int[] {
		10, 10
	};
	
	public PanelTy() {
		
	}
	
	public PointTy getPointTy() {
		return pointTy;
	}
	public void setPointTy(PointTy pointTy) {
		this.pointTy = pointTy;
	}
	public int[] getSizePt() {
		return sizePt;
	}
	public void setSizePt(int[] sizePt) {
		this.sizePt = sizePt;
	}

	// Override
 	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(pointTy.getColor());
		g.fillOval(pointTy.getX(), 
				pointTy.getY(), 
				getSizePt()[0], 
				getSizePt()[1]);
	}
	
}

class PointTy {
	int x, y;
	Color color;
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
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public PointTy(int _x, int _y, Color _color) {
		setX(_x);
		setY(_y);
		setColor(_color);
	}
	
}

class ImplThread implements Runnable {

	PointTy pointTy;
	int wait = 1000;
	int[] sizePt = new int[] {
			10, 10
	};
	int keyPressed;
	
	// class ImplThread
	@Override
	public void run() {
		System.out.println("coucou");
		if(keyPressed == KeyEvent.VK_RIGHT) {
			pointTy.setX(pointTy.getX() + sizePt[0]);
			System.out.println("implThread");
		}
	}
	
}

public class FrameThread01 extends JFrame implements KeyListener{

	PanelTy panelTy = new PanelTy();
	ImplThread implThread = new ImplThread();
	Thread thread = new Thread (implThread);
	
	
	boolean go = false, 
			goU, 
			goD, 
			goR, 
			goL;

	public static void main(String[] args) {
		new FrameThread01();
	}
	
	// constructor
	public FrameThread01() {
		//  Auto-generated constructor stub
		setTitle("title");
		setBounds(500, 10, 500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		implThread.pointTy = panelTy.getPointTy();
//		FrameThread01 frameThread01 = new FrameThread01();
//		thread = new Thread(frameThread01);
		
		add(panelTy);
		addKeyListener(this);
		setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//  Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//  Auto-generated method stub
		if(!go) {
			go = true;
			thread.start();
			System.out.println("voloo");
			return;
		}
		if(go) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				goR = true;
				goL = false;
				goD = false;
				goU = false;
				implThread.keyPressed = KeyEvent.VK_RIGHT;
				System.out.println("faaroo");
				
			}
		}
		
		panelTy.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//  Auto-generated method stub
		
	}

	

}
