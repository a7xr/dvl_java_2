package StudyGraphic;

public class FrameKotranaDpProxy01 {
	
	public FrameKotranaDpProxy01() {
		Charging01 charging01 = new ProxyImage02("/path/fileImg");
		charging01.display();
		charging01.display();
	}

}

abstract class Charging01 {
	
	String path;
	
	public abstract void display();
}

class RealImage02 extends Charging01{

	RealImage02(String _path) {
		this.path = _path;
	}
	
	@Override
	public void display() {
		
		System.out.println("    loading img");
		
	}
	
}

class ProxyImage02 extends Charging01 {

	RealImage02 realImage;
	
	public ProxyImage02(String _path) {
		this.path = _path;
	}
	
	@Override
	public void display() {
		
		if (realImage == null ) {
			System.out.println("from realImg");
			realImage = new RealImage02(this.path);
			realImage.display();
		}
		else {
			System.out.println("from proxy image");
			realImage.display();
		}
		
	}
	
}