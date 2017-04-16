
/**
 * Write a description of countryExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class countryExport {
      public String countryInfo(CSVParser parser, String country) {  
          String c;
          for(CSVRecord record : parser) {
                   if(record.get(0).contains(country)) {
                       c = country + ": " + record.get(1) + ": " + record.get(2); 
                       return c;
                    }
                }
          return "NOT FOUND";
        }
      public void tester() {
          FileResource fr = new FileResource();
          CSVParser parser = fr.getCSVParser();
          System.out.println(countryInfo(parser, "Nauru"));
          parser = fr.getCSVParser();
          System.out.println("Countries that export " + "cotton " + "and " + "flowers: ");
          listExportersTwoProducts(parser, "cotton", "flowers");
          parser = fr.getCSVParser();
          System.out.println("Num of exporters(cocoa): " + numberOfExporters(parser ,"cocoa")); 
          parser = fr.getCSVParser();
          System.out.println("Exporters bigger than " + "$999,999,999,999" + " :");
          bigExporters(parser, "$999,999,999,999");
          System.out.println("-----------------------------------------------------------");
        }
      public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
          for(CSVRecord record : parser) {
               String exports = record.get("Exports");
               if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                  System.out.println(record.get("Country"));
                }
            } 
        } 
      public int numberOfExporters(CSVParser parser, String exportItem) {
           int num = 0;
           for(CSVRecord record : parser) {
              String exports = record.get("Exports");
              if(exports.contains(exportItem)){
                 num++;
                }
            }
           return num;
        }
      public void bigExporters(CSVParser parser, String amount) {
          for(CSVRecord record : parser) {
              String dollar = record.get(2);
              if(dollar.length() > amount.length()) {
                  System.out.println(record.get("Country") + " " + dollar);
                }
            }
        
        }
}
