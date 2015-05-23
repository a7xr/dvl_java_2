package StudyGraphic;

import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ti tkn mamoka ilai menu
 * 	1boutons
 * @author a7xr
 *
 */
public class FrameDpProxy extends JFrame implements ActionListener{
	
	JButton jButtonDisplay = new JButton("disp");
	Frame03 frame03;
	ImageProxy imageProxy = new ImageProxy();

	public FrameDpProxy(){
		setTitle("Proxy");
		setBounds(70, 70, 400, 200);
		setLayout(new FlowLayout());

		getContentPane().add(jButtonDisplay);
		jButtonDisplay.addActionListener(this);

		setVisible(true);
	}

	String toDisp = "";
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(frame03 == null) {
			frame03 = new Frame03();
		}else {
//			imageProxy.path = "thisis a ";
			toDisp = toDisp + "\ncoco";
			System.out.println(toDisp);
			frame03.panel02.txt = toDisp;
			frame03.panel02.repaint();
		}
	}
	
}

class Frame03 extends JFrame {
	Panel02 panel02 ;

	public Frame03() throws HeadlessException {
		panel02 = new Panel02();
//		String str=""
		panel02.txt = "coucou";
		setBounds(40, 40, 600, 300);
		getContentPane().add(panel02);
		
		
		
		setVisible(true);
	}
	
	
}

final class Panel02 extends JPanel{
	
	String txt;
	
	Panel02 () {
		
	}
	
	int y = 50;
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		FontMetrics fontMetrics = g.getFontMetrics();
		y += fontMetrics.getHeight();
		g.drawString(this.txt, 50, y);
	}
	
}

abstract class ImageDpProxy {
	String path;
	public void setPath (String _path) {
		this.path = _path;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public abstract void display();
}

class ImageProxy extends ImageDpProxy{

	RealImage realImage;
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		if(realImage == null) {
			realImage = new RealImage();
			realImage.setPath("/defaultPath");
			realImage.display();
		}else{
			realImage.display();
		}
	}
	
	
	
}

class RealImage extends ImageDpProxy {

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
	
}