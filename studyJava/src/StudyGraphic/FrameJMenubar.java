package StudyGraphic;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import StudyDP.Strategy;

public class FrameJMenubar extends JFrame implements ActionListener, WindowListener, DocumentListener{
	JMenuBar jMenuBar;
	
	// last_attr
	JMenu jMenuKotranaDpProxy = new JMenu("proxy");
	JMenuItem jMenuItemKotranaDpProxy01 = new JMenuItem("01");
	
	JMenu jMenuKotranaDp = new JMenu("dp");
	JMenu jMenuKotranaDpObserver = new JMenu("observer");
	JMenuItem jMenuItemKotranaDpObserver01 = new JMenuItem("01");
	
	JMenu jMenuKotrana;
	JMenu jMenuKotranaSnake;
	JMenuItem jMenuItemKotranaSnake01;
	
	JMenuItem jMenuItemDpProxy01;
	
	JMenu jMenuDpProxy ;
	
	JMenuItem jMenuItemJTableBasic02;
	
	
	JMenu jMenuDpObserver;
	JMenuItem jMenuItemDpObserver01;
	
	JMenu jMenuDpProto;
	JMenuItem jMenuItemDpProto01;
	
	JMenu jMenuJTable;
	JMenu jMenuJTableBasic;
	JMenuItem jMenuItemJTableBasic01;
	
	JMenuItem jMenuQuit;
	
	JMenu jMenuSql;
	JMenuItem jMenuItemNotSelect;
	JMenuItem jMenuItemSelect;
	
	JMenu jMenuDp;
	JMenu jMenuDpStrategy;
	JMenuItem jMenuDpStrategyOp;
	
	JMenu jMenuTest;
	JMenu jMenuPotato;
	JMenuItem jMenuItemPotatoLike;
	JMenuItem jMenuItemPotatoDislike;
	Dialog dialog;
	
	class Dialog extends JDialog implements ActionListener{
		
		JButton jButtonClose = new JButton("close");
		
		Dialog(){
			setModal(true);
			setTitle("this is a dialog");
			setBounds(40, 40, 200, 150);
			
			setLayout(new FlowLayout());
			
			getContentPane().add(jButtonClose);
			
			jButtonClose.addActionListener(this);
			
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == jButtonClose)
				setVisible(false);
		}
	}
	
	class DialogOperation extends JDialog implements ActionListener{
		
		JButton jButtonOperation = new JButton("operation");
		JButton jButtonClose = new JButton("close");
		
		JTextField jTextFieldOperand1 = new JTextField(4);
		JTextField jTextFieldOperand2 = new JTextField(4);
		
		JTextField jTextFieldSign = new JTextField(2);
		JComboBox<String> jComboBoxSign = new JComboBox<>();
		
		JTextField jTextFieldRes = new JTextField(4);
		
		public DialogOperation() {
//			super();
			setModal(true);
			setLocationRelativeTo(null);
			setBounds(75, 75, 400, 200);
			setLayout(new FlowLayout());
			
			String[] allOperation = {
				"+",
				"-",
				"*",
				"/"
			};
			jComboBoxSign.addItem("+");
			jComboBoxSign.addItem("-");
			jComboBoxSign.addItem("*");
			jComboBoxSign.addItem("/");
			
			add(jTextFieldOperand1);
//			add(jTextFieldSign);
			add(jComboBoxSign);
			add(jTextFieldOperand2);
			add(new JLabel("result: "));
			add(jTextFieldRes);
			
			add(jButtonOperation);
			add(jButtonClose);
			
			jButtonClose.addActionListener(this);
			jButtonOperation.addActionListener(this);
			
			setVisible(true);
		}



		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == jButtonOperation){
				
				Context context = new Context();
				
				String opSign = (String)jComboBoxSign.getSelectedItem();
//				System.out.println("opsign: " + opSign);
				int a = Integer.valueOf(jTextFieldOperand1.getText());
				int b = Integer.valueOf(jTextFieldOperand2.getText());
				int res = 0;
				switch(opSign){
				case "+":
					context.strategy = new OperationAdd();
					res = context.execStrategy(a, b);
//					System.out.println("doing addition as operation");
					jTextFieldRes.setText(res+"");
					break;
				case "-":
					context.strategy = new OperationMinus();
					res = context.execStrategy(a, b);
					jTextFieldRes.setText(res+"");
//					System.out.println("doing minus as operation");
					break;
				case "*":
//					System.out.println("doing multiply as operation");
					context.strategy = new OperationMul();
					res = context.execStrategy(a, b);
					jTextFieldRes.setText(res+"");
					break;
				case "/":
//					System.out.println("doing division as operation");
					context.strategy = new OperationDiv();
					res = context.execStrategy(a, b);
					jTextFieldRes.setText(res+"");
					break;
				default:
					System.out.println("not a sign of operation");
				}
			}else if(e.getSource() == jButtonClose){
				setVisible(false);
			}
		}
		
	}
	
	// constructor
	public FrameJMenubar()  {
		setTitle("title");
		setBounds(75, 75, 400, 200);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(this);
		
		jMenuBar = new JMenuBar();
		jMenuTest = new JMenu("test");
		jMenuPotato = new JMenu("potato");
		jMenuItemPotatoLike = new JMenuItem("like");
		jMenuItemPotatoDislike = new JMenuItem("dislike");
		
		jMenuBar.add(jMenuTest);
		
		jMenuItemPotatoLike.addActionListener(this);
		
		jMenuTest.add(jMenuPotato);
		jMenuPotato.add(jMenuItemPotatoLike);
		jMenuPotato.add(jMenuItemPotatoDislike);
		
		
		jMenuDp = new JMenu("dp");
		jMenuDpStrategy = new JMenu("strategy");
		jMenuDpStrategyOp = new JMenuItem("operation");
		
		jMenuDp.add(jMenuDpStrategy);
		jMenuDpStrategy.add(jMenuDpStrategyOp);
		
		jMenuDpStrategyOp.addActionListener(this);
		
		jMenuBar.add(jMenuDp);
		
		jMenuDpObserver = new JMenu("observer");
		jMenuDp.add(jMenuDpObserver);
		jMenuItemDpObserver01 = new JMenuItem("01");
		jMenuDpObserver.add(jMenuItemDpObserver01);
		jMenuItemDpObserver01.addActionListener(this);
		
		jMenuDpProto = new JMenu("prototype");
		jMenuItemDpProto01 = new JMenuItem("01");
		jMenuDpProto.add(jMenuItemDpProto01);
		jMenuItemDpProto01.addActionListener(this);
		
		jMenuDp.add(jMenuDpProto);
		
		jMenuSql = new JMenu("sql");
		jMenuItemSelect = new JMenuItem("select");
		jMenuItemNotSelect = new JMenuItem("not select");
		
		jMenuItemNotSelect.addActionListener(this);
		jMenuItemSelect.addActionListener(this);
		
		jMenuSql.add(jMenuItemNotSelect);
		jMenuSql.add(jMenuItemSelect);
		
		jMenuBar.add(jMenuSql);
		
		
		
		
		
		jMenuJTable = new JMenu("jtable");
		jMenuJTableBasic = new JMenu("basic");
		jMenuItemJTableBasic01 = new JMenuItem("01");
		jMenuItemJTableBasic02 = new JMenuItem("02");
		
		
		jMenuJTable.add(jMenuJTableBasic);
		jMenuJTableBasic.add(jMenuItemJTableBasic01);
		jMenuJTableBasic.add(jMenuItemJTableBasic02);

		jMenuItemJTableBasic01.addActionListener(this);
		jMenuItemJTableBasic02.addActionListener(this);
		
		jMenuBar.add(jMenuJTable);
		
		
		jMenuDpProxy = new JMenu("proxy");
		jMenuDp.add(jMenuDpProxy);
		
		jMenuItemDpProxy01 = new JMenuItem("01");
		jMenuDpProxy.add(jMenuItemDpProxy01);
		jMenuItemDpProxy01.addActionListener(this);
		
		
		jMenuKotrana = new JMenu("kotrana");
		jMenuBar.add(jMenuKotrana);
		
		jMenuKotranaSnake = new JMenu("snake");
		jMenuKotrana.add(jMenuKotranaSnake);
		
		jMenuItemKotranaSnake01 = new JMenuItem("01");
		jMenuKotranaSnake.add(jMenuItemKotranaSnake01);
		jMenuItemKotranaSnake01.addActionListener(this);
		
		jMenuKotrana.add(jMenuKotranaDp);
		jMenuKotranaDp.add(jMenuItemKotranaDpObserver01);
		jMenuItemKotranaDpObserver01.addActionListener(this);
		
		jMenuKotranaDp.add(jMenuKotranaDpProxy);
		jMenuKotranaDpProxy.add(jMenuItemKotranaDpProxy01);
		jMenuItemKotranaDpProxy01.addActionListener(this);
//		jMenuKotranaDpProxy
		// last_constructor

		jMenuQuit = new JMenuItem("quit");
		jMenuQuit.addActionListener(this);
		jMenuBar.add(jMenuQuit);
		setJMenuBar(jMenuBar);
		setVisible(true);
	}
	
	//action_listener action_performed
	@Override
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		if(e.getSource() == jMenuItemJTableBasic01){
			new DialogJTable01();
		}else if(e.getSource() == jMenuItemKotranaDpProxy01) {
			new FrameKotranaDpProxy01();
		}
		else if (e.getSource() == jMenuItemKotranaDpObserver01) {
//			System.out.println("this is a test");
			new FrameKotranaDpObserver01();
		}
		else if(e.getSource() == jMenuItemKotranaSnake01) {
			new FrameBibilavaTest01();
		}
		else if(e.getSource() == jMenuItemDpProxy01) {
			new FrameDpProxy();
		}
		else if(e.getSource() == jMenuItemJTableBasic02){
//			System.out.println("jmenuitemjtablebasic02");
			new DialogJtable02();
		}
		else if(e.getSource() == jMenuItemDpObserver01){
			new DialogDpObserver();
		}
		else if(e.getSource() == jMenuItemDpProto01){
			new DialogDpPrototype();
		}
		else if(e.getSource() == jMenuItemPotatoLike){
			dialog = new Dialog();
		}else if(e.getSource() == jMenuDpStrategyOp) {
			new DialogOperation();
		}else if(e.getSource() == jMenuItemNotSelect){
			try {
				new DialogSqlNotSelect();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource() == jMenuItemSelect){
			System.out.println("this is when u are ready for" +
					" doing the select dialog");
		}else if (e.getSource() == jMenuQuit) {
			System.out.println("clicked on quit");
			int rep=JOptionPane.showConfirmDialog(null, "voulez-vous continuer") ;
			switch(rep) {
			case 0:
				System.exit(0);
				break;
			case 1:
				break;
			case 2:
				System.out.println("canceled");
				break;
			case -1:
				System.out.println("just closed");
				break;
			}
//			System.exit(0);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

class Context {
	Strategy02 strategy;
	public int execStrategy(int a, int b){
		return strategy.doOp(a, b);
	}
}

interface Strategy02 {
	public int doOp(int a, int b);
}









class OperationMinus implements Strategy02{

	@Override
	public int doOp(int a, int b) {
		// TODO Auto-generated method stub
		return a - b;
	}
	
}

class OperationDiv implements Strategy02 {

	@Override
	public int doOp(int a, int b) {
		// TODO Auto-generated method stub
		return a / b;
	}
	
}

class OperationMul implements Strategy02 {

	@Override
	public int doOp(int a, int b) {
		// TODO Auto-generated method stub
		return a * b;
	}
	
}

class OperationAdd implements Strategy02 {
	@Override
	public int doOp(int a, int b){
		return a + b;

	}
}

class TestingThis {
	static int a;
	
}