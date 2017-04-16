
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter {
     private ArrayList<Filter> filters;
     public MatchAllFilter() {
         filters = new ArrayList<Filter>();
     }
     public void addFilter(Filter f) {
         filters.add(f);
     }
     public String getName() {
         StringBuilder newStg = new StringBuilder();
         for(int i = 0; i < filters.size(); i++) {
            if(i != filters.size() - 1) {
               newStg.append(filters.get(i).getName());
               newStg.append(" ");
            }
            else {
               newStg.append(filters.get(i).getName());
            }
         }
         return newStg.toString();
     }
     public boolean satisfies(QuakeEntry qe) {
         for(Filter f : filters) {
             if(!f.satisfies(qe)) {
                 return false;
             }
         }
         return true;
     }
}
