
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k = 0; k < 3; k++){
			String st = markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov(int seed) {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 5000;
		
        /*MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovModel mFour = new MarkovModel(4);
        runModel(mFour, st, size, seed);
        */
        EfficientMarkovModel eMfive = new EfficientMarkovModel(5);
        runModel(eMfive, st, size, seed);
        eMfive.printHashMapInfo();
    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
	public void testMap() {
	    String train = "yes-this-is-a-thin-pretty-pink-thistle";
	    int size = 50;
	    int seed = 42;
	    EfficientMarkovModel mFive = new EfficientMarkovModel(2);
	    runModel(mFive, train, size, seed);
	    mFive.printHashMapInfo();
	}
}
