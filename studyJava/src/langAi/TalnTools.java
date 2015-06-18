package langAi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Tools.Basics;
import Tools.MysqlManage;

/**
 * this is a test
 * @author a7xr
 *
 */
enum State {
	/**
	 * natao ho passena en argument
	 */
		draft, 
		real
	}
public class TalnTools {

	MysqlManage mysql;
	ArrayList<String> tovona;
	ArrayList<String> tovana;
	ArrayList<String> ftt;
	ArrayList<String> anarana;
	
	/**
	 * amnireo misy teny milatsaka
	 */
	String [][] rplarr = new String [] [] {
//			
//			  +-- ireto ilai miaraka am tena_ftt
//			  |    
//			  |    +--- ireto ilai _milatsaka_
//			  |    |
//			  |	   |
//			  v	   v
			{"l", "d"}, 
			{"t", "n"},
			{"h", "g"},
			{"r", "dr"},
			{"p", "m"},
			{"s", ""},
			{"s", "n"},
			{"v", "m"},
	};
	
	/**
	 * 
	 * @param s State.real <br/>State.draft
	 */
	// constructor
	public TalnTools(State s) {
		if(s == State.real)
			try {
				mysql = new MysqlManage();
				tovona = getAllTovona();
				tovana = getAllTovana();
				ftt = getAllFtt();
				anarana = getAllAnarana();
			} catch (ClassNotFoundException e) {
				//   Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				//   Auto-generated catch block
				e.printStackTrace();
			}
		else{
			
		}
	}
	
	/**
	 * mtov am <br/>
	 * this(State.real)
	 * <br/>
	 * State dia type(enum)
	 */
	public TalnTools() {
		this(State.real);
	}
	
	public boolean isFtt(String _teny) {
//		if(_teny.length() == 0)
//			return false;
		for(String _ftt:this.ftt) {
			if(_teny.equalsIgnoreCase(_ftt))
				return true;
		}
		return false;
	}
	

	public boolean isTovona(String _teny) {
//		if(_teny.length() == 0)
//			return false;
		for(String _tovona:this.tovona) {
			if(_teny.equalsIgnoreCase(_tovona))
				return true;
		}
		return false;
	}
	
	public boolean isTovana(String _teny) {
//		if(_teny.length() == 0)
//			return false;
		for(String _tovana:getAllTovana()) {
			if(_teny.equalsIgnoreCase(_tovana))
				return true;
		}
		return false;
	}
	
	/**
	 * possible we param(_word) ok syntaxiquement fa tsy ok semantiquement <br/>
	 * ex: _word = milainga
	 * <br/> apdirn ani anati db rah tsis dikany
	 * @param _word
	 */
	public void checkIfNormalWordIsNotOk(String _word) {
		String res = Basics.readStr("ny teny: "+_word+" ve misy dikany? [y/n]");
		switch (res) {
		case "y":
			System.out.println("ok");
			break;
		case "n":
			try {
				String _query = "insert into tenyNormalTsiz (teny, state) values "
						+"('"+ _word+"', 0)";
				mysql.ins_upd_del_QueryToStatement(_query);
				
			} catch (SQLException e) {
				//   Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	
	/**
	 * mteni waz ny anaranlai method
	 * @param _teny
	 * @return
	 */
	public boolean isLazaina(String _teny) {
		ArrayList<String> _anaranas = mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like '%anarana%'");
		
		for(String _var : _anaranas){
			if (_var.equalsIgnoreCase(_teny))
				return true;
		}
		
		return false;
	}
	
	
	public boolean isAnaranaIombonana(String _teny) {
		ArrayList<String> anaranaIombonana = mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like 'anarana iombonana'");
		
		for(String _var : anaranaIombonana){
			if (_var.equalsIgnoreCase(_teny))
				return true;
		}
		
		return false;
	}
	
	public boolean isEntiMilaza01(String _teny) {
//		boolean res = true;
		ArrayList<String> voaRasa = rasaTeny01(_teny);
		for(int i = 0; i < voaRasa.size(); i++) {
//			System.out.println(voaRasa.get(i).toString());
			
		}
		
		if( (isTovona(voaRasa.get(0).toString())) && (isFtt(voaRasa.get(1).toString()))
				&& (isTovana(voaRasa.get(2).toString()))) {
			return true;
		}
		
		if ( (isTovona(voaRasa.get(0).toString())) && (isFtt(voaRasa.get(1).toString())) )
			return true;
		
		if ( (isFtt(voaRasa.get(0).toString())) && (isTovana(voaRasa.get(1).toString())) )
			return true;
		
		return false;
	}
	
	
	/**
	 * TODO
	 * ny teo aloha dia hoe <br/>
	 * 	tvo	+	ftt	+	tva
	 * <br/>
	 * ka ny ftt dia _tsy_ftt_marina_ (ex: dainga(ftt) avam hoe mandainga)
	 * <br/>
	 * <br/>
	 * iti dia atao hoe <br/>
	 * ex: _teny = mandainga <br/>
	 * return man + lainga
	 * @param _teny
	 * @return
	 */
	public ArrayList<String> rasaTeny03(String _teny) {
		ArrayList<String> res = new ArrayList<>();
		
		ArrayList<String> voaRasaFaFttTsMety = rasaTeny02(_teny);
		
		
		return null;
	}
	
	/**
	 * mandrasa teny simple
	 * 	ka tsRarahina ny ftt na iz na tsiz
	 * @param _teny ex: mandoko
	 * @return man doko
	 */
	public ArrayList<String> rasaTeny02(String _teny) {
		
		ArrayList<String> res = new ArrayList<>();
		
		if(isTovana(_teny.substring(_teny.length() - 3, _teny.length()))) // rah misi tovana
		{ // rah misi tovana
//			System.out.println("misi tva");
			if(
				(isTovona(_teny.substring(0, 1) ) ) 
				| (isTovona(_teny.substring(0, 2))) 
				| (isTovona(_teny.substring(0, 3))) 
			) {
				// misi tvo sy tva
				ArrayList<String> rasaTvo = rasaTenyTovona(_teny);
				ArrayList<String> rasaFaa2 = rasaTenyTovana(rasaTvo.get(1).toString());
				
				res.add(rasaTvo.get(0));
				res.add(rasaFaa2.get(0));
				res.add(rasaFaa2.get(1));
			}
			else // rah misi tovana fa tsis tovona
			{
//				System.out.println("ato");
				return rasaTenyTovana(_teny);
			}
		}
		else // rah tsis tovana
		{ // rah tsis tovana
//			System.out.println("ato");
			return rasaTenyTovona(_teny);
		}
		
		return res;
	}
	
	/**
	 * katreto dia mizara ny <br/>
	 * 		tovona	...	ftt		...	tovana <br/>
	 * 	ar ny ftt dia tsMaintsFtt marina
	 * @param teny
	 * @return
	 */
	public ArrayList<String> rasaTeny01(String teny) {
		ArrayList<String> res = new ArrayList<>();
		String tvana = teny.substring(teny.length() - 3, teny.length());
		if(isTovana(tvana)){
			
			if(isFtt(teny.substring(0, teny.length() - 3))) {
				res.add(teny.substring(0, teny.length() - 3));
				res.add(tvana);
				return res;
			}
			
			String tvoFtt = teny.substring(0, teny.length() - 3);
			
			String tvoFttMialaCharVoloo = esorinaCharKatrAm(tvoFtt, 1);
			boolean isTrueTvoFttMialaCharVoloo =
					isFtt(tvoFttMialaCharVoloo);
			if(isTrueTvoFttMialaCharVoloo) {
				res.add(tvoFtt.substring(0, 1));
				res.add(tvoFtt.substring(1));
				res.add(tvana);
				return res;
			}
		}else {
			String tvoFttMialaCharFaaroo = esorinaCharKatrAm(teny, 2);
			boolean isTrueTvoFttMialaCharFaaroo =
					isFtt(tvoFttMialaCharFaaroo);
			if(isTrueTvoFttMialaCharFaaroo){
				res.add(teny.substring(0, 2));
				res.add(teny.substring(2));
				res.add("");
				return res;
			}
		}
		
		
		return res;
	}

	public String[] splitTovonaWithoutTovana(String _teny) {
		String [] res = new String[2];
		String mialaVoloo = esorinaCharKatrAm(_teny, 1);
		if (isFtt(mialaVoloo)) {
			res[0] = _teny.substring(0, 1);
			res[1] = _teny.substring(1);
			return res;
		}
		
		String mialaFaaroo = esorinaCharKatrAm(_teny, 2);
		if(isFtt(mialaFaaroo)) {
			res[0] = _teny.substring(0, 2);
			res[1] = _teny.substring(2);
		}
		return res;
	}
	
	
	
	/**
	 * ilaina rhf ilai esorina n char voloo na faaroo ka jerena we 
	 * ftt v ny teny manaraka ao
	 * @param tvoFtt
	 * @param i
	 * @return
	 */
	private static String esorinaCharKatrAm(String tvoFtt, int i) {
		String tvoFttMialaCharVoloo = tvoFtt.substring(i, tvoFtt.length());
		return tvoFttMialaCharVoloo;
	}
	
	/**
	 * 
	 * @return ireo anarana rht avam db <br/>
	 * tsakfid mits na anarana iombonana na inn
	 */
	public ArrayList<String> getAllAnarana() {
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like '%anarana%';");
	}

	
	/**
	 * 
	 * @return ireo ftt rht ani anati db
	 */
	public ArrayList<String> getAllFtt() {
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like 'ftt';");
	}
	
	
	/**
	 * 
	 * @return ireo tovana rht ao anaty db
	 */
	public ArrayList<String> getAllTovana() {
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like 'tovana';");
	}
	
	
	/**
	 * 
	 * @param _teny
	 * @return
	 */
	public ArrayList<String> getTovanaFromString (String _teny) {
		ArrayList<String> res = new ArrayList<>();
		
		if(isTovana(_teny)) {
			if(isTovona(_teny)){
				
			}
		}
		
		return res;
	}
	
	/**
	 * 
	 * @return ireo tovona ao anaty db
	 */
 	public ArrayList<String> getAllTovona(){
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like 'tovona';");
	}
	
	@Deprecated
	/**
	 * there is a method already done in java
	 * 	String.endsWith("end");
	 * @param _str the string in which i will test
	 * @param _end the end of character in which the program will test to @_str
	 * @return
	 */
	public static boolean endedTo(String _str, String _end) {
		
		int endLength = _end.length();
//		String withoutEnd = _str.substring(0, _str.length() - endLength);
		String strEnd = _str.substring(_str.length() - endLength, _str.length());
		
		if(strEnd.equalsIgnoreCase(_end))
			return true;
		
		return false;
	}
	
	/**
	 * 
	 * @param _teny
	 * @return rhf niala ny tovana
	 */
	public String removeTovana(String _teny) {
		String res = _teny.substring(0, _teny.length() - 3);
		return res;
	}
	
	
	/**
	 * 
	 * @param _teny
	 * @return rhf niala ny tovona
	 */
	public String removeTovona(String _teny) {
		boolean isTvo;
		
		isTvo = isTovona(_teny.substring(0, 1));
		if (isTvo) 
			return _teny.substring(1, _teny.length());
		
		isTvo = isTovona(_teny.substring(0, 2));
		if(isTvo)
			return _teny.substring(2, _teny.length());
		
		isTvo = isTovona(_teny.substring(0, 3));
		if (isTvo) 
			return _teny.substring(3, _teny.length());
		
		return null;
	}
	
	
	
	
	/**
	 * mbl tsVita satri mkotrana regex <br/> <br/>
	 * am teny hoe mandainga 
	 * lasa hoe dainga ka milatsaka ny d dia msolo l
	 * @param _teny ex: dainga
	 * @param voloo ex: d
	 * @param solony ex:
	 * @return
	 */
	public boolean nSoloNvolooKNaazFttV (String _teny, 
			String miarakaAmFtt, String solony) {
		/**
		 * ex: _teny = dainga
		 * 		avam ftt lainga
		 * 			miarakaAmFtt = l
		 * 			solony = d
		 * ex: _teny = dreraka
		 * 		avam ftt reraka
		 * 			miarakaAmFtt = r
		 * 			solony = dr
		 */
		StringBuffer f = new StringBuffer(_teny);
//		System.out.println(f.replace(0, solony.length(), miarakaAmFtt).toString());
//		System.out.println(f.replace(0, solony.length(), miarakaAmFtt).toString());
		return isFtt(f.replace(0, solony.length(), miarakaAmFtt).toString());
		
	}
	
	public boolean nSoloNvolooKNaazFttV (String _teny) {
		for (int i = 0; i < rplarr.length; i++) {
			if(nSoloNvolooKNaazFttV(_teny, rplarr[i][0], rplarr[i][1]))
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * mandrasa teny(xxxx + tovana) <br/>
	 * ex: _word = ihiraina <br/>
	 * tsy maintsy misi tovana ao fa rah tsizai dia  <br/>
	 * mamoaka tsis erreur izi kanefa zavatra diso
	 * <br/> 
	 * @param _word ex: ihiraina
	 * @return 
	 * araka ny _word eo ambony <br/>
	 * [ihira] <br/>
	 * 	[ina]
	 */
	public static ArrayList<String> rasaTenyTovana(String _word) {
		ArrayList<String> res = new ArrayList<String>();
		
		res.add(_word.substring(0, _word.length() - 3));
		res.add(_word.substring(_word.length() - 3, _word.length()));
		
		return res;
	}
	
	public ArrayList<String> rasaTenyTovona(String teny) {
		ArrayList<String> res = new ArrayList<>();
		
		if(
//			!(isTovona(teny.substring(0, 1))) 
//			& !(isTovona(teny.substring(0, 2))) 
//			& 
			(isTovona(teny.substring(0, 3))) 	// ny char(3)voloo ian no tvo
		) {
			res.add(teny.substring(0, 3));
			res.add(teny.substring(3, teny.length()));
			return res;
		}
		
		if( 
//				!(isTovona(teny.substring(0, 1))) 
				(isTovona(teny.substring(0, 2))) 
//				& !(isTovona(teny.substring(0, 3))) 	
		) {
			res.add(teny.substring(0, 2));
			res.add(teny.substring(2, teny.length()));
			return res;
		}

		if( 
				(isTovona(teny.substring(0, 1))) 
//				& !(isTovona(teny.substring(0, 2))) 
//				& !(isTovona(teny.substring(0, 3))) 
		) {
			res.add(teny.substring(0, 1));
			res.add(teny.substring(1, teny.length()));
			return res;
		}
		
//		if (isTovona(teny.substring(0, 2))){
//			res.add(teny.substring(0, 2));
//			res.add(teny.substring(2, teny.length()));
//			return res;
//		}
		return null;
	}
	
	public static void main12(String[] args) {
		ArrayList<String> voaRasa = new TalnTools().rasaTeny01("milakaina");
		Basics.printArrayListString(voaRasa);
	}
	
	/**
	 * test rasaTenyTovona
	 * @param args
	 */
	public static void main11(String[] args) {
		ArrayList<String> voaRasa = new TalnTools().rasaTenyTovona("ihiraina");
		Basics.printArrayListString(voaRasa);
	}
	
	/**
	 * mianatra StringBuffer
	 * @param args
	 */
	public static void main10(String[] args) {
		StringBuffer strBuff = new StringBuffer("le langage java 2");
		strBuff.replace(3, 10, "LANGAGE");
		System.out.println(strBuff.toString());
	}
	
	/**
	 * testing the superlative
	 * @param args
	 */
	public static void main09(String[] args) {
		System.out.println(superlative("happy"));
	}
	
	public static void main18(String[] args) {
//		HashMap hashMap = new HashMap<>();
		System.out.println("this is a test");
	}
	
	/**
	 * mianatra hashtable
	 * @param args
	 */
	public static void main17(String[] args) {
		Hashtable<String, String> hashtable = new Hashtable<>();
		
		hashtable.put("tovona", "i");
		hashtable.put("ftt", "hira");
		hashtable.put("tovana", "ina");
		
//		System.out.println(hashtable.get("tovona"));
//		Enumeration e = hashtable.elements();
//		
//		while(e.hasMoreElements()) {
//			System.out.println(e.nextElement()); 
//		}
		
		Basics.printHashTableByValue(hashtable, "tovona", "ftt");
		
	}
	
	/**
	 * i testena ni rasaTeny02(String)
	 * @param args
	 */
	public static void main(String[] args) {
		printSimpleArrayString(new TalnTools().rasaTeny02("mandoko"));
	}
	
	public static void printSimpleArrayString(ArrayList<String> al) {
		Basics.printArrayListString(al);
	}
	
	/**
	 * to test nSoloNvolooKNaazFttV("drora")
	 * 
	 */
	public static void main15(String[] args) {
		
		System.out.println(new TalnTools().nSoloNvolooKNaazFttV("drora"));
	}
	
	public static void main14(String[] args) {
		System.out.println(new TalnTools().nSoloNvolooKNaazFttV("dainga", "l", "d"));
	}
	
	/**
	 * rasaTeny01 ka ni ftt dia tsMaints ftt_marina
	 * @param args
	 */
	public static void main13(String[] args) {
		System.out.println(new TalnTools().rasaTeny01("milainga"));
	}
	
	/**
	 * to create a comparative of a word
	 * @param _word
	 * @return the comparative word
	 */
	public static String comparative01(String _word) {
	
		if(_word.endsWith("y")){
			StringBuffer strBuff = new StringBuffer(_word);
			strBuff.replace(_word.length() - 1, _word.length(), "ier");
			return strBuff.toString();
		}
		else {
			return _word+"er";
		}
	}
	
	
	public static String superlative(String _word) {
		if(_word.endsWith("y")){
			StringBuffer strBuff = new StringBuffer(_word);
			strBuff.replace(_word.length() - 1, _word.length(), "iest");
			return strBuff.toString();
		}
		else {
			return _word+"est";
		}
//		return null;
	}
	
	/**
	 * kotrana regex
	 * @param args
	 */
	public static void main07(String[] args) {
//		String texte =  "0 33 32 456 85" ;
//		String regex = "0( )?(3[234])( )?([0-9]{2})( )?([0-9]{3})( )?([0-9]){2}";
		
		String texte = "asdølf@gmail.com";
		String regex = "(.*)@(.*)\\.(.{2,3})";
		Pattern p = Pattern.compile(regex) ;
		Matcher m = p.matcher(texte) ;
		boolean b = m.matches() ;
		
		System.out.println(b);
	}
	
	
	/**
	 * testing removeTovona and removeTovana
	 * @param args
	 */
	public static void main06(String[] args) {
		String str = "ihiraina";
		System.out.println(new TalnTools().removeTovona(str));
	}
	
	
	/**
	 * to test if a word ended by ka, tra, na
	 * @param args
	 */
	public static void main05(String[] args) {
		System.out.println(endedTo("entana", "na"));
	}
	
	
	/**
	 * will test if the param is a sentence or not
	 * @param args
	 */
	public static void main04(String[] args) {
		System.out.println(new TalnTools().isPhrase01("hiraina ny micro"));
	}
	
	
	/**
	 * test rasaTeny01
	 * @param args
	 */
	public static void main03(String[] args) {
		String teny = "ihiraina";
		ArrayList<String> rasa = new TalnTools().rasaTeny01(teny);
		Basics.printArrayListString(rasa);
	}
	
	
	/**
	 * exactly the same as below
	 * @param args
	 */
	public static void main02(String[] args) {
		MysqlManage mysql;
		try {
			mysql = new MysqlManage();
			ArrayList<String> tovona = mysql.simpleSelectQuery(
					"select tovona from tovona");
			ArrayList<String> tovana = mysql.simpleSelectQuery(
					"select tovona from tovona");
			
			System.out.println();
			System.out.println("tovona..................");
			Basics.printArrayListString(tovona);
			System.out.println();
			System.out.println("tovana..................");
			Basics.printArrayListString(tovana);
		}
		catch (ClassNotFoundException e) {
			
		}catch(SQLException e) {
			
		}
	}
	
	
	/**
	 * testing taking only one column from db
	 * @param args
	 */
	public static void main01(String[] args) {
		MysqlManage mysql;
		try {
			mysql = new MysqlManage();
			ArrayList<String> res = mysql.simpleSelectQuery(
					"select tiitle from notes_tech order by tiitle;");
			Basics.printArrayListString(res);
			
		} catch (ClassNotFoundException e) {
			//   Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			//   Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * tsy mlai satri rah irai ian ni ligne_resultat dia tsMvoka ilai izi
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main00(String[] args) throws ClassNotFoundException, SQLException {
//		
		MysqlManage mysql = new MysqlManage();
		mysql.selectQueryToResultSet("select tiitle from notes_tech where id_n_t in (955);");
		String[][] strArr = mysql.resultSetToStrDoubleArray();
		Basics.printDoubleDimensionArrayOfString(strArr);
	}
	
	public boolean isPhrase01(String txt) {
		String[] strArr = txt.split(" ");
		
		if( ( isEntiMilaza01(strArr[0]) ) && (isEfitra(strArr[1])) && (isLazaina(strArr[2])) )
			return true;
		
		return false;
	}
	
	public boolean isEfitra(String _teny) {
		for(String _efitra:getAllEfitra()) {
			if(_teny.equalsIgnoreCase(_efitra))
				return true;
		}
		return false;
	}
	
	
	public ArrayList<String> getAllEfitra() {
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like 'efitra';");
	}
	
}
