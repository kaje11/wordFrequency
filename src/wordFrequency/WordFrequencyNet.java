package wordFrequency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * <h1>WordFrequencyNet</h1>
 * This class is called if user provided URL for WordFrequency counter.
 * <p>It implements the parseFile function.
 * @author kaje11
 * @version 0.1
 *
 */
public class WordFrequencyNet extends WordFrequency {
	/**
	 * <b>WordFrequencyNet</b> constructor calls the parent constructor.
	 * @param path is the URL of the file.
	 */
	WordFrequencyNet(final String path) {
		super(path);
	}

	@Override
	final
	void parseFile() {
		URL url;
		try {
			url = new URL(this.path);

			InputStream is = url.openStream();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(is));

			String line;
			while ((line = br.readLine()) != null) {
				this.parseLine(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
