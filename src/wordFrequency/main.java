/**
 * 
 */
package wordFrequency;

import java.io.IOException;
/**
 * @author kaje11
 * @version 0.1
 *
 */
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//EXAMPLE:
		WordFrequencyFactory wordFrequencyFactory = 
				new WordFrequencyFactory();
		
		try {
			WordFrequency wordFrequency = wordFrequencyFactory.openFile( "examples/example.txt" );
			System.out.println( "Generating an example of cloud map." );
			wordFrequency.generateWordCloud("examples/cloud.html", 400);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
