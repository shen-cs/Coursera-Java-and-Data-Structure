
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int preN;
    private HashMap<String, ArrayList<String>> map;
	public EfficientMarkovModel(int n) {
		myRandom = new Random();
		preN = n;
		map = new HashMap<String, ArrayList<String>>();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - preN);
		String key = myText.substring(index, index + preN);
		sb.append(key);
		for(int k = 0; k < numChars - preN; k++) {
			ArrayList<String> follows = getFollows(key);
			if(follows.size() > 0) {
        		index = myRandom.nextInt(follows.size());
        		sb.append(follows.get(index));
        		key = key.substring(1) + follows.get(index);
            }
		}

		return sb.toString();
	}
	
	public void buildMap(String key) {
	   ArrayList<String> follows = oldGetFollows(key);
       map.put(key, follows);
	}
	
	public ArrayList<String> oldGetFollows(String key) {
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
	
	public ArrayList<String> getFollows(String key) {
	    if(!map.containsKey(key)) {
	       buildMap(key); 
	    }
	    return map.get(key);
	}
	
	public void printHashMapInfo() {
	   //System.out.println(map);
	   int max = 0;
	   String maxKey = "";
	   for(String key : map.keySet()) {
	      if(max < map.get(key).size()) {
	         max = map.get(key).size();
	         maxKey = key;
	      }
	   }
	   ArrayList<String> maxValue = new ArrayList<String>();
	   for(String key : map.keySet()) {
	      if(map.get(maxKey).size() == map.get(key).size()) {
	         maxValue.add(key);
	      }
	   }
	   System.out.println("# of keys: " + map.size());
	   System.out.println("Largest follows: " + map.get(maxKey).size());
	   System.out.printf("Keys having maximum size value: %s \n", maxValue);
	}
	
	public String toString() {
	   return "EfficientMarcovModel of order " + preN;
	}
}
