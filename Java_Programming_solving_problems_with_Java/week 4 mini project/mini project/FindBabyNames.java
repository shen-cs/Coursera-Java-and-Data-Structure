
/**
 * Write a description of FindBabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;
import org.apache.commons.csv.*;
public class FindBabyNames {
      public void totalBirths(FileResource fr) {
          int totalBirths = 0, totalBoys = 0,totalGirls = 0;
          int totalNames = 0, boysNames = 0, girlsNames = 0;
          for(CSVRecord row : fr.getCSVParser(false)) {
              totalNames++;   
              int newBorn = Integer.parseInt(row.get(2));
              totalBirths += newBorn;
              if(row.get(1).equals("F")) {
                  totalGirls += newBorn;
                  girlsNames++;
              }
              else {
                  totalBoys += newBorn;
                  boysNames++;
              }
          }
          System.out.println("Total births = " + totalBirths + ". Total names = " + totalNames);
          System.out.println("Total boys = " + totalBoys + ". Total boy names = " + boysNames);
          System.out.println("Total girls = " + totalGirls + ". Total girl names = " + girlsNames);
          System.out.println("-----------------------------------------------------");
      }
      public void testTotalBirths() {
          FileResource fr = new FileResource();
          totalBirths(fr);
      }
      public int getRank(int year, String name, String gender) {
         gender = gender.toUpperCase();
         String fName = "yob" + year + "m.csv";
         FileResource fr = new FileResource(fName);
         int rank = 1;
         int num = 0;
         for(CSVRecord row : fr.getCSVParser(false)) {
             if(row.get(1).equals(gender)) {
                 if(row.get(0).equals(name)) {
                   rank = 1;
                   num = Integer.parseInt(row.get(2));
                   break;
                }
                else {
                   rank = -1;
                }
             }
         }
         if(rank != -1) {
            for(CSVRecord row : fr.getCSVParser(false)) {
               if(row.get(1).equals(gender)) {
                   int num2com = Integer.parseInt(row.get(2));
                   if(num < num2com) {
                      rank++;
                   }
               }
            }   
         } 
         return rank;
      }
      public String getName(int year, int rank, String gender) {
          gender = gender.toUpperCase();
          String fName = "yob" + year + "m.csv";
          FileResource fr = new FileResource(fName);
          for(CSVRecord row : fr.getCSVParser(false)) {
              int rk = 1;
              String name = row.get(0);
              if(row.get(1).equals(gender)) {
                 for(CSVRecord rec : fr.getCSVParser(false)) {
                    if(rec.get(1).equals(gender) && !rec.get(0).equals(name)) {
                       rk++;
                        //System.out.println(rk);
                    }
                    else if(rec.get(0).equals(name)) {
                       break;
                    }
                 }
                 if(rk == rank) {
                    return row.get(0);
                 }
              }
              //System.out.println(rk);
          }
          return "NO NAME";
      }
      public void whatIsNameInYear(String name, int year, int newYear, String gender) {
          int rk = getRank(year, name, gender);
          String newName = getName(newYear, rk, gender);
          gender = gender.toUpperCase();
          if(gender.equals("F")) {
             System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
          }
          else {
             System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear);
          }
          System.out.println("-----------------------------------------------------");
      }
      public int yearOfHighestRank(String name, String gender) {
         DirectoryResource dr = new DirectoryResource();
         int highestYr = 0, highestRk = 0;
         for(File f : dr.selectedFiles()) {
            String fName = f.getName();
            int loc = 0;
            while(true) {
               if(fName.substring(loc,loc+1).equals("1") || fName.substring(loc,loc+1).equals("2")) {
                   break;
               }
               loc++;
            }
            int year = Integer.parseInt(fName.substring(loc, loc + 4));
            int rk = getRank(year, name, gender);
            if(rk == -1) {
              continue;
            }
            if(highestRk == 0) {
               highestRk = rk ;
               highestYr = year;
            }
            else if(highestRk > rk) {
               highestRk = rk ;
               highestYr = year;
            }
         }
         if(highestRk == 0) {
             return -1;
         }
         return highestYr;
      }
      public double getAverageRank(String name, String gender) {
         DirectoryResource dr = new DirectoryResource();
         int totalRk = 0;
         double num = 0.0;
         for(File f : dr.selectedFiles()) {
            String fName = f.getName();
            int loc = 0;
            while(true) {
               if(fName.substring(loc,loc+1).equals("1") || fName.substring(loc,loc+1).equals("2")) {
                   break;
               }
               loc++;
            }
            int year = Integer.parseInt(fName.substring(loc, loc + 4));
            int rk = getRank(year, name, gender);
            if(rk == -1) {
              continue;
            }
            totalRk += rk; 
            num++;
         }
         return totalRk / num;
      }
      public int getTotalBirthsRankedHigher(String name, int year, String gender) {
         gender = gender.toUpperCase();
         String fName = "yob" + year + "m.csv";
         FileResource fr = new FileResource(fName);
         int total = 0;
         int rk = getRank(year, name, gender);
         for(CSVRecord row : fr.getCSVParser(false)) {
              int rk2com = getRank(year, row.get(0), gender);
              if(rk > rk2com && rk2com != -1)  {
                 total += Integer.parseInt(row.get(2));
              }
         }
         return total;
      }
}

