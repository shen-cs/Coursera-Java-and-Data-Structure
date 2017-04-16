
/**
 * Write a description of mergeSortClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class mergeSortClass {
      private ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
          int length = list.size();
          if(length == 1) {
              return new ArrayList<Integer>(list.subList(0,length));
          }
          ArrayList<Integer> left = mergeSort(new ArrayList<Integer>(list.subList(0, length / 2)));
          ArrayList<Integer> right = mergeSort(new ArrayList<Integer>(list.subList(length / 2, length)));
          int j = 0;
          //merge left to right
          for(int i = 0; i < left.size(); i++) {
             while(j < right.size() && right.get(j) < left.get(i)) {
                 j++;
             }
             right.add(j, left.get(i));
             j++;
          }
          return right;
      }
      
      private ArrayList<Integer> changeList(ArrayList<Integer> list) {
         ArrayList<Integer> set = new ArrayList<Integer>();
         for(int i = 0; i < 10; i++) {
            set.add(i);    
         }
         return set;
      }
      
      public void testMergeSort(int[] arr) {
         ArrayList<Integer> list = new ArrayList<Integer>();
         for(int num : arr) {
            list.add(num);   
         }
         //list = changeList(list);
         //mergeSort(list);
         System.out.println(mergeSort(list));
      }
}
