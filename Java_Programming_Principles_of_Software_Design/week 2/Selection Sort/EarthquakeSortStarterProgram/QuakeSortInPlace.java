
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;
public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    private void swap(ArrayList<QuakeEntry> list, int idx, int min) {
        QuakeEntry hold = list.get(idx);
        QuakeEntry hold2 = list.get(min);
        list.set(idx, hold2);
        list.set(min, hold);
    }
    
    private int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    private int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    private void sortByLargestDepth(ArrayList<QuakeEntry> in) {
       
       for (int i = 0; i < in.size(); i++) {
            int maxIdx = getLargestDepth(in,i);
            swap(in, i, maxIdx);
        }
        
    }
    
    private int sortByMagnitude(ArrayList<QuakeEntry> in) {
        int i;
        for (i = 0; i < in.size(); i++) {
            if(isSorted(in)) {
                return i;
            }
            int minIdx = getSmallestMagnitude(in,i);
            swap(in, i, minIdx);
        }
        return i;
    }
    
    private void onePassBubbleSort(ArrayList<QuakeEntry> quakes, int numPassed) {
        int loopTime = quakes.size() - numPassed - 1;
        for(int i = 0; i < loopTime; i++) {
            if(quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude() ) {
               swap(quakes, i, i + 1);
            }
        }
    }
    
    private int bubbleSortByMagnitude(ArrayList<QuakeEntry> in) {
        int j;
        for(j = 0; j < in.size() - 1; j++) {
            if(isSorted(in)) {
                return j;
            }
            onePassBubbleSort(in, j);
        }
        return j;
    }
    
    private boolean isSorted(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < in.size() - 1; i++) {
            if(in.get(i).getMagnitude() > in.get(i + 1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        System.out.println("#Number passed: " + bubbleSortByMagnitude(list));
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
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
