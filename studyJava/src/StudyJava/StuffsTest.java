package StudyJava;

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

class ThreadTest01 extends Thread{
	
	String str = "";
	int wait = 500, times = 5;
	
	@Override
	public void run() {
		str = "this is a test";
		try {
			for (int i = 0; i < times; i++) {
				System.out.println(this.str);
				
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