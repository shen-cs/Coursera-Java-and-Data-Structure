
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CharactersInPlay {
     private ArrayList<String> names = new ArrayList<String>();
     private ArrayList<Integer> counts = new ArrayList<Integer>();
     private void update(String person) {
        int index = names.indexOf(person);
        if(index == -1) {
            names.add(person);
            counts.add(1);
        }
        else {
           counts.set(index, counts.get(index) + 1);
        }
     }
     private void findAllCharacters() {
        names.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()) {
           if(line.indexOf('.') != -1) {
               String person = line.substring(0, line.indexOf('.'));
               update(person);
           }
        }
     }
     private void charactersWithNumParts(int num1, int num2) {
        for(int i = 0; i < names.size(); i++) {
            if(num1 <= counts.get(i) && counts.get(i) <= num2) {
               System.out.println("Number between " + num1 + " and " + num2 + " :");
               System.out.println(names.get(i) + " : " + counts.get(i));
            }
        }
        System.out.println("-------------------------------");
     }
     public void tester() {
        findAllCharacters();
        for(int i = 0; i < names.size(); i++) {
           if(counts.get(i) > 1) {
              System.out.println(names.get(i) + " : " + counts.get(i));
           }
        }
        System.out.println("-------------------------------");
        charactersWithNumParts(10, 15);
     }
}
