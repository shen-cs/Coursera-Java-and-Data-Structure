import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location Denver = new Location(39.7392, -104.9903);
        Filter f = new DistanceFilter(Denver, 1000000); 
        //Filter f = new DepthFilter(-4000.0, -2000.0);
        //Filter f = new PhraseFilter("a", "end");
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        //ArrayList<QuakeEntry> m = filter(m7, f);
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println("#: " + m7.size()); 
        System.out.println("-----------------------------------------------------------");
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location Denver = new Location(55.7308, 9.1153);
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MinMagFilter(0.0, 5.0));
        //maf.addFilter(new DepthFilter(-180000.0, -30000.0));
        maf.addFilter(new PhraseFilter("e", "any"));
        maf.addFilter(new DistanceFilter(Denver, 3000000));
        ArrayList<QuakeEntry> quake = filter(list, maf);
        for(QuakeEntry qe : quake) {
            System.out.println(qe);
        }
        System.out.println("#: " + quake.size()); 
        System.out.println("Filters used are: " + maf.getName());
        System.out.println("-----------------------------------------------------------");
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
