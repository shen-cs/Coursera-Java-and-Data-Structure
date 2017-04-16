
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovModel extends AbstractMarkovModel {
	private int preN;
	public MarkovModel(int n) {
		myRandom = new Random();
		preN = n;
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - preN);
		String key = myText.substring(index, index + preN);
		sb.append(key);
		for(int k = 0; k < numChars - preN; k++){
			ArrayList<String> follows = getFollows(key);
			index = myRandom.nextInt(follows.size());
			sb.append(follows.get(index));
			key = key.substring(1) + follows.get(index);
		}
		
		return sb.toString();
	}
	
	public String toString() {
	   return "MarcovModel of order " + preN;
	}
}

