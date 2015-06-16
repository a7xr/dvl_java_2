package Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Basics {
	
	public static void printArrayListString(ArrayList<String> array){
		int i = 0;
		for (String str:array){
			System.out.println("String["+i+"]: " + str);
			i++;
		}
	}
	
	/**
	 * remember that HashMap00 starts from 0
	 * @param objects
	 */
	public static void printHashMap_IntString(HashMap<Integer, String> objects){
		for(int i = 0; i < objects.size(); i++) {
			System.out.println("i: "+ i);
			System.out.println(objects.get(i).toString());
		}
	}
	
	public static void printDoubleDimensionArrayOfString(String[][]strArr){
		for (int i = 0; i < strArr.length; i++){
			System.out.println("line[ "+i+" ]: ");
			for(int j = 0; j < strArr[i].length; j++){
				System.out.print(strArr[i][j]);
			}
			System.out.println("");
		}
	}

	public static String readStr(String txt) {
		Scanner scanner = new Scanner(System.in);
        System.out.print(txt);

        String res = scanner.nextLine();
        
        return res;
	}
	
	public static int generateIntRand(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	public static void printSimpleArrayString(String[] strArr) {
		int i = 0;
		for(String str : strArr) {
			System.out.println(i+": "+str);
			i++;
		}
	}
	
}
