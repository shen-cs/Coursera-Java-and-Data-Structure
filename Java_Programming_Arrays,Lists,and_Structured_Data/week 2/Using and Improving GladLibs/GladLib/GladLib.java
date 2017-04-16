import edu.duke.*;
import java.util.*;

public class GladLib {
    private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> used = new ArrayList<String>();
	private int wordsReplaced = 0;
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
	    String[] labels = {"adjective", "noun", "color", "country", "name" , "animal", 
	                       "timeframe", "verb", "fruit"};
	    for(String label : labels) {
	       String fName = source + "/" + label + ".txt";
	       map.put(label, readIt(fName));
	    }
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {		     
		if (label.equals("number")){
			return "" + myRandom.nextInt(50) + 5;
		}
		return randomFrom(map.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0, first);
		String suffix = w.substring(last + 1);
		String sub = getSubstitute(w.substring(first + 1,last));
		while(used.contains(sub)) {
		    sub = getSubstitute(w.substring(first + 1,last));
		}
		used.add(sub);
		wordsReplaced++;
		return prefix + sub + suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w + " ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
	    used.clear();
	    wordsReplaced = 0;
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 50);
		System.out.println();
		System.out.println("Number of words replaced: " + wordsReplaced);
		System.out.println("----------------------------------------");
	}
	


}
