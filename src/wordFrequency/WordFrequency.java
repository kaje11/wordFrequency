package wordFrequency;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * <h1>WordFrequency</h1> counts the frequency of words in a file.
 * It provides functionality such as:
 * - finding palindromes
 * - getting the least frequent words
 * - getting the most frequent words
 * - generating an HTML cloud with found words
 * @author kaje11
 * @version 0.1
 */
public abstract class WordFrequency {
	private static final int MAX_FONT_SIZE = 70;
	private static final int MIN_FONT_SIZE = 20;
	private static final int DEFAULT_RADIUS = 400;

	/**
	 * Path the file is located in.
	 */
	protected String path;
	/**
	 * wordMap contains all of the words and number of occurrences.
	 */
	private Map<String, Integer> wordMap =
			new LinkedHashMap<String, Integer>();
	/**
	 * The total of words found in the file.
	 */
	private int totalWords = 0;

	/**
	 * <b>WordFrequency</b> is the constructor for WordFrequency class.
	 * @param filePath is the path provided by user for the words source.
	 */
	WordFrequency(final String filePath) {
		this.path = filePath;
	}

	/**
	 * This function is called inside WordFrequencyFactory class.
	 * It has local or net file parsing implemented functionality.
	 */
	abstract void parseFile();

	/**
	 * This function returns number of occurrences in the whole file.
	 * @return totalWords found in the whole file.
	 */
	public int getTotalWords() {
		return this.totalWords;
	}

	/**
	 * <b>getTotalWords</b> returns the sum of values in map.
	 * @param map - a map containing word and number of occurrences
	 * @return total of values inside the map
	 */
	public int getTotalWords(final Map<String, Integer> map) {
		int sum = 0;
		for (int i : map.values()) {
			sum += i;
		}
		return sum;
	}
	/**
	 * <b>getLastElement</b> returns the last element found in a Map.
	 * @param map with String as key, and Integer as value.
	 * @return Entry<String,Integer>
	 */
	public Entry<String, Integer> getLastElement(
			final Map<String, Integer> map) {
		Iterator<Entry<String, Integer>> iterator =
				map.entrySet().iterator();

		Map.Entry<String, Integer> result = null;
		while (iterator.hasNext()) {
			result = iterator.next();
		}
		return result;
	}
	/**
	 * <b>getFirstElement</b> returns first element found in a Map.
	 * @param map with String as key, and Integer as value.
	 * @return Entry<String,Integer>
	 */
	public Entry<String, Integer> getFirstElement(
			final Map<String, Integer> map) {
		return map.entrySet().iterator().next();
	}

	/**
	 * <b>parseLine</b> method is called within parseFile function.
	 * This function fills the map with words found in the file.
	 * It allows digits, alphanumeric characters, underscore and minus char.
	 * @param line is a String sent from parseFile function.
	 */
	protected void parseLine(final String line) {
		String[] words = line.split("\\s+");
		for (String word : words) {
			if (word.matches("[\\d\\w0-9-_]+")) {
				word = word.replaceAll("[^\\d\\w0-9-_]", "");
				this.totalWords++;

				Integer count = wordMap.get(word);
				if (count == null) {
					wordMap.put(word, 1);
				} else {
					wordMap.put(word, count + 1);
				}
			}
		}
	}

	/**
	 * <b>getMostFrequent</b> returns the most frequent items from a Map.
	 * @param limit limits the Map to x items.
	 * @return Map<String,Integer> with sorted, the most frequent items.
	 */
	public Map<String, Integer> getMostFrequent(final int limit) {
		if (limit > this.wordMap.size()) {
			return getMostFrequent();
		}

		Map<String, Integer> newMap =
				new LinkedHashMap<String, Integer>(limit);
		Map<String, Integer> sortedMap =
				WordFrequency.sortByValue(this.wordMap, true);

		int inc = 0;
		Iterator<Entry<String, Integer>> it =
				sortedMap.entrySet().iterator();

		while (it.hasNext() && inc < limit) {
			Entry<String, Integer> word = it.next();
			newMap.put(word.getKey(), word.getValue());
			inc++;
		}

		return newMap;
	}

	/**
	 * <b>getMostFrequent</b> returns the most frequent items from a Map.
	 * @return Map<String,Integer> with sorted, the most frequent items.
	 */
	public Map<String, Integer> getMostFrequent() {
		return this.getMostFrequent(this.wordMap.size());
	}

	/**
	 * <b>printMostFrequent</b> prints the most frequent items from a Map.
	 * @param limit limits the Map to x items.
	 */
	public void printMostFrequent(final int limit) {
		Map<String, Integer> sorted = this.getMostFrequent(limit);

		for (Entry<String, Integer> word : sorted.entrySet()) {
			System.out.println("Word: " + word.getKey()
					+ " Occurences: " + word.getValue());
		}
	}

	/**
	 * <b>printMostFrequent</b> prints the most frequent items from a Map.
	 */
	public void printMostFrequent() {
		this.printMostFrequent(this.wordMap.size());
	}

	/**
	 * <b>getLeastFrequent</b> returns the least frequent items from a Map.
	 * @param limit limits the Map to x items.
	 * @return Map<String,Integer> with sorted, the least frequent items.
	 */
	public Map<String, Integer> getLeastFrequent(final int limit) {
		if (limit > this.wordMap.size()) {
			return getLeastFrequent();
		}
		Map<String, Integer> newMap =
				new LinkedHashMap<String, Integer>(limit);
		Map<String, Integer> sortedMap =
				WordFrequency.sortByValue(this.wordMap, false);

		int inc = 0;
		Iterator<Entry<String, Integer>> it =
				sortedMap.entrySet().iterator();

		while (it.hasNext() && inc < limit) {
			Entry<String, Integer> word = it.next();
			newMap.put(word.getKey(), word.getValue());
			inc++;
		}

		return newMap;
	}
	/**
	 * <b>getLeastFrequent</b> returns the least frequent items from a Map.
	 * @return Map<String,Integer> with sorted, the least frequent items.
	 */
	public Map<String, Integer> getLeastFrequent() {
		return this.getLeastFrequent(this.wordMap.size());
	}

	/**
	 * <b>printLeastFrequent</b> prints the least frequent items from a Map.
	 * @param limit limits the Map to x items.
	 */
	public void printLeastFrequent(final int limit) {
		Map<String, Integer> sorted = this.getLeastFrequent(limit);
		for (Entry<String, Integer> word : sorted.entrySet()) {
			System.out.println("Word: " + word.getKey()
					+ " Occurences: " + word.getValue());
		}
	}
	/**
	 * <b>printMostFrequent</b> prints the most frequent items from a Map.
	 */
	public void printLeastFrequent() {
		this.printLeastFrequent(this.wordMap.size());
	}

	/**
	 * <b>generateWordCloud</b> generates an HTML file with word cloud.
	 * @param htmlFileOut is the path of the file the function prints into.
	 */
	public void generateWordCloud(final String htmlFileOut) {
		generateWordCloud(htmlFileOut, this.wordMap.size());
	}

	/**
	 * <b>generateWordCloud</b> generates an HTML file with word cloud.
	 * @param htmlFileOut is the path of the file the function prints into.
	 * @param limit limits the map to contain x items.
	 */
	public void generateWordCloud(final String htmlFileOut,
			final int limit) {
		generateWordCloud(htmlFileOut, limit,
				MIN_FONT_SIZE, MAX_FONT_SIZE, DEFAULT_RADIUS);
	}
	/**
	 * <b>generateWordCloud</b> generates an HTML file with word cloud.
	 * @param htmlFileOut is the path of the file the function prints into.
	 * @param limit limits the map to contain x items.
	 * @param minSize is the minimal size in pixels of font for a word.
	 * @param maxSize is the maximal size in pixels of font for a word.
	 * @param radius is the radius of a circle in pixels.
	 */
	public void generateWordCloud(final String htmlFileOut, final int limit,
			final int minSize, final int maxSize, final int radius) {
		Map<String, Integer> sorted = this.getMostFrequent(limit);
		int mostFrequent = this.getFirstElement(sorted).getValue();
		int leastFrequent = this.getLastElement(sorted).getValue();

		try (PrintWriter htmlOutput = new PrintWriter(htmlFileOut)) {
			htmlOutput.println("<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "	<head>\n"
				+ "		<meta charset=\"utf-8\">\n"
				+ "	<head>\n" + "	<body>");

			int fontSize, posX, posY;
			double angle, radiusSquare;

			for (Entry<String, Integer> word : sorted.entrySet()) {
				/*
				 * Explanation
				 * https://github.com/kaje11/gameDevProject/blob/master/Assets/
				 * Scripts/UserInterface/BarPrototype.cs
				 */
				fontSize =
						(word.getValue() - leastFrequent) *
						(maxSize - minSize) / (mostFrequent - leastFrequent)
						+ minSize;
				
				angle = Math.random() * Math.PI * 2;
				radiusSquare = Math.random() * radius * radius;
				
				posX = (int) Math.round(Math.sqrt(radiusSquare) * Math.cos(angle)) + radius;
				posY = (int) Math.round(Math.sqrt(radiusSquare) * Math.sin(angle)) + radius;

				htmlOutput.println("\t\t<div style=\"position:absolute;"
						+ "left: " + posX + "px; top: " + posY + "px;"
						+ "font-size: " + fontSize + "px\">" + word.getKey()
						+ "</div>");
			}

			htmlOutput.println("	</body>");
			htmlOutput.println("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// http://stackoverflow.com/a/2581754/7152879
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean desc) {
		return map.entrySet().stream()
				.sorted(desc ? Map.Entry.comparingByValue(Collections.reverseOrder()) : Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}