
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    protected ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    while(true) {
	        int index = myText.indexOf(key, pos);
	        pos = index + key.length();
	        if(index == -1 || pos >= myText.length()) {
	            break;
	        }
	        follows.add(myText.substring(pos, pos + 1));
	    }
	    return follows;
	} 
	
    abstract public String getRandomText(int numChars);

}
