package langAi;

import java.util.ArrayList;
import java.util.HashMap;

import Tools.Basics;

public class Id01 {

	/**
	 * 
	 * @param vct01
	 * @param vct02
	 * @return
	 */
	public static int getOccurence(String txt, String str) {
		
		String[] strArray = txt.split(" ");
		int res = 0;
		
		for(String part : strArray) {
			if(part.equalsIgnoreCase(str)) {
				res++;
			}
		}
		
		return res;
	}
	
	public static void main04(String[] args) {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		hashMap.put(1, 1);
		hashMap.put(2, 1);
		hashMap.put(3, 0);
		hashMap.put(4, 1);
		hashMap.put(5, 1);
		
		System.out.println(norme(hashMap));
	}
	
	public static double norme(HashMap<Integer, Integer> vector) {
		double intSqrt = 0;
		ArrayList<Integer> keys = Basics.getKeysFromHashMap(vector);
		
		for(Integer key: keys) {
			intSqrt += Math.pow(vector.get(key), 2);
		}
		
		return Math.sqrt(intSqrt);
	}
	
	public static void main(String[] args) {
		
		Basics.printHashMap(setVector("this is a very hard test"));
		
	}
	
	public static HashMap<String, Integer> setVector(String _txt) {
		HashMap<String, Integer> res = new HashMap<>();
		for(String str : _txt.split(" "))
			res.put(str, getOccurence(_txt, str));
		return res;
	}
	
	/**
	 * to compute the prdScalaire between 2vct
	 * @param vct01
	 * @param vct02
	 * @return
	 */
	public static double prodScalaire(
			HashMap<Integer, Integer> vct01,
			HashMap<Integer, Integer> vct02) {
		
		double res = 0;
		
		ArrayList<Integer> keysVct01 = Basics.getKeysFromHashMap(vct01);
		ArrayList<Integer> keysVct02 = Basics.getKeysFromHashMap(vct02);
		
		if(keysVct01.size() != keysVct02.size())
			return -1;
		else {
			for (int i = 0; i < keysVct01.size(); i++) {
				res += vct01.get(i) * vct02.get(i);
			}
		}
		return res;
		
	}
	
	public static void main03(String[] args) {
		double a = Math.acos( (Math.sqrt(3)) / 3 );
		
		double b = Math.toDegrees(a);
		
		System.out.println(b);
	}

	public static void main02(String[] args) {
//		System.out.println(getOccurence("java this is a test of doing java", "java"));
	}
	
	public static void main01(String[] args) {
		HashMap<Integer, Integer> v01 = new HashMap<>();
		HashMap<Integer, Integer> v02 = new HashMap<>();
		
		v01.put(0, 5);
		v01.put(1, 3);
		v01.put(2, 0);

		v02.put(0, 0);
		v02.put(1, 0);
		v02.put(2, 6);
		
		System.out.println(prodScalaire(v01, v02));
	}
	
}
