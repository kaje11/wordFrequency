package wordFrequency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <h1>WordFrequencyLocal</h1>
 * This class is called if user provided LOCAL file for WordFrequency counter.
 * <p>It implements the parseFile function.
 * @author kaje11
 * @version 0.1
 *
 */
public class WordFrequencyLocal extends WordFrequency {
	/**
	 * <b>WordFrequencyNet</b> constructor calls the parent constructor.
	 * @param path is the LOCAL path of the file.
	 */
	WordFrequencyLocal(final String path) {
		super(path);
	}

	@Override
	final
	void parseFile() {
		try (BufferedReader br =
				new BufferedReader(new FileReader(path))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       this.parseLine(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
