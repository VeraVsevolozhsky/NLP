package testexternals;

import general.porter.stemmer.Stemmer;

public class TestExternalTools {

	public static void main(String[] args) {
		try {
			testStemmer();
			
			String blabla = "This isn't the greatest example sentence in the world because I've seen better.  Neither is this one.  This one's not bad, though.";
			
			// the sentence detector and tokenizer constructors
			// take paths to their respective models
			//SentenceDetectorME sdetector =
			    //new SentenceDetector(Constants.SENTENCE_DETECTOR_MODEL);
			//Tokenizer tokenizer = new Tokenizer(Constants.TOKENIZE_MODEL);
			 
			/*
			// the parser takes the path to the parser models
			// directory and a few other options
			boolean useTagDict = true;
			boolean useCaseInsensitiveTagDict = false;
			int beamSize = ParserME.defaultBeamSize;
			double advancePercentage = ParserME.defaultAdvancePercentage;
			ParserME parser = TreebankParser.getParser(
			        "models/parser", useTagDict, useCaseInsensitiveTagDict,
			        beamSize, advancePercentage);
			        */
			 
			// break a paragraph into sentences
			/*String[] sents = sdetector.sentDetect(blabla);
			for (String sentence : sents) {
				System.out.println(sentence);
			}*/
			
			//String[] tokens = tokenizer.tokenize(blabla);
			//for (String token : tokens) {
//				System.out.print(token + " ");
			//}
			
			
			/*
			Parse parse;
			MaxentModel model = new GISModel(_params, predLabels, _ocNames, _correctionConstant, _correctionParam);
			Tokenizer tokenizer = new Tokenizer("data" + File.separator + "lady_susan.txt");
			tokenizer.tokenize(s);
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void testStemmer() {
		Stemmer stemmer = new Stemmer();
		String words[] = "hello hellos you your yours do doing does done car cars eggs deny determinate determine".split(" ");
		for (String word : words) {
			stemmer.add(word.toCharArray(), word.length());
			stemmer.stem();
			System.out.println("Orignial: " + word + ", Stemmed: " + stemmer.toString());				
		}		
	}

}
