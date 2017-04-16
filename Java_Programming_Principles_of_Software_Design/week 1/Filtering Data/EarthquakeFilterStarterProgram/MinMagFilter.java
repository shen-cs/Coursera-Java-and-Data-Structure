
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    private double magMin; 
    private double magMax;
    public MinMagFilter(double min, double max) { 
        magMin = min;
        magMax = max;
    } 

    public String getName() {
        return "Magnitude";
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax; 
    } 

}
