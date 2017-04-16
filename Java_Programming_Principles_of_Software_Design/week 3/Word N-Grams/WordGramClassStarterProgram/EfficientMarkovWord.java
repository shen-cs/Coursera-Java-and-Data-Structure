
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private HashMap<WordGram, ArrayList<String>> map;
    private int myOrder;
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        map = new HashMap<WordGram, ArrayList<String>>();
    }
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }
    public int indexOf(String[] words, WordGram target, int start) {
        for(int i = start; i < words.length - target.length() + 1; i++) {
            WordGram wg = new WordGram(words, i, target.length());
            if(wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }
    private void buildMap() {
        for(int i = 0; i < myText.length - myOrder + 1; i++) {
            ArrayList<String> follows = new ArrayList<String>();
            WordGram kGram = new WordGram(myText, i, myOrder);
            if(!map.containsKey(kGram)) {
                follows = oldGetFollows(kGram);
                map.put(kGram, follows);
            }
        }
    }
    private ArrayList<String> oldGetFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
	    while(true) {
	        int index = indexOf(myText, kGram, pos);
	        pos = index + kGram.length();
	        if(index == -1 || pos >= myText.length ) {
	            break;
	        }
	        follows.add(myText[pos]);
	    }
	    return follows;
    }
    private ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);
    }
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString());
        sb.append(" ");
        buildMap();
        for(int i = 0; i < numWords - myOrder; i++) {
            ArrayList<String> follows = getFollows(kGram);
            if(follows.size() > 0) {
                index = myRandom.nextInt(follows.size());
                sb.append(follows.get(index));
                sb.append(" ");
                kGram = kGram.shiftAdd(follows.get(index));
            }
        }
        return sb.toString().trim();
    }
    public void printHashMapInfo() {
	   //System.out.println(map);
	   int max = 0;
	   WordGram maxKey = null;
	   for(WordGram key : map.keySet()) {
	      if(max < map.get(key).size()) {
	         max = map.get(key).size();
	         maxKey = key;
	      }
	   }
	   ArrayList<WordGram> maxValue = new ArrayList<WordGram>();
	   for(WordGram key : map.keySet()) {
	      if(map.get(maxKey).size() == map.get(key).size()) {
	         maxValue.add(key);
	      }
	   }
	   System.out.println("# of keys: " + map.size());
	   System.out.println("Largest follows: " + map.get(maxKey).size());
	   System.out.printf("Keys having maximum size value: %s \n", maxValue);
	}
    public String toString() {
        return "EfficientMarkovWordModel " + myOrder;
    }
}
