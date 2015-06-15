package StudyGraphic;

import java.util.ArrayList;

public class FrameKotranaDpObserver01 {

	public FrameKotranaDpObserver01() {
		ObjectObservedKotrana objObserved = new ObjectObservedKotrana();
		OctalObserverKotrana octalObserverKotrana = new OctalObserverKotrana();
		BinaryObserverKotrana binaryObserverKotrana = new BinaryObserverKotrana();
		
		objObserved.observers.add(octalObserverKotrana);
		objObserved.observers.add(binaryObserverKotrana);
		
		octalObserverKotrana.objectObservedKotrana = objObserved;
		binaryObserverKotrana.objectObservedKotrana = objObserved;
		
		objObserved.setA(7);
		objObserved.setA(2);
	}
	
}

class ObjectObservedKotrana {
	int a;
	ArrayList<ObserverKotrana> observers = new ArrayList<>();
	
	public void setA(int _a){
		this.a = _a;
		notifyAllObservers();
	}
	
	private void notifyAllObservers() {

		for (ObserverKotrana observerKotrana : observers) {
			observerKotrana.notified();
		}
		
	}
}

abstract class ObserverKotrana {
	ObjectObservedKotrana objectObservedKotrana;
	
	public abstract void notified();
}

class OctalObserverKotrana extends ObserverKotrana{

	@Override
	public void notified() {
		System.out.println("octal observer notified: "+Integer.toOctalString(objectObservedKotrana.a));
	}
	
}

class BinaryObserverKotrana extends ObserverKotrana{

	@Override
	public void notified() {
		System.out.println("binary observer notified: "+Integer.toBinaryString(objectObservedKotrana.a));
	}
	
}