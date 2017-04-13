package wordFrequency;

import java.io.IOException;
/**
 * WordFrequencyFactory.
 * This class identifies NET or LOCAL path of the file.
 * @author kaje11
 * @version 0.1
 */
public class WordFrequencyFactory {
	/**
	 * <b>openFile</b> identifies the path and parses the file.
	 * @param path of the file - either URL or LOCAL.
	 * @return WordFrequency handler
	 * @throws IOException when the file is not found.
	 * @throws Exception when no words are found.
	 */
	public WordFrequency openFile( String path ) throws IOException, Exception {
		if (path == null) {
			throw new IOException("Wrong path");
		}

		String s = path.trim().toLowerCase();

		WordFrequency handler = null;

		if (s.startsWith("http://") || s.startsWith("https://")) {
			handler = new WordFrequencyNet(path);
		} else {
			handler = new WordFrequencyLocal(path);
		}

		handler.parseFile();
		if (handler.getTotalWords() == 0){
			throw new Exception("Could not find any words in file");
		}

		return handler;
	}
}
