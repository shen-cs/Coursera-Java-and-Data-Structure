
/**
 * Write a description of parsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class parsingWeatherData {
     public CSVRecord coldestHourInFile(CSVParser parser) {
         CSVRecord coldest = null;  
         for(CSVRecord record : parser) {
             if(record.get("TemperatureF").equals("-9999")) {
                 continue;
                } 
             coldest = getLowestOfTwo(coldest, record, "TemperatureF");
            }
            return coldest;
        }
     public void testColdestHourInFile() {
          FileResource fr = new FileResource();
          CSVParser parser = fr.getCSVParser();
          CSVRecord record = coldestHourInFile(parser);
          System.out.println("The coldest tempereture was " + record.get("TemperatureF") + " at " + record.get("DateUTC"));
          System.out.println("-------------------------------------------------------");
        }
     public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldest = null;
        String coldestFileName = "";
        for(File f : dr.selectedFiles()) {
           FileResource fr = new FileResource(f);
           CSVRecord record = coldestHourInFile(fr.getCSVParser());
           if(coldest == null) {
                  coldest = record;
                  coldestFileName = f.getName();
                }
               else if(Double.parseDouble(coldest.get("TemperatureF")) >  Double.parseDouble(record.get("TemperatureF"))) {
                  coldest = record;
                  coldestFileName = f.getName();
                }
        }
        return coldestFileName;
        }
     public CSVRecord getLowestOfTwo(CSVRecord lowest, CSVRecord record, String row) {
          if(lowest == null) {
                  lowest = record;
                }
          else if(Double.parseDouble(lowest.get(row)) >  Double.parseDouble(record.get(row))) {
                  lowest = record;
                }
          return lowest;
        } 
     public void testFileWithColdestTemperature() {
          String name = fileWithColdestTemperature(); 
          System.out.println(name);
          FileResource fr = new FileResource(name);
          CSVParser parser = fr.getCSVParser();
          String coldestTemp = coldestHourInFile(parser).get("TemperatureF");
          System.out.println("Coldest day was in file " + name);
          System.out.println("Coldest temperature on that day was " + coldestTemp);
          System.out.println("All the temperatures on coldest day were: ");
          parser = fr.getCSVParser();
          for(CSVRecord record : parser) {
              System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
            }
          System.out.println("-------------------------------------------------------");
        }
     public CSVRecord lowestHumidityInFile(CSVParser parser) {
            CSVRecord lowest = null;  
         for(CSVRecord record : parser) {
             if(record.get("Humidity").equals("N/A")) {
                continue;
                }
              lowest = getLowestOfTwo(lowest, record, "Humidity");
            }
            return lowest;
        }
     public void testLowestHumidityInFile() {
          FileResource fr = new FileResource();
          CSVParser parser = fr.getCSVParser();
          CSVRecord record = lowestHumidityInFile(parser);
          System.out.println("The lowest humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
          System.out.println("-------------------------------------------------------");
        }
     public CSVRecord lowestHumidityInManyFiles() {
          DirectoryResource dr = new DirectoryResource();
          CSVRecord lowest = null;
          for(File f : dr.selectedFiles()) {
              FileResource fr = new FileResource(f);
              CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
              lowest = getLowestOfTwo(lowest, record, "Humidity");
            }
          return lowest;
        }
     public void testLowestHumidityInManyFiles() {
          CSVRecord lowest = lowestHumidityInManyFiles();
          System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
          System.out.println("-------------------------------------------------------");
        }
     public double averageTemperatureInFile(CSVParser parser) {
          double total = 0;
          int rowNum = 0;
          for(CSVRecord row : parser) {
              total += Double.parseDouble(row.get("TemperatureF"));
              rowNum++;
            }
          return total / rowNum ;
        }
     public void testAverageTemperatureInFile() {
         FileResource fr = new FileResource();
         CSVParser parser = fr.getCSVParser();
         System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
         System.out.println("-------------------------------------------------------");
        }
     public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
         double totalTemp = 0;
         int rowNum = 0;
         for(CSVRecord row : parser) {
              if(Double.parseDouble(row.get("Humidity")) >= value) {
                  totalTemp += Double.parseDouble(row.get("TemperatureF"));
                  rowNum++;
                }
            }
         return totalTemp / rowNum;
        }
     public void testAverageTemperatureWithHighHumidityInFile(int value) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser,value);
        if(avgTemp > 0) {
        System.out.println("Average temperature with humidity " + value + " is " + avgTemp);
        }
        else {
        System.out.println("No temperature with that humidity");
        }
        System.out.println("-------------------------------------------------------");
    }
}