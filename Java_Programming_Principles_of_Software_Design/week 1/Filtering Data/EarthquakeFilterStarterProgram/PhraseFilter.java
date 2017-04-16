
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
     String phrase;
     String request;
     public PhraseFilter(String phr, String req) {
        phrase = phr;
        request = req;
     }
     public String getName() {
        return "Phrase";
     }
     public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        switch(request) {
           case "start":
           return title.startsWith(phrase);
           case "end":
           return title.endsWith(phrase);
           case "any":
           return title.contains(phrase);
           default:
           return true;
        }
     }
}
