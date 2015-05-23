package StudyGraphic;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.naming.SizeLimitExceededException;
import javax.swing.*;

public class FrameBibilavaTest01 extends JFrame implements KeyListener{
	PanelTesting panelTesting = new PanelTesting();
	ArrayList<PointTest> points = new ArrayList<>();
	int [] sizePt = {10, 10};
	
	//constructor
	public FrameBibilavaTest01() {
		// TODO Auto-generated constructor stub
		setTitle("title");
		setBounds(50, 50, 500, 250);
//		setLayout(new FlowLayout());

		PointTest pointHead = new PointTest(80, 50);
		pointHead.color = Color.RED;
		points.add(pointHead);
		points.add(new PointTest(70, 50));
		points.add(new PointTest(60, 50));
		points.add(new PointTest(50, 50));
		
		addKeyListener(this);
		
		panelTesting.sizePt = this.sizePt;
		
		panelTesting.points = this.points;
		
		getContentPane().add(panelTesting);
		setVisible(true);
	}
	
	public static void main01(String[] args) {
		new FrameBibilavaTest01();
//		PointTest pPrev = new PointTest(6, 7);
//		PointTest pNext = new PointTest(9, 2);
//		
//		prevPoint(pPrev, pNext);
//		System.out.println(pPrev.x);
//		System.out.println(pPrev.y);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int a = e.getKeyCode();
		
		if(a == KeyEvent.VK_RIGHT) {
				PointTest tmp = points.get(0);
			this.points.get(0).x += sizePt[0];
			this.points.get(0).y += 0;
//			panelTesting.points = this.points;
			
			panelTesting.points = this.points;
			panelTesting.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void prevPoint(PointTest ptPrev, PointTest ptNext){
		ptNext.x = ptPrev.x;
		ptNext.y = ptPrev.y;
	}
	
}

class PanelTesting extends JPanel {
	ArrayList<PointTest> points = new ArrayList<>();
	int [] sizePt = {10, 10};
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(points.get(0).color);
		g.fillOval(points.get(0).x, points.get(0).y, sizePt[0], sizePt[1]);
		
		
		for (int i = 1; i < points.size(); i++) {
			g.setColor(points.get(i).color);
			g.fillOval(points.get(i).x, points.get(i).y, sizePt[0],	sizePt[1]);
		}
	}
	
}

class PointTest {
	int x = 0, 
			y = 0;
	Color color = Color.black;

	public PointTest(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
}