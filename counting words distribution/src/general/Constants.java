package general;

import java.io.File;

public interface Constants {

	static final String DATA_DIR = "data"; 
	static final String STOP_WORDS_FILE = "stop_words_sorted.txt";
	
	static final String MODELS_DIR = "opennlp_models";
	static final String TOKENIZE_MODEL = MODELS_DIR + File.separator + "EnglishTok.bin.gz";
	
	static final String SENTENCE_DETECTOR_MODEL = MODELS_DIR + File.separator + "EnglishSD.bin.gz";
}
