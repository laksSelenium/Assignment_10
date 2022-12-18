
//To count the duplicate words from a sentence
package assignment_10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Assignment10_Task3 {

	public static void main(String[] args) {
		int count=0;
		String sentence = "iNeuron has many courses. iNeuron teaches Selenium";
		String [] sentenceSplit = sentence.split(" ");
		List<String> arrayToList = new ArrayList();	
		HashMap<String,Integer> wordCounter = new HashMap();
		
		for(String s:sentenceSplit ) {
			arrayToList.add(s);
		}
		
		
		
		Iterator<String> it =arrayToList.iterator();
		while(it.hasNext()) {
			String wordToMap = it.next();
			if(!wordCounter.containsKey(wordToMap)) {
				wordCounter.put(wordToMap, 1);
			}else {
				count = (wordCounter.get(wordToMap))+1;
				wordCounter.put(wordToMap, count);
			}
		}
		
		System.out.println(wordCounter);

	}
}
