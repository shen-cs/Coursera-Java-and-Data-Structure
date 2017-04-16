
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    public String decryptWithKey(String input, int k) {
        CaesarCipher cc = new CaesarCipher(26 -k);
        return cc.encrypt(input);
    }
    public String decryptWithoutKey(String encrypted) {
       //determine key
       key = getKey(encrypted);
       System.out.println("key is " + key);
       return decryptWithKey(encrypted, key);
    }
    public void setKey(int k) {
        key = k;
    }
    private int getKey(String s) {
       int[] freq = countLetterOf(s);
       int maxIdx = maxIndexOf(freq);
       int dkey = maxIdx - 4;
       dkey = (dkey < 0) ? (26 + dkey) : dkey;
       return dkey;
    }
    private int[] countLetterOf(String input) {
       int[] counts = new int[26];
       String newStg = input.toUpperCase();
       for(int i = 0 ; i < newStg.length(); i++) {
          if(Character.isLetter(newStg.charAt(i))) {
             counts[alphabet.indexOf(newStg.charAt(i))]++;
          } 
        }
       return counts;
    }
    private int maxIndexOf(int[] counts) {
       int maxIdx = 0;
       for(int i = 0; i < counts.length; i++) {
           if(counts[maxIdx] < counts[i]) {
               maxIdx = i;
           }
       }
       return maxIdx;
    }
    private int key;
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet;
}
