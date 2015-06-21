package Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

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
	
	public static void printHashTableByValue(
			Hashtable<String, String>hashtable, 
			String ... values ){
	
		for(String value : values ) {
			System.out.println(value + ": " + hashtable.get(value));
		}
		
	}

	public static void printHashMapByValues(HashMap<String, String> hashMap, String ... values ) {
		for(String value : values ) {
			System.out.println(value + ": " + hashMap.get(value));
		}
	}

	public static <K, V> void printHashMapByValues(HashMap<K, V> hashmap, V ... values) {
		for(V value : values) {
			System.out.println(value+": " + hashmap.get(value));
		}
	}
	
	public static <K, V> void printHashMap(HashMap<K, V> hashMap) {
		ArrayList<K> keys = getKeysFromHashMap(hashMap);
		
		for(K key : keys) 
			System.out.println(key + ": "+hashMap.get(key));
	}
	
	public static <K, V> ArrayList<K> getKeysFromHashMap(HashMap<K, V> hashMap) {
		ArrayList<K> res;
		Set < Entry<K, V> > entrees = hashMap.entrySet () ;
		
		Iterator iterator = entrees.iterator();
		
		res = new ArrayList();
		
		while(iterator.hasNext()) {
			Map.Entry <K, V> entree = (Map.Entry)iterator.next() ;
			res.add(entree.getKey());
		}
		
		return res;
	}
	
	public static void main00(String[] args) {
		HashMap<Integer, String> hashMap = new HashMap<>();
		
		hashMap.put(1, "coucou");
		hashMap.put(2, "test");
		hashMap.put(3, "hello");
		
		ArrayList<Integer> arrayList = getKeysFromHashMap(hashMap);
		
		System.out.println(hashMap.get(1));
		System.out.println(hashMap.get(2));
	}
	
}