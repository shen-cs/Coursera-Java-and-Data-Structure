/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class findAGene {
	public String findProtein(String dna) {
		String DNA = dna.toUpperCase();
	    int start = DNA.indexOf("ATG");
	    String gene;
		if (start == -1) {
			return  "";
		}
		int stop = DNA.indexOf("tag", start+3);
		if ((stop - start) % 3 == 0) {
			return DNA.substring(start, stop+3);
		}
		stop = DNA.indexOf("TGA", start + 3);
		if ((stop - start) % 3 == 0) {
			return DNA.substring(start, stop+3);
		}
		stop = DNA.indexOf("TAA", start + 3);
		if ((stop - start) % 3 == 0) {
			return DNA.substring(start, stop+3);
		}
		return "";
	   }
	public String stopCodon(String gene) {
	    if(gene == "") {
	    return "";
	   }
	   else {
	   int length = gene.length();
	   return gene.substring(length - 3 ,length);
	   }
	   }
	public void testing() {
		String a = "acatgataacctaag";
		String ap = "".toUpperCase();
		//String a = "atgcctag";
		//String ap = "";
		//String a = "ATGCCCTAG";
		//String ap = "ATGCCCTAG";
		String result = findProtein(a);
		if (ap.equals(result)) {
			System.out.println("success for " + result + " length " + result.length());
			System.out.println("The stop codon is " + stopCodon(ap));
		}
		else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
	}
}


