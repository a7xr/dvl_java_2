package StudyJava;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class StuffsTest {

}

class NothingTest01 implements Runnable{

	String txt = "this an object from nothingtest01";
	int wait=500,
			times =5;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < this.times; i++) {
			try {
				System.out.println(this.txt);
				Thread.sleep(this.wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

class FrameThis extends JFrame implements KeyListener{
	
	ThreadTest01 threadTest01 = new ThreadTest01();
	
	public FrameThis() {
		setTitle("title");
		setBounds(50, 50, 500, 300);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		threadTest01.start();

		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}

class ThreadTest01 extends Thread{
	
	int strInt = 0;
	int wait = 500, times = 1;
	
	@Override
	public void run() {
//		str = ;
		
		try {
			for (int i = 0; i < times; i++) {
				System.out.println(this.strInt + "");
				strInt++;
					sleep(this.wait);
				
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			for (int i = 0; i < times; i++) {
				System.out.println(this.strInt + "");
				strInt--;
					sleep(this.wait);
				
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void soping(String txt) {
		System.out.println(txt);
	}	

}