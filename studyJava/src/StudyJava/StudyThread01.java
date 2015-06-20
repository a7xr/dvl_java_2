package StudyJava;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StudyThread01 extends JFrame implements KeyListener {

	boolean threadGo = false;
	ExtThread thread = new ExtThread();
	ExtPanel panel = new ExtPanel();

	public StudyThread01() {

		setTitle("title");
		setBounds(50, 50, 500, 250);
//		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		thread.setPanel(panel);
		addKeyListener(this);
		panel.pointThread = thread.point;
		panel.sizePt = thread.sizePt;
		add(panel);

		setVisible(true);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			if (!threadGo) {
				thread.start();
				threadGo = true;
				thread.goD = false;
				thread.goU = false;
				thread.goL = false;
				thread.goR = true;


			} else {
				System.out.println("thread efa mande nefa nipitsitra entree");
			}
		if (threadGo) {
			// ra efa mande ny thread
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				thread.goD = false;
				thread.goU = false;
				thread.goL = false;
				thread.goR = true;
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				thread.goD = false;
				thread.goU = false;
				thread.goL = true;
				thread.goR = false;
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				thread.goD = false;
				thread.goU = true;
				thread.goL = false;
				thread.goR = false;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				thread.goD = true;
				thread.goU = false;
				thread.goL = false;
				thread.goR = false;
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}

class ExtPanel extends JPanel {
	
	PointThread pointThread;
	int[] sizePt;
	
	public PointThread getPointThread() {
		return pointThread;
	}

	public void setPointThread(PointThread pointThread) {
		this.pointThread = pointThread;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		g.fillOval(pointThread.getX(), pointThread.getY(), sizePt[0], sizePt[1]);
		
	}
	
}

class ExtThread extends Thread {
	boolean goU, goD, goR, goL;

	PointThread point = new PointThread(10, 10);
	int[] sizePt = new int[] { 10, 10 };
	int wait = 500;

	JPanel panel;
	
	public void setPanel(JPanel _panel) {
		this.panel = _panel;
	}
	
	public int[] getSizePt() {
		return this.sizePt;
	}

	@Override
	public void run() {
		
		while (true) {
			try {
				if ((goR) && (!goD) && (!goU) && (!goL)) {
					point.setX(point.getX() + getSizePt()[0]);
					
					System.out.println("x: " + point.getX() + "y: " + 
							point.getY());
//					sleep(wait);
				} else if ((!goR) && (!goD) && (!goU) && (goL)) {
					point.setX(point.getX() - getSizePt()[0]);
					
					System.out.println(
							"x: " + point.getX() + "y: " + point.getY());
//					sleep(wait);
				} else if ((!goR) && (goD) && (!goU) && (!goL)) {
					point.setY(point.getY() + getSizePt()[1]);
					
					System.out.println(
							"x: " + point.getX() + "y: " + point.getY());
//					sleep(wait);
				}else if ((!goR) && (!goD) && (goU) && (!goL)) {
					point.setY(point.getY() - getSizePt()[1]);
					System.out.println(
							"x: " + point.getX() + "y: " + point.getY());
				}
				sleep(wait);
				panel.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class PointThread {
	int x, y;

	public PointThread(int x, int y) {
		// super();
		this.x = x;
		this.y = y;
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

}