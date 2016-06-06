package core.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class WordCounterUtil {
	/*
	 * Unable to fully structure code Enabled aggressive block sorting Enabled
	 * unnecessary exception pruning Enabled aggressive exception aggregation
	 * Lifted jumps to return sites
	 */
	public static Map<String, Integer> wordCount() throws FileNotFoundException, IOException {
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		FileInputStream fis = null;
		Scanner scanner = null;
		String next = null;
		try {
			fis = new FileInputStream(new File("C:\\temp\\sam.txt"));
			scanner = new Scanner(fis, "UTF-8");
			while (scanner.hasNextLine()) {
				next = scanner.nextLine();
				for (String word : next.split("\\s")) {
					if (wordCount.containsKey(word = word.replaceAll("[\\-\\+\\.\\^:,\\;]", ""))) {
						wordCount.put(word, wordCount.get(word) + 1);
						continue;
					}
					wordCount.put(word, 1);
				}
			}

		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		System.out.println(wordCount.toString());
		return wordCount;
	}

	public static List<String> topTexts(int top) throws FileNotFoundException, IOException {
		LinkedHashMap<String, Integer> orderedWordCount = (LinkedHashMap<String, Integer>) WordCounterUtil
				.sortByValue(WordCounterUtil.wordCount(), top);
		System.out.println(orderedWordCount.entrySet().toString());
		return WordCounterUtil.convertMapToList(orderedWordCount);
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, int top) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {

			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list.subList(0, top)) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static List<String> convertMapToList(Map<String, Integer> filteredMap) {
		ArrayList<String> filteredList = new ArrayList<String>();
		StringBuffer sb = null;
		for (Map.Entry<String, Integer> entry : filteredMap.entrySet()) {
			sb = new StringBuffer();
			sb.append(entry.getKey()).append("|").append(entry.getValue());
			filteredList.add(sb.toString());
		}
		return filteredList;
	}
}
