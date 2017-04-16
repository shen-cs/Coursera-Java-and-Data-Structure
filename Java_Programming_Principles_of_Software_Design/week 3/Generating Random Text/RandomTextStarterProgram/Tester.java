
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class Tester {
     public void testGetFollows(String s) {
         MarkovOne markov = new MarkovOne();
         String test = "this is a test yes a test.";
         markov.setTraining(test);
         ArrayList<String> follows = markov.getFollows(s);
         System.out.println(follows);
         System.out.println(follows.size());
     }
     public void testGetFollowsWithFile(String s) {
         FileResource fr = new FileResource();
         MarkovOne markov = new MarkovOne();
         markov.setTraining(fr.asString());
         ArrayList<String> follows = markov.getFollows(s);
         System.out.println(follows);
         System.out.println(follows.size());
     }
}
