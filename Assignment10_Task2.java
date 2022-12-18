
//To count the duplicate characters from a string
package assignment_10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Assignment10_Task2 {

	public static void main(String[] args) {
		String word = "selenium";
		int count = 0;

		List<String> listOfChar = new ArrayList<String>();

		HashMap<String, Integer> duplicateMap = new HashMap<String, Integer>();

		for (int i = 0; i < word.length(); i++) {
			String a = Character.toString(word.charAt(i));
			listOfChar.add(a);
		}

		Iterator<String> it = listOfChar.iterator();
		while (it.hasNext()) {
			String a = it.next();

			if (!duplicateMap.containsKey(a)) {
				duplicateMap.put(a, 1);
			} else {
				count = (duplicateMap.get(a)) + 1;
				duplicateMap.put(a, count);
			}

		}

		System.out.println(duplicateMap);

	}
}
