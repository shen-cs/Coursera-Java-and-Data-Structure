import java.util.*;

public class SortTimings {
	Random random = new Random();
	
	private String makeString(int size){
		StringBuilder sb = new StringBuilder();
		String alph = "abcdefghijklmnopqrstuvwxyz";
		for(int k=0; k < size; k++){
			sb.append(alph.charAt(random.nextInt(alph.length())));
		}
		return sb.toString();
	}
	
	public ArrayList<String> makeRandomList(int wordSize, int size){
		ArrayList<String> list = new ArrayList<String>();
		for(int k=0; k < size; k++){
			list.add(makeString(wordSize));
		}
		return list;
	}
	
	private void bubbleSort(ArrayList<String> list){
		for(int k=0; k < list.size(); k++) {
			for(int j=0; j < list.size()-k-1; j++) {
				if (list.get(j).compareTo(list.get(j+1)) > 0) {
					Collections.swap(list, j,j+1);
				}
			}
		}
	}
	
	private void selectSort(String[] list) {
	    for(int k=0; k < list.length; k++){
			int mindex = k;
			for(int j=k+1; j < list.length; j++){
				if (list[j].compareTo(list[mindex]) < 0){
					mindex = j;
				}
			}
			String temp = list[k];
			list[k] = list[mindex];
			list[mindex] = temp;
		}
	}
	
	private void selectSort(ArrayList<String> list) {
		for(int k=0; k < list.size(); k++){
			int mindex = k;
			for(int j=k+1; j < list.size(); j++){
				if (list.get(j).compareTo(list.get(mindex)) < 0) {
					mindex = j;
				}
			}
			Collections.swap(list, k, mindex);
		}
	}
	
	private int Partition(ArrayList<String> list, int start, int end) {
	   int index = start;
	   int a = start;
	   int b = end;
	   while(true) {
		  while(list.get(b).compareTo(list.get(index)) >= 0) {
			  b--;
			  if(b == index) {
			     return index;
			  }
		  }
		  Collections.swap(list, b, index);
		  index = b;
		  while(list.get(a).compareTo(list.get(index)) <= 0) {
			  a++;
			  if(a == index) {
			     return index;
			  }
		  }
		  Collections.swap(list, a, index);
		  index = a;
	   }
	}
	private void quickSort(ArrayList<String> list, int start, int end) {
	    if(start >= end) {
	        return;
	    }
	    int a = Partition(list, start, end);
	    quickSort(list, start, a - 1);
	    quickSort(list, a + 1, end);
	}
	
	private ArrayList<String> mergeSort(ArrayList<String> list) {
          int length = list.size();
          if(length == 1) {
              return new ArrayList<String>(list.subList(0,length));
          }
          ArrayList<String> left = mergeSort(new ArrayList<String>(list.subList(0, length / 2)));
          ArrayList<String> right = mergeSort(new ArrayList<String>(list.subList(length / 2, length)));
          int j = 0;
          //merge left to right
          for(int i = 0; i < left.size(); i++) {
             while(j < right.size() && right.get(j).compareTo(left.get(i)) < 0) {
                 j++;
             }
             right.add(j, left.get(i));
             j++;
          }
          return right;
      }
	
	private boolean isSorted(ArrayList<String> list) {
		for(int k=1; k < list.size(); k++){
			if (list.get(k).compareTo(list.get(k-1)) < 0) {
				return false;
			}
		}
		return true;
    }


     private void timer(int start, int stop, int increment, int trials) {
		for(int k = start; k <= stop; k += increment) {
			ArrayList<String> list = makeRandomList(10,k);

		    double begin = System.nanoTime();
			for(int j = 0; j < trials; j++) {
				ArrayList<String> copy = new ArrayList<String>(list);
				//quickSort(copy, 0 , copy.size() - 1);
				copy = mergeSort(copy);
				if (! isSorted(copy)) {
					System.out.println("trouble on sorted select "+k);
				}
			}	
			double end = System.nanoTime();
			double stime = (end-begin) / 1e9 / trials;
			begin = System.nanoTime();
			for(int j=0; j < trials; j++) {
				ArrayList<String> copy = new ArrayList<String>(list);
				//Collections.sort(copy);
				//bubbleSort(copy)
				quickSort(copy, 0 , copy.size() - 1);
				if (! isSorted(copy)) {
					System.out.println("trouble on sorted tim "+k);
				}
			}	
			end = System.nanoTime();
			double ttime = (end-begin) / 1e9 / trials;
			System.out.printf("%d\t%3.2f\t%3.2f\n",k,stime,ttime);
		}
	} 
	
	public void runSelect(){
	    String[] cats = {"tiger", "lion", "cheetah", "puma", "leopard"};
	    ArrayList<String> animals = new ArrayList<String>();
	    for(String s : cats) {
	       animals.add(s);
	    }
	    quickSort(animals, 0, animals.size() -1 );
	    for(String s : animals) {
	        System.out.println(s);
	    }
	}

	public void runner(){
		timer(10000,100000,1000,2);
	}

}
