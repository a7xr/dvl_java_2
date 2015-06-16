package StudyGraphic;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.naming.SizeLimitExceededException;
import javax.swing.*;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import Tools.Basics;

public class FrameBibilavaTest01 extends JFrame implements KeyListener, Runnable{
	PanelTesting panelTesting = new PanelTesting();
	ArrayList<PointTest> points = new ArrayList<>();
	int [] sizePt = {10, 10};
	PointTest food;
	ArrayList<PointTest> obstacles = new ArrayList<>();
	int speed = 1000;
	
	boolean horizontal, vertical;
	
	public boolean headTouchedObstacles() {
		boolean res = false;
		
		for(PointTest _pt : this.obstacles) {
			if ((points.get(0).getX() == _pt.getX()) && (points.get(0).getY() == _pt.getY())) {
				res = true;
			}
		}
		
		return res;
	}
	
	public PointTest generateRandomFood(ArrayList<PointTest> _pts,
			ArrayList<PointTest> _obstacles) {
		Random random = new Random();
		int min = 0;
		int max = 210;
		int xRand = (random.nextInt((max - min) + 10) / sizePt[0]) * sizePt[0] + min;
		int yRand = (random.nextInt((max - min) + 10) / sizePt[1]) * sizePt[1] + min;
		
		
		PointTest res = generateRandomFood();
		
		ArrayList<PointTest> clone = (ArrayList)_pts.clone();
		clone.addAll(_obstacles);
		PointTest.printArrayPointTest(_pts);
		
		for(PointTest _pt : clone) {
			if((_pt.getX() == res.getX()) && (_pt.getY() == res.getY())) {
				res = generateRandomFood(_pts, _obstacles);
				System.out.println("nisi ntovi");
			}
		}
		
		return res;
	}
	
	public PointTest generateRandomFood() {
		Random random = new Random();
		int min = 0;
		int max = 210;
		
		int xRand = (random.nextInt((max - min) + 10) / sizePt[0]) * sizePt[0] + min;
		int yRand = (random.nextInt((max - min) + 10) / sizePt[1]) * sizePt[1] + min;
		
		PointTest res = new PointTest(xRand, yRand);
		res.color = Color.blue;
		return res;
	}
	
	public boolean headTouchedBody(ArrayList<PointTest>_pts, PointTest head) {
		boolean res = false;
		
		for(int i = 1; i < _pts.size(); i++){
			if(head.getX() == _pts.get(i).getX() && head.getY() == _pts.get(i).getY())
				return true;
		}
		
		return res;
	}
	
	public void touchedLimitRight() {
		points.get(0).setX(0);
	}
	
	public void touchedLimitLeft() {
		points.get(0).setX(this.getWidth());
	}
	
	public void touchedLimitUp() {
		points.get(0).setY(this.getHeight());
	}
	
	public void touchedLimitDown() {
		points.get(0).setY(0);
	}
	
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
		
		
		obstacles = generateObstacleGuided(new PointTest(
				(Basics.generateIntRand(0, 250) / sizePt[0]) * sizePt[0], 
				(Basics.generateIntRand(0, 100) / sizePt[1]) * sizePt[1]),  
				sizePt, true, true);
		
		obstacles = generateObstacleGuided(new PointTest(
				(Basics.generateIntRand(0, 250) / sizePt[0]) * sizePt[0], 
				(Basics.generateIntRand(0, 100) / sizePt[1]) * sizePt[1]),  
				sizePt, true, true);
		
		panelTesting.sizePt = this.sizePt;
		this.food = generateRandomFood();
		panelTesting.food = this.food;
		panelTesting.points = this.points;
		panelTesting.obstacles = this.obstacles;
		
		getContentPane().add(panelTesting);
		setVisible(true);
	}

	public static void moveRight(ArrayList <PointTest> pts, 
			int [] ptSize) {
	
		PointTest tmp = new PointTest();
		tmp.setX(pts.get(0).getX());
		tmp.setY(pts.get(0).getY());
		pts.get(0).setX(tmp.getX() + ptSize[0] );
		for(int i = 1; i < pts.size(); i++) {
			swap(pts.get(i), tmp);
		}
	}
	
	public static void swap(PointTest sw0, PointTest sw1) {
		PointTest tmp = new PointTest();
		tmp.setX(sw0.getX());
		tmp.setY(sw0.getY());
		
//		sw0 = sw1;
		sw0.setX(sw1.getX());
		sw0.setY(sw1.getY());
//		sw0 = PointTest.clone(sw1);
		
//		sw1 = tmp;
		sw1.setX(tmp.getX());
		sw1.setY(tmp.getY());
//		sw1 = PointTest.clone(tmp);
	}
	
	
	
	public static void moveUp(ArrayList <PointTest> pts,
			int[] ptSize) {
		PointTest tmp = new PointTest();
		tmp.setX(pts.get(0).getX());
		tmp.setY(pts.get(0).getY());
		
		pts.get(0).setY(tmp.getY() - ptSize[1] );
		for(int i = 1; i < pts.size(); i++) {
			swap(pts.get(i), tmp);
		}
	}
	
	public static void moveDown(ArrayList <PointTest> pts,
			int[] ptSize) {
		PointTest tmp = new PointTest();
		tmp.setX(pts.get(0).getX());
		tmp.setY(pts.get(0).getY());
		
		pts.get(0).setY(tmp.getY() + ptSize[1] );
		for(int i = 1; i < pts.size(); i++) {
			swap(pts.get(i), tmp);
		}
	}
	
	public static void moveLeft(ArrayList <PointTest> pts, 
			int [] ptSize) {
	
		PointTest tmp = new PointTest();
		tmp.setX(pts.get(0).getX());
		tmp.setY(pts.get(0).getY());
		pts.get(0).setX(tmp.getX() - ptSize[0] );
		for(int i = 1; i < pts.size(); i++) {
			swap(pts.get(i), tmp);
		}
	}

	public void move ( ArrayList <PointTest> pts, 
			int [] ptSize, int keyPressed) {
		
		if(headTouchedBody(this.points, this.points.get(0))) {
			System.out.println("head touched the body");
		}
		
		if(headTouchedObstacles()) {
			System.out.println("touched obstacles");
		}
		
		if (keyPressed == KeyEvent.VK_RIGHT) {
			moveRight(pts, ptSize);
		}else if(keyPressed == KeyEvent.VK_UP) {
			moveUp(pts, ptSize);
		}else if(keyPressed == KeyEvent.VK_LEFT)
			moveLeft(pts, ptSize);
		else if (keyPressed == KeyEvent.VK_DOWN)
			moveDown(pts, ptSize);
		}
	
	public static void main07(String[] args) {
		PointTest p00 = new PointTest(40,  10);
		PointTest p01 = new PointTest(30,  10);
		PointTest p02 = new PointTest(30,  20);
		PointTest p03 = new PointTest(20,  20);
		PointTest p04 = new PointTest(10,  20);
		
		ArrayList<PointTest> bibil = new ArrayList<>();
		bibil.add(p00);
		bibil.add(p01);
		bibil.add(p02);
		bibil.add(p03);
		bibil.add(p04);
		
//		move(bibil, new int[] {10, 10}, KeyEvent.VK_DOWN);
		
		PointTest.printArrayPointTest(bibil);
	}
	
	/**
	 * move up
	 * @param args
	 */
	public static void main06(String[] args) {
		PointTest p00 = new PointTest(40,  10);
		PointTest p01 = new PointTest(30,  10);
		PointTest p02 = new PointTest(30,  20);
		PointTest p03 = new PointTest(20,  20);
		PointTest p04 = new PointTest(10,  20);
		
		ArrayList<PointTest> bibil = new ArrayList<>();
		bibil.add(p00);
		bibil.add(p01);
		bibil.add(p02);
		bibil.add(p03);
		bibil.add(p04);
		
		moveUp(bibil, new int[] {10, 10});
		
		PointTest.printArrayPointTest(bibil);
	}
	
	/*
	 * test move right
	 */
	public static void main05(String[] args) {
		PointTest p00 = new PointTest(10,  30);
		PointTest p01 = new PointTest(10,  20);
		PointTest p02 = new PointTest(10,  10);
		PointTest p03 = new PointTest(20,  10);
		PointTest p04 = new PointTest(30,  10);
		
		ArrayList<PointTest> bibil = new ArrayList<>();
		bibil.add(p00);
		bibil.add(p01);
		bibil.add(p02);
		bibil.add(p03);
		bibil.add(p04);
		
		moveRight(bibil, new int[] {10, 10});
		
		PointTest.printArrayPointTest(bibil);
	}
	
	/*
	 * for moving to the right
	 */
	public static void main04(String[] args) {
		PointTest p00 = new PointTest(80,  50);
		PointTest p01 = new PointTest(70,  50);
		PointTest p02 = new PointTest(60,  50);
		PointTest p03 = new PointTest(50,  50);
		PointTest p04 = new PointTest(40,  50);
		
		ArrayList<PointTest> bibil = new ArrayList<>();
		bibil.add(p00);
		bibil.add(p01);
		bibil.add(p02);
		bibil.add(p03);
		bibil.add(p04);
		
		moveRight(bibil, new int[] {10, 10});
		
		PointTest.printArrayPointTest(bibil);
	}
	
	/**
	 * swapping 2pts
	 * @param args
	 */
	public static void main03(String[] args) {
		PointTest p00 = new PointTest(1, 2);
		PointTest p01 = new PointTest(3, 4);
		
		System.out.println(p00.getX() + ", "+ p00.getY());
		System.out.println(p01.getX() + ", "+ p01.getY());
		
		swap(p00, p01);
		System.out.println(p00.getX() + ", "+ p00.getY());
		System.out.println(p01.getX() + ", "+ p01.getY());
		
	}
	
	/*
	 * move right by 2pts
	 */
	public static void main02(String[] args) {
		ArrayList<PointTest> pts = new ArrayList<>();
		pts.add(new PointTest(50, 40));
		pts.add(new PointTest(40, 40));
		
		for (int i = 0; i < 2; i++) {
			System.out.println(i + "x: "+pts.get(i).x + ", y: " + pts.get(i).y);
		}
		
		moveRight(pts, new int[] {10, 10});
		
		for (int i = 0; i < 2; i++) {
			System.out.println(i + "x: "+pts.get(i).x + ", y: " + pts.get(i).y);
		}
	}
	
	// testing depl(pts, int keyPressed, ptSize)
	
	public static ArrayList<PointTest> generateObstacleGuided(PointTest _ptRandom,
			int[] ptSize,
			boolean v00, boolean v01) {
		int xRand = Basics.generateIntRand(0, 7);
//		System.out.println(xRand);
		int yRand = Basics.generateIntRand(0, 7);
		ArrayList<PointTest> res = new ArrayList<PointTest>();
		System.out.println("xRand: "+xRand);
		System.out.println("yRand: "+yRand);
		
		res.add(_ptRandom);
		if(v00) {
			for(int i = 1; i <= xRand; i++) {
				res.add(new PointTest(
							_ptRandom.getX() + (ptSize[0] * i), 
							_ptRandom.getY()
						)
				);
			}
		}else{
			for(int i = 1; i <= xRand; i++) {
				res.add(new PointTest(
							_ptRandom.getX() - (ptSize[0] * i), 
							_ptRandom.getY()
						)
				);
			}
		}

		if(v01) {
			for(int e = 1; e <= yRand; e++) {
				res.add(new PointTest(
							_ptRandom.getX() , 
							_ptRandom.getY()+ (ptSize[0] * e)
						)
				);
			}
		}
		else{
			for(int e = 1; e <= yRand; e++) {
				res.add(new PointTest(
							_ptRandom.getX(), 
							_ptRandom.getY() - (ptSize[0] * e)
						)
				);
			}
		}		
		
		return res;
	}
	
	public static void main09(String[] args) {
		ArrayList<PointTest> obstacle = generateObstacleGuided(new PointTest(150, 150), new int[]{10, 10}, 
				false, false);
		PointTest.printArrayPointTest(obstacle);
	}
	
	// main_graphic
	public static void main08(String[] args) {
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
		if(points.get(0).getX() == this.getWidth()){
			touchedLimitRight();
		}else if(points.get(0).getX() == 0){
			touchedLimitLeft();
		}else if( points.get(0).getY() == this.getHeight() ) {
			touchedLimitDown();
		}else if( points.get(0).getY() == 0 ) {
			touchedLimitUp();
		}
		
		move(this.points, new int[] {10, 10},  a);
//		System.out.println("food: " + food.getX() + ", " + food.getY());
//		System.out.println("[0]: " + points.get(0).getX() + ", " + points.get(0).getY());
		
		if(PointTest.is2PtsEquals(food, points.get(0))){
			this.food = generateRandomFood(this.points, this.obstacles);
			this.points.add(new PointTest(this.points.get(this.points.size() - 1).getX(), 
					this.points.get(this.points.size() - 1).getY()));
			panelTesting.food = this.food;
			panelTesting.points = this.points;
		}
//		panelTesting.points = this.points;
		panelTesting.repaint();
		
//		if(a == KeyEvent.VK_RIGHT) {
//				PointTest tmp = points.get(0);
//			this.points.get(0).x += sizePt[0];
//			this.points.get(0).y += 0;
////			panelTesting.points = this.points;
//			
//			panelTesting.points = this.points;
//			panelTesting.repaint();
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		points.add(new PointTest(0, 0));
		while(true)
			try {
				System.out.println(points.get(0).getX());
				Thread.sleep(speed);
				points.get(0).setX(points.get(0).getX() + 1);
			}catch(InterruptedException e) {
				
			}
	}
	
	

	public static void main(String[] args) {
		FrameBibilavaTest01 frameBibilavaTest01 = 
				new FrameBibilavaTest01(State.draft);
		Thread thread = new Thread(frameBibilavaTest01);
		thread.start();
	}
	
	public FrameBibilavaTest01(State s) {
		
	}
	
}

enum State {
	draft
}

class PanelTesting extends JPanel {
	ArrayList<PointTest> points = new ArrayList<>();
	ArrayList<PointTest> obstacles = new ArrayList<>();
	int [] sizePt = {10, 10};
	PointTest food;
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(points.get(0).color);
		g.fillOval(points.get(0).x, points.get(0).y, sizePt[0], sizePt[1]);
		
		g.setColor(food.color);
		g.fillOval(food.getX(), food.getY(), sizePt[0], sizePt[1]);
		
		for (int i = 1; i < points.size(); i++) {
			g.setColor(points.get(i).color);
			g.fillOval(points.get(i).x, points.get(i).y, sizePt[0],	sizePt[1]);
		}
		
		for(int e = 0; e < obstacles.size(); e++) {
			g.setColor(obstacles.get(e).color);
			g.fillOval(obstacles.get(e).x, obstacles.get(e).y, sizePt[0], sizePt[1]);
		}
		
	}
	
}

class PointTest {
	int x = 0; 
	int y = 0;
	
	public static void printArrayPointTest(ArrayList<PointTest> pts) {
		for(int i = 0; i < pts.size(); i++) {
			System.out.println(i + ": x: "+pts.get(i).getX()
					+", y: "+pts.get(i).getY());
		}
	}
	
	public void setX(int _x) {
		this.x = _x;
	}
	
	public void setY(int _y) {
		this.y = _y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public PointTest() {
		
	}
	
	public static boolean is2PtsEquals(PointTest p00, PointTest p01) {
		if ((p00.getX() == p01.getX()) && (p00.getY() == p01.getY()))
			return true;
		else
			return false;
	}
	
	/**
	 * mbola tsy mande
	 * @param toClone
	 * @return
	 */
	public static PointTest clone(PointTest toClone) {
		PointTest res = new PointTest();
		res.setX(toClone.getX());
		res.setY(toClone.getY());
		return res;
	}
	
	Color color = Color.black;

	public PointTest(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public static void printArrPt( ArrayList<PointTest>alPtTest) {
		for(int i = 0; i < alPtTest.size(); i++){
			System.out.println("["+i+"]: " + alPtTest.get(i).x + ", "+ alPtTest.get(i).y);
		}
	}

	public static void switchPt(PointTest pt1, PointTest pt0) {
//		System.out.println("0: "+pt0.x+ ", "+pt0.y);
//		System.out.println("1: "+pt1.x+ ", "+pt1.y);
		PointTest tmp;
		tmp = pt0;
		pt0 = pt1;
		pt1 = tmp;
		
//		System.out.println("after substitute");
//		System.out.println("0: "+pt0.x+ ", "+pt0.y);
//		System.out.println("1: "+pt1.x+ ", "+pt1.y);
	}
	
	
}