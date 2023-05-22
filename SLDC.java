package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class SLDC {

	public static void main(String[] args) throws IOException {
		
		URL poem = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(poem.openStream()));

		
		Map<String, Integer> wordCounts = new HashMap<>();

		String line;
		
		bufferedReader.skip(170);

		while ((line = bufferedReader.readLine()) != null) {
			
			
			String[] words = line.split("[\\s.;,?:!()\"]+");

			
			for (String word : words) {

				word = word.trim();

				if (word.length() > 0) {

					if (wordCounts.containsKey(word)) {
						wordCounts.put(word, wordCounts.get(word) + 1);
					} else {
						wordCounts.put(word, 1);
					}
				}
			}
		}

		
		Map<String, Integer> sortedWordCounts = wordCounts.entrySet().stream()
				.sorted(Collections.reverseOrder(Entry.comparingByValue()))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		
		System.out.printf("%-20s%15s\n", "Word", "Frequency");

		System.out.println();

		for (Map.Entry<String, Integer> entry : sortedWordCounts.entrySet()) {
			
			System.out.printf("%-20s%10s\n", entry.getKey(), entry.getValue());
		}

		bufferedReader.close();
	}
}
