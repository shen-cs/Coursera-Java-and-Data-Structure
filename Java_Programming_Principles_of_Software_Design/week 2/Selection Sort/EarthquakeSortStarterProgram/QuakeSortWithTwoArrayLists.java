import java.util.*;
import edu.duke.*;

public class QuakeSortWithTwoArrayLists {
    // This is the code from the Video of Selection Sort with Two ArrayLists
    
    public QuakeSortWithTwoArrayLists() {
        // TODO Auto-generated constructor stub
    }
   
    private void swap(ArrayList<QuakeEntry> list, int idx, int min) {
        QuakeEntry hold = list.get(idx);
        QuakeEntry hold2 = list.get(min);
        list.set(idx, hold2);
        list.set(min, hold);
    }
    private int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int start) {
        int minIdx = start;
        for (int j = start + 1; j < quakes.size(); j++) {
            if (quakes.get(j).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = j;
            }
        }
        return minIdx;
    }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < in.size() - 1; i++) {
            int min = getSmallestMagnitude(in, i);
            swap(in, i, min);
        }
        return in;
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        list = sortByMagnitude(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();     
       // String source = "data/nov20quakedatasmall.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
