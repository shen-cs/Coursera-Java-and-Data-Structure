
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
     Location loc;
     double maxDist;
     public DistanceFilter(Location place, double max) {
         loc = place;
         maxDist = max;
     }
     public String getName() {
         return "Distance";
     }
     public boolean satisfies(QuakeEntry qe) {
         return loc.distanceTo(qe.getLocation()) <= maxDist;
     }
}
