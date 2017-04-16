
/**
 * Write a description of WebLinkFinder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class WebLinkFinder {
        public void findURL() {
          URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
          for(String w : ur.words()) {
            String wl = w.toLowerCase();
              if(wl.startsWith("href") && wl.indexOf("youtube.com") != -1) {
                int start = w.indexOf("\"") + 1;
                int stop = w. indexOf("\"", wl.indexOf("youtube.com"));
                String link = w.substring(start, stop);
                System.out.println(link);
            }
            }
        }
}
