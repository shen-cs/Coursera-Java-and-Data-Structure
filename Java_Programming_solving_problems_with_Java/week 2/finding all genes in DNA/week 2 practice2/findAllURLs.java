
/**
 * Write a description of findAllURLs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class findAllURLs {
   public StorageResource findURLs(String url) {
       URLResource ur = new URLResource(url);
       StorageResource urls = new StorageResource();
       int start, stop;
       for(String w : ur.lines()){
        if(w.contains("href")) {
           start = w.indexOf("\"", w.indexOf("=")) + 1;
           stop = w.indexOf("\"", start);
           urls.add(w.substring(start, stop));
        } 
       }
       return urls;
    }
   public void testURLWithStorage() {
       StorageResource urls = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
       int numOfurl = 0;
       int numOfhttps = 0;
       int numOfcom = 0;
       int numOfcom2 = 0;
       int numOfDot = 0;
       for(String url : urls.data()) {
           int loc = 0;
           int start = 0;
           System.out.println(url);
           numOfurl++;
           if(url.startsWith("https")) {
           numOfhttps++; 
           }
           if(url.contains(".com") ) {
           numOfcom++; 
           }
           if(url.endsWith(".com") || url.endsWith(".com/")) {
           numOfcom2++; 
           }
           while(true) {
             loc = url.indexOf(".", start);
             if(loc == -1) {
             break;
             }
             numOfDot++;
             start = loc + 1;
           }
        }
       System.out.println("The number of urls is " + numOfurl);
       System.out.println("The number of secured urls is " + numOfhttps);
       System.out.println("The number of urls containing .com is " + numOfcom);
       System.out.println("The number of urls ending with .com and .com/ is " + numOfcom2);
       System.out.println("The number of dots is " + numOfDot);
       System.out.println("--------------------------------------------------------------");
    }
   public void test() {
    FileResource ur = new FileResource();
    for(String w : ur.lines()) {
       System.out.println(w);
    }   
    System.out.println("--------------------------------------------------------------");
   }
   public void test2() {
    FileResource ur = new FileResource();
    for(String w : ur.words()) {
       System.out.println(w);
    }   
     System.out.println("--------------------------------------------------------------");
   }
   public void test3() {
      URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
      int num = 0;
      int numOfcom = num;
      for(String w : ur.words()) {
          if(w.contains("href")) {
            num++;
           }
          if(w.contains("href") && w.contains(".com")) {
            numOfcom++;
            }
      }
      System.out.println("Number of urls is " + num);
      System.out.println("Number of urls with .com is " + numOfcom);
      System.out.println("--------------------------------------------------------------");
   }
}
