package StudyJava;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import StudyGraphic.*;
import StudyDP.*;
import StudyDPReview.*;
import Tools.*;

public class Main {


	//main_graphic
	public static void main(String[] args) {
		FrameJMenubar frame = new FrameJMenubar();
	}
	
//	public static void main(String[] args) {
//		new Thread(new NothingTest01()).start();
//	}
	
//	public static void main(String[] args) {
//		ThreadTest01 thread = new ThreadTest01();
//		thread.start();
//	}
	
	
	
//	public static void main(String[] args) {
//		String notes [] = {"C", "D", "E", "F", "G", "A", "B"};
//		String[] octave = {"4","5","6"};
//		String name = "";
//		String note= "";
//		String bla[] = new String[100];
//		int g = 0;
//		for(int i=0; i< 3; i++){
//			for(int j=0; j<7; j++){
//				name = notes[j]+octave[i]+" ";
//				bla[g] = name;
//				System.out.println(bla[g]);
//				note += name+" ";
//				g++;
//			}
//		}
//	}
	
//	public static void main(String[] args) {
//		String [] strArr = new String[5];
//		strArr[0] = "coucou";
//		strArr[1] = "coucou";
//		
//		for (int i = 0; i < strArr.length; i++) {
//			System.out.print(strArr[i]);
//		}
//	}
	
	
//	public static void main(String[] args) {
//		ContextState context = new ContextState();
//		StartState startState = new StartState();
//		context.setState(startState);
//		startState.setContext(context);
//		startState.doAction(context);
//		String str = context.getState().toString();
//		System.out.println("str: "+str);
//	}
	

	
	
	// testd about listening in chgt in jtextfield
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setTitle("title");
//		frame.setBounds(75, 75, 400, 250);
//		frame.setLayout(new FlowLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		JTextField jtextField = new JTextField(10);
//		
//		jtextField.getDocument().addDocumentListener(new DocumentListener() {
//			
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("u removed");
//			}
//			
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("u inserted");
//			}
//			
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("u changed it");
//			}
//		});
//		
//		frame.add(jtextField);
//		
//		frame.setVisible(true);
//	}
	

	/**
	 * 
	 * here_11
	 * testing the dao_pattern
	 * 
	 */
//	public static void main(String[] args) {
//		Student01 s00 = new Student01(0, "coucou");
//		Student01 s01 = new Student01(1, "hello");
//		Student01 s02 = new Student01(2, "aon");
//		Student01 s03 = new Student01(3, "n fandean");
//		
//		StudentDaoImpl stdDaoImpl = new StudentDaoImpl();
//		
//		stdDaoImpl.addStudents(s00, s01, s02, s03);
//		stdDaoImpl.printAllStudents();
//		
//		System.out.println("**********************");
//		stdDaoImpl.updateStudent(2, "how is it going");
//		stdDaoImpl.printAllStudents();
//	}
	
	/**
	 * just a test of ArrayList
	 * @param args
	 */
//	public static void main(String[] args) {
//		ArrayList<String>arr = new ArrayList();
//		
//		arr.add(0, "coucou");
//		arr.add(1, "je suis");
//		arr.add(2, "la");
//		
//		arr.set(1, "il est");
//		
//		for(int i=0; i < 3; i++){
//			System.out.println(i+": "+arr.get(i));
//		}
//	}
	
//	public static void main(String[] args) {
//		Context context = new Context(new OpAdd());
//		int a = context.exeStrat(5,  7);
//		System.out.println("a: "+a);
//	}
	
	/**
	 * here_10
	 */
//	public static void main(String[] args) {
//		SubjectObserved02 subjectObserved02 = new SubjectObserved02();
//		
//		HexObserver02 octalObserver01 = new HexObserver02(subjectObserved02);
//		OctalObserver02 octalObserver02 = new OctalObserver02(subjectObserved02);
//		
//		subjectObserved02.attach(octalObserver01);
//		subjectObserved02.attach(octalObserver02);
//		
//		subjectObserved02.setState(9);
//		subjectObserved02.setState(269);
//		subjectObserved02.setState(37);
//		subjectObserved02.setState(4);
//		
//	}
	
	/**here_09
	 * rebiewing the prototype_pattern
	 * @param args
	 */
//	public static void main(String[] args) {
//		ShapeCache04.loadCache();
//		ShapeProtot04 s0 = ShapeCache04.getShape(0);
//		s0.speciality();
//		ShapeProtot04 s1 = ShapeCache04.getShape(3);
//		s1.speciality();
//	}
	
	
	/**
	 * testing the hashmap_objec
	 * @param args
	 */
//	public static void main(String[] args) {
//		HashMap<Integer, String>hashmap = new HashMap<>();
//		
//		hashmap.put(0, "the first");
//		hashmap.put(1, "coucou");
//		hashmap.put(2, "testing ");
//		hashmap.put(3, "javaing");
//		
//		Basics.printHashMap_IntString(hashmap);
//		
//	}
	/**
	 * here_08
	 * trying to do a frame with many tabbedpane inside
	 */
//	public static void main(String[] args) {
//		Frame01 frame = new Frame01();
//	}
	
	/*
	 * just testing the select_query in a db
	 */
//	public static void main(String[] args) {
//		try{
//			MysqlManage sql = new MysqlManage();
//			sql.selectQueryToResultSet("select * from notes_tech where id_n_t = 545");
//			String[][]strArr = sql.resultSetToStrDoubleArray();
//			Basics.printDoubleDimensionArrayOfString(strArr);
//		}catch(ClassNotFoundException e){
//			e.printStackTrace();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * to insert sth in the db
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			MysqlManage sql = new MysqlManage();
//			sql.ins_upd_del_QueryToStatement("insert into draft(draft) values (\"this is a test\")");
//			System.out.println("here");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch(ClassNotFoundException e){
//			e.printStackTrace();
//		}
//		
//	}
	
	
	
	/**
	 * 
 +->                                                                                                                                                                                
 |  read(rep)                                                                                                                                                                       
 |                                                                                                                                                                                  
 |  if (rep = y)                                                                                                                                                                    
 |      go(here)        ---+                                                                                                                                                        
 |  else if (rep = n)      |                                                                                                                                                        
 |      finish()    --+    |                                                                                                                                                        
 |  else if(rep=b)    |    |                                                                                                                                                        
 +--    go(back)      |    and_ok                                                                                                                                                   
 |  else              |    |                                                                                                                                                        
 +----  not managed   |    |                                                                                                                                                        
                      |    |                                                                                                                                                        
                   <<-+    |                                                                                                                                                        
                        <<-+     
	 */
//	public static void main(String[] args) {
//		String rep = "";
//		boolean pass = false;
//		while(!pass){
//			rep = Basics.readStr("u want to continue?: ");
//			switch(rep){
//			case "y":
//				pass = true;
//				System.out.println("u pass");
//				break;
//			case "b":
//				System.out.println("u decided to go back");
//				break;	
//			case "n":
//				System.out.println("finished");
//				System.out.println("	and not passed");
//				System.exit(0);
//				break;
//			default:
//				System.out.println("not managed");
//				System.out.println("return");
//			}
//			
//		}
//	}
	
	/**
	 * testing the readStr
	 */
//	public static void main(String[] args) {
//		String str = Basics.readStr("txt: ");
//	
//		System.out.println("the txt u entered was: "+str);
//	}
	
	/**
	 * testing that switch is ok with switch-case
	 * @param args
	 */
//	public static void main(String[] args) {
//		String str = "u";
//		switch(str){
//		case "abc"	:
//			System.out.println("abc, potatoes");
//			break;
//		case "coucou":
//			System.out.println("coucou and some lines of instructions");
//			break;
//		default:
//			System.out.println("default and some line of instructions");
//		}
//	}
	
	/**
	 * here_07
	 * 
	 * about singleton
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		Singleton singleton = Singleton.getInstance();
//		singleton.doSth();
//		
//		Singleton singleton1 = Singleton.getInstance();
//		singleton1.doSth();
//	}
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		MysqlManage sql = new MysqlManage();
//	}
	
	/**
	 * here_06
	 * pour apprendre le dp:Observer
	 * @param args
	 */
//	public static void main(String[] args) {
//		SubjectObserved subject = new SubjectObserved();
//		
//		OctalObserver o1 = new OctalObserver(subject);
//		BinaryObserver b1 = new BinaryObserver(subject);
//		HexObserver h1 = new HexObserver(subject);
//		
//		subject.setState(9);
//		System.out.println("============================");
//		subject.setState(90);
//	}
	
	/**
	 * here_05
	 * @param args
	 */
//	public static void main(String[] args) {
//		StudentModelMVC student = new StudentModelMVC(1, "mmt");
//		StudentViewMVC view = new StudentViewMVC();
//		
//		System.out.println("*****************");
//		view.printStudent(student);
//		
//		StudentCtrlMVC ctrlr = new StudentCtrlMVC(student, view);
//		
//		ctrlr.setStudentName("coucou");
//		ctrlr.update();
//		System.out.println("******************");
//		ctrlr.updateView();
//	}
	
	/**
	 * here_04
	 */
//	public static void main(String[] args) {
//		ShapeCache03.loadCache();
//		ShapeProtot03 sp = ShapeCache03.getShape(3);
//		sp.speciality();
//	}
//	
	/*
	 * here_03
	 */
//	public static void main(String[] args) {
//		FactJouet fj = new FactJouet();
//		JouetFactInterf interfJ = fj.getjouet("plane");
//		interfJ.speciality();
//	}
	
	/*
	 * here_02
	 * do not yet really understand
	 */
//	public static void main(String[] args) {
//		ShapeCache.loadCache();
//		ShapeProtot shapeProtot = (ShapeProtot) ShapeCache.getShape(3);
//		shapeProtot.speciality();
//	}
	
	/*
	 * here_01
	 */
//	public static void main(String[] args) {
//		FactoryToy ftoy = new FactoryToy();
//		Toy toy1 = ftoy.getToy("car");
//		toy1.speciality();
//	}
	public Main() {
		// TODO Auto-generated constructor stub
	}
	
}
