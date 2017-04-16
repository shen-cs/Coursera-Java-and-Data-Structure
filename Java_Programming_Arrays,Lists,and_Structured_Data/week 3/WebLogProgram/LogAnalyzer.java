
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile() {
         // complete method
         FileResource fr = new FileResource();
         for(String line : fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for(LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs() {
         ArrayList<String> ips = new ArrayList<String>();
         for(LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if(!ips.contains(ipAddr)) {
                ips.add(ipAddr);   
            }   
         }
         return ips.size();
     }
     public void printAllHigherThanNum(int num) {
         for(LogEntry le : records) {
            if(le.getStatusCode() > num) {
               System.out.println(le);
            }
         }
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> ips = new ArrayList<String>();
         for(LogEntry le : records) {
            String date = le.getAccessTime().toString();
            date = date.substring(4, 10);
            if(date.equals(someday) && !ips.contains(le.getIpAddress())) {
               ips.add(le.getIpAddress());
            }
         }
         return ips;
     }
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> ips = new ArrayList<String>();
         int count = 0;
         for(LogEntry le : records) {
            int code = le.getStatusCode();
            if(low <= code && code <= high && !ips.contains(le.getIpAddress())) {
               ips.add(le.getIpAddress());
               count++;
            } 
         }
         return count;
     }
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> map = new HashMap<String, Integer>();
         for(LogEntry le : records) {
             String ip = le.getIpAddress();
             if(map.containsKey(ip)) {
                map.put(ip, map.get(ip) + 1);   
             } 
             else {
                map.put(ip, 1);   
             }
         }
         return map;
     }
     public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
         int max = 0;
         for(String ip : map.keySet()) {
            if(max < map.get(ip)) {
               max = map.get(ip);
            }   
         }
         return max;
     }
     public ArrayList<String> IPsMostVisits(HashMap<String, Integer> map) {
         ArrayList<String> ips = new ArrayList<String>();
         int max = mostNumberVisitsByIP(map);
         for(String ip : map.keySet()) {
            if(map.get(ip) == max) {
               ips.add(ip);
            }
         }
         return ips;
     }
     public HashMap<String, ArrayList<String>> IPsForDays() {
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records) {
            ArrayList<String> ips = new ArrayList<String>();
            String date = le.getAccessTime().toString().substring(4, 10);
            if(!map.containsKey(date)) {
               ips.add(le.getIpAddress());
               map.put(date, ips);
            }
            else {
               map.get(date).add(le.getIpAddress());
            }
         }
         return map;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
         String maxDay = "";
         int maxSize = 0;
         for(String date : map.keySet()) {
            if(maxSize <= map.get(date).size()) {
               maxDay = date;
               maxSize = map.get(date).size();
            }
         }
         return maxDay;
     }
     public ArrayList<String> IPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>>map, String date) {
         ArrayList<String> ips = map.get(date);
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(String ip : ips) {
            if(counts.containsKey(ip)) {
               counts.put(ip, counts.get(ip) + 1);
            }
            else {
              counts.put(ip, 1);
            }
         }
         return IPsMostVisits(counts);
     }
}
