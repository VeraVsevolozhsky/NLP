package general.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsDistribution {

	public class WordInfo { 
		String word;
		Integer counter;		
		
		public String toString() {
			return "Word: [" + word + "], counter: " + counter;
		}
	}
	
	public class WordInfoComparatorByName implements Comparator<WordInfo> {

		@Override
		public int compare(WordInfo o1, WordInfo o2) {
			return o1.word.compareTo(o2.word);
		}		
	}
	
	private class WordInfoComparatorDesc implements Comparator<WordInfo> {

		@Override
		public int compare(WordInfo o1, WordInfo o2) {
			return - o1.counter.compareTo(o2.counter);
		}		
	}
	
	public List<WordInfo> getDistributionSortedDescendingList(List<String> tokens) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String token : tokens) {
			Integer counter = map.get(token);
			if (counter == null) {
				counter = 1;				
			} else {
				counter++;
			}
			map.put(token, counter);			
		}
		
		List<WordInfo> words = new ArrayList<WordInfo>();
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			WordInfo wordInfo = new WordInfo();
			wordInfo.word = entry.getKey();			
			wordInfo.counter = entry.getValue();
			words.add(wordInfo);
		}
		
		Collections.sort(words, new WordInfoComparatorDesc());
		return words;
	}
	
	public List<WordInfo> getDistributionSubListSortedByName(List<String> tokens, int sublistSize) {
		List<WordInfo> list = getDistributionSortedDescendingList(tokens);
		list = list.subList(0, sublistSize);
		Collections.sort(list, new WordInfoComparatorByName());
		return list;
	}
}
