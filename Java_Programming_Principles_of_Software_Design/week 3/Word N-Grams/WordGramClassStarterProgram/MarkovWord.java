
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }
    private int indexOf(String[] words, WordGram target, int start) {
        for(int i = start; i < words.length - target.length() + 1; i++) {
            WordGram wg = new WordGram(words, i, target.length());
            if(wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
	    while(true) {
	        int index = indexOf(myText, kGram, pos);
	        pos = index + kGram.length();
	        if(index == -1 || pos >= myText.length- 1) {
	            break;
	        }
	        follows.add(myText[pos]);
	    }
	    return follows;
    }
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString());
        sb.append(" ");
        for(int i = 0; i < numWords - myOrder; i++) {
            ArrayList<String> follows = getFollows(kGram);
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            sb.append(" ");
            kGram = kGram.shiftAdd(follows.get(index));
        }
        return sb.toString().trim();
    }
    public String toString() {
        return " MarkovWordModel " + myOrder;
    }
}
