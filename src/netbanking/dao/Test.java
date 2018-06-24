package netbanking.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Test {
	private static List<Integer> myList = new ArrayList<Integer>();
	private static Map<Integer,String> myMap = new HashMap<Integer,String>();
	
	static{
		
		myMap.put(1, "First");
		myMap.put(2, "Second");
		myMap.put(3, "Third");
		myMap.put(4, "Fourth");
		myMap.put(5, "Fifth");
		myMap.put(6, "Sixth");
	}
	public static void main(String[] args) {
		System.out.println(generateRandomQuestion());
	}
	
	
	private static String generateRandomQuestion(){
		
		Random rd = new Random();
		
		while(myList.size()<3)
		{
			int num = rd.nextInt(6) + 1;
			if(!myList.contains(num)){
				myList.add(num);
			}
		}
		
		Collections.sort(myList);
		
		StringBuilder sb = new StringBuilder("Enter the ");
		sb.append(myMap.get(myList.get(0)));
		sb.append(" , ");
		sb.append(myMap.get(myList.get(1)));
		sb.append(" and ");
		sb.append(myMap.get(myList.get(2)));
		sb.append(" digits of your security password");
		return sb.toString();
		
	}

}
