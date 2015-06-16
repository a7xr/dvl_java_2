package langAi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Tools.Basics;
import Tools.MysqlManage;

enum State {draft, real}
public class TalnTools {

	MysqlManage mysql;
	ArrayList<String> tovona;
	ArrayList<String> tovana;
	ArrayList<String> ftt;
	ArrayList<String> anarana;
	
	// constructor
	public TalnTools(State s) {
		if(s == State.real)
			try {
				mysql = new MysqlManage();
				tovona = getTovona();
				tovana = getTovana();
				ftt = getFtt();
				anarana = getAnarana();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else{
			
		}
	}
	
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
		for(String _tovana:this.tovana) {
			if(_teny.equalsIgnoreCase(_tovana))
				return true;
		}
		return false;
	}
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
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
		ArrayList<String> voaRasa = rasaTeny(_teny);
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
	 * katreto dia mizara ny 
	 * 		tovona	...	ftt		...	tovana
	 * @param teny
	 * @return
	 */
	public ArrayList<String> rasaTeny(String teny) {
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
	
	public ArrayList<String> getAnarana() {
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like '%anarana%';");
	}
	
	public ArrayList<String> getFtt() {
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like 'ftt';");
	}
	
	public ArrayList<String> getTovana() {
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like 'tovana';");
	}
	
	public ArrayList<String> getTovona(){
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like 'tovona';");
	}
	
	/**
	 * 
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
	
	public String removeTovana(String _teny) {
		String res = _teny.substring(0, _teny.length() - 3);
		return res;
	}
	
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
	 * mbl tsVita satri mkotrana regex
	 * am teny hoe mandainga
	 * lasa hoe dainga ka milatsaka ny d dia msolo l
	 * @param _teny ex: dainga
	 * @param voloo ex: d
	 * @param solony ex:
	 * @return
	 */
	public static String nSoloNvolooKNaazFtt (String _teny, 
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
		
//		String coucou = "test".repla
		
		return null;
		
	}
	
	public static void main(String[] args) {
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
	 * test rasateny
	 * @param args
	 */
	public static void main03(String[] args) {
		String teny = "ihiraina";
		ArrayList<String> rasa = new TalnTools().rasaTeny(teny);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		for(String _efitra:getEfitra()) {
			if(_teny.equalsIgnoreCase(_efitra))
				return true;
		}
		return false;
	}
	
	public ArrayList<String> getEfitra() {
		return mysql.simpleSelectQuery(
				"select teny from karazateny where karazana like 'efitra';");
	}
	
}
