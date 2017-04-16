
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (Shen Chan-Shao) 
 * @version (version 1)
 */
public class CaesarCipher {
    public CaesarCipher(int k) {
        key = k;
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    public void setKey(int k) {
       key = k;
       shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    public String encrypt(String input) {
        StringBuilder newStg = new StringBuilder(input);
        for(int i = 0; i < newStg.length(); i++) {
            boolean isCapital = false;
            if(Character.isUpperCase(newStg.charAt(i))) {
               isCapital = true;
            }
            char ch = newStg.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(ch));
            if(index != -1) {
                if(isCapital) {
                   newStg.setCharAt(i,shiftedAlphabet.charAt(index));
                }
                else {
                   newStg.setCharAt(i,Character.toLowerCase(shiftedAlphabet.charAt(index)));
                }
            }
        }
        //System.out.println(newStg.toString());
        return newStg.toString();
    }    
    private int key;
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet;
}
