
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        la.printAll();
        System.out.println("--------------------------------------");
    }
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        System.out.println("Count of unique IPs: " + la.countUniqueIPs());
        System.out.println("--------------------------------------");
    }
    public void testAllHigherThanNum(int num) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        la.printAllHigherThanNum(num);
        System.out.println("---------------------------------------");
    }
    public void testUniqueIPVisitsOnDay(String someday) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        ArrayList<String> uniIps = la.uniqueIPVisitsOnDay(someday);
        System.out.println("Date: " + someday);
        for(String ip : uniIps) {
           System.out.println(ip);
        }
        System.out.println("size is: " + uniIps.size());
        System.out.println("---------------------------------------");
    }
    public void testCountUniqueIPsInRange(int low, int high) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        System.out.println("Count is: " + la.countUniqueIPsInRange(low, high));
        System.out.println("---------------------------------------");
    }
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        System.out.println(la.countVisitsPerIP());
        System.out.println("---------------------------------------");
    }
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, Integer> map = la.countVisitsPerIP();
        System.out.println(la.IPsMostVisits(map));
        System.out.println("---------------------------------------");
    }
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        System.out.println(la.IPsForDays());
        System.out.println("---------------------------------------");
    }
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, ArrayList<String>> map = la.IPsForDays();
        System.out.println(la.dayWithMostIPVisits(map));
        System.out.println("---------------------------------------");
    }
    public void testIPsWithMostVisitsOnDay(String date) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, ArrayList<String>> map = la.IPsForDays();
        System.out.println("Date is: " + date);
        System.out.println(la.IPsWithMostVisitsOnDay(map, date));
        System.out.println("---------------------------------------");
    }
}
