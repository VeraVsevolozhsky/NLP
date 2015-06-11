package hws;

import general.Constants;
import general.words.WordsDistribution;
import general.words.WordsParser;
import general.words.WordsDistribution.WordInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Homework1 {
	
	public static final int NUM_OF_WORDS_TO_DISPLAY_Q_B = 30;
	public static final int NUM_OF_WORDS_TO_DISPLAY_Q_C = 50;
	
	public static void main(String[] args) {
		
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		
		try {
			WordsParser wordsParser = new WordsParser(Constants.STOP_WORDS_FILE);
			WordsDistribution wordsDistribution = new WordsDistribution();
			fileWriter = new FileWriter("question6_results.txt");
			writer = new BufferedWriter(fileWriter);
			final String austenFiles[] = {				
				"emma.txt",
				"persuasion.txt",
				"pride_and_prejudice.txt"
			};
			
			final String dostoyevskyFiles[] = {				
					"the_brothers_karamazov.txt",
					"the_idiot.txt",
					"crime_and_punishment.txt"
				};
			
			writer.write("Question 6b:");
			writer.newLine();
			writer.write("~~~~~~~~~~~");
			writer.newLine();
			writer.newLine();

			writer.write("All results are with lower case, stop words, punctuation marks removal, etc.");
			writer.newLine();
			
			boolean stem = true;
			writer.newLine();
			writer.write("Results with stemming:");
			writer.newLine();
			writer.newLine();
			
			writer.write("Austen:");
			writer.newLine();
			writer.newLine();
			for (String filename : austenFiles) {
				printFileResults(wordsParser, wordsDistribution, "austen", filename, stem, writer);
			}
			
			writer.newLine();
			writer.write("Dostoyevsky:");
			writer.newLine();
			writer.newLine();
			for (String filename : dostoyevskyFiles) {
				printFileResults(wordsParser, wordsDistribution, "dostoyevsky", filename, stem, writer);
			}			
			
			stem = false;
			writer.newLine();
			writer.write("Results without stemming:\n");
			writer.newLine();
			writer.newLine();
			
			writer.write("Austen:");
			writer.newLine();
			writer.newLine();
			for (String filename : austenFiles) {
				printFileResults(wordsParser, wordsDistribution, "austen", filename, stem, writer);
			}
			
			writer.newLine();
			writer.write("Dostoyevsky:");
			writer.newLine();
			writer.newLine();
			for (String filename : dostoyevskyFiles) {
				printFileResults(wordsParser, wordsDistribution, "dostoyevsky", filename, stem, writer);
			}
			
			writer.newLine();
			writer.newLine();
			writer.write("===========================================================================");
			writer.newLine();
			writer.newLine();
			writer.write("Question 6c:");
			writer.newLine();
			writer.write("~~~~~~~~~~~");
			writer.newLine();
			writer.newLine();
			
			stem = true;
			writer.newLine();
			writer.write("Results with stemming:");
			writer.newLine();
			writer.newLine();
			
			printPerAuthorSummary(wordsParser, wordsDistribution, "austen", austenFiles, stem, writer);
			writer.newLine();			
			printPerAuthorSummary(wordsParser, wordsDistribution, "dostoyevsky", dostoyevskyFiles, stem, writer);						
			
			stem = false;
			writer.newLine();
			writer.write("Results without stemming:\n");
			writer.newLine();
			writer.newLine();
			
			printPerAuthorSummary(wordsParser, wordsDistribution, "austen", austenFiles, stem, writer);
			writer.newLine();			
			printPerAuthorSummary(wordsParser, wordsDistribution, "dostoyevsky", dostoyevskyFiles, stem, writer);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {			
			if (writer != null) {
				try {					
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void printFileResults(WordsParser wordsParser, WordsDistribution wordsDistribution, String authorDir, String filename, boolean stem, BufferedWriter writer) throws IOException {
		writer.newLine();
		writer.write("Results for file: " + filename);
		writer.newLine();
		writer.write("~~~~~~~~~~~~~~~~");
		writer.newLine();
		writer.newLine();
		String fullFilename = Constants.DATA_DIR + File.separator + authorDir + File.separator + filename;				
		List<String> tokens = wordsParser.parseFile(fullFilename, stem);
		List<WordInfo> wordInfos = wordsDistribution.getDistributionSubListSortedByName(tokens, NUM_OF_WORDS_TO_DISPLAY_Q_B);
		for (WordInfo wordInfo : wordInfos) {
			writer.write(wordInfo.toString());
			writer.newLine();
		}
	}
	
	public static void printPerAuthorSummary(WordsParser wordsParser, WordsDistribution wordsDistribution, String authorDir, String filenames[], boolean stem, BufferedWriter writer) throws IOException {
		writer.newLine();
		writer.write("Results for author: " + authorDir);
		writer.newLine();
		writer.write("~~~~~~~~~~~~~~~~");
		writer.newLine();
		writer.newLine();
		List<String> allTokens = new ArrayList<String>();
		for (String filename : filenames) {
			String fullFilename = Constants.DATA_DIR + File.separator + authorDir + File.separator + filename;				
			List<String> tokens = wordsParser.parseFile(fullFilename, stem);
			allTokens.addAll(tokens);			
		}
		List<WordInfo> wordInfos = wordsDistribution.getDistributionSubListSortedByName(allTokens, NUM_OF_WORDS_TO_DISPLAY_Q_C);
		for (WordInfo wordInfo : wordInfos) {
			writer.write(wordInfo.toString());
			writer.newLine();
		}
		
	}

}
