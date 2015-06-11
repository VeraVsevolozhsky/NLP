package general.words;

import general.porter.stemmer.Stemmer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsParser {

	private List<String> m_words;
	private Map<String, Boolean> m_stopWords;
	private Stemmer m_stemmer = new Stemmer();
	
	public WordsParser(String stopWordsFile) throws IOException {
		if (! new File(stopWordsFile).isFile()) {
			throw new IOException("can not read stop words file " + stopWordsFile);
		}
		m_stopWords = new HashMap<String, Boolean>();
		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			fileReader = new FileReader(stopWordsFile);
			reader = new BufferedReader(fileReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				m_stopWords.put(line.toLowerCase(), Boolean.TRUE);
			}
		}
		finally {
			if (reader != null) {
				try {					
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<String> parseFile(String filename, boolean stem) throws IOException {
		if (! new File(filename).isFile()) {
			throw new IOException("can not read file " + filename);
		}
		m_words = new ArrayList<String>();
		FileReader fileReader = null;
		BufferedReader reader = null;
		//int counter = 0;
		try {
			fileReader = new FileReader(filename);
			reader = new BufferedReader(fileReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				addTextToTokens(line, m_words, stem);
				//counter++;
				//if (counter%50 == 0 || counter < 10) {
				//	System.out.println("parsed " + counter + " lines");
				//}
				
				//TODO - remove this code
				//if (counter == 200) break;
			}
			
			return m_words;
		}
		finally {
			if (reader != null) {
				try {					
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<String> tokenizeText(String text, boolean stem) throws IOException {
		List<String> tokens = new ArrayList<String>();
		addTextToTokens(text, tokens, stem);
		return tokens;
	}
	
	private void addTextToTokens(String text, List<String> tokens, boolean stem) throws IOException {
		// to lower case
		text = text.toLowerCase();
		
		// tokenize words with tokenizer
		//Tokenizer tokenizer = new Tokenizer(Constants.TOKENIZE_MODEL);
		//String[] strings = tokenizer.tokenize(text);
		//String[] strings = text.split("[ \\t\\n\\x0B\\f\\r[^a-z]]");
		String[] strings = text.split("[^a-z']");
		
		for (String str : strings) {
			if (! str.matches("[a-z']*")) {
				System.out.println("problem with " + str);
				System.exit(1);
			}
			
			// skip empty words
			if (str.length() == 0) {
				continue;
			}
			
			if (str.equals("'")) {
				continue;
			}
			
			// don't add tags
			if (str.startsWith("<") || str.endsWith(">")) {
				continue;
			}			
			if (str.startsWith("{") || str.endsWith("}")) {
				continue;
			}
			
			// don't add stop words
			if (m_stopWords.containsKey(str)) {
				continue;
			}
			
			// stem
			if (stem) {
				m_stemmer.add(str.toCharArray(), str.length());
				m_stemmer.stem();
				str = m_stemmer.toString();
			}
			
			// add token
			tokens.add(str);
		}
	}
}
