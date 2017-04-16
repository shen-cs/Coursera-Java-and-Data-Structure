
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class WordsInFiles {
     private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
     private void addWordsFromFile(File f) {
         FileResource fr = new FileResource(f);
         for(String w : fr.words()) {
            if(map.containsKey(w)) {
                 if(!map.get(w).contains(f.getName())) {
                     map.get(w).add(f.getName());
                 }
            }
            else {
               ArrayList<String> nameList = new ArrayList<String>();
               nameList.add(f.getName());
               map.put(w,nameList);
            }
         }
     }
     private void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
     }
     private int maxNumber() {
        int max = 0;
        for(String w : map.keySet()) {
            if(max < map.get(w).size()) {
               max = map.get(w).size();
            }
        }
        return max;
     }
     private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for(String w : map.keySet()) {
            if(number == map.get(w).size()) {
                words.add(w);
            }
        }
        return words;
     }
     private void printFilesIn(String word) {
        for(String fName : map.get(word)) {
            System.out.println(fName);
        }
     }
     public void tester() {
        buildWordFileMap();
        /*for(String maxWord : wordsInNumFiles(maxNumber())) {
            System.out.println(maxWord);
            printFilesIn(maxWord);
            System.out.println("-------------------------------------");
        }*/
        System.out.println(wordsInNumFiles(7).size());
        System.out.println(wordsInNumFiles(4).size());
        printFilesIn("sea");
        System.out.println("------------------------------------");
        printFilesIn("tree");
     }
}
