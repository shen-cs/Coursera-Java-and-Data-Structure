
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class tester {
       public void CCencryptViaFile(int key) {
           FileResource fr = new FileResource();
           String message = fr.asString();
           CaesarCipher cc = new CaesarCipher(key);
           System.out.println(cc.encrypt(message));
       }
       public void CCdecryptViaFile() {
           FileResource fr = new FileResource();
           String encrypted = fr.asString();
           CaesarCracker cb = new CaesarCracker();
           System.out.println(cb.decrypt(encrypted));
       }
       public void VencryptViaFile(String key) {
           String alph = "abcdefghijklmnopqrstuvwxyz";
           key = key.toLowerCase();
           int[] keys = new int[key.length()];
           for(int i = 0; i < keys.length; i++) {
               keys[i] = alph.indexOf(key.charAt(i));
           }           
           FileResource fr = new FileResource();
           String message = fr.asString();
           VigenereCipher vc = new VigenereCipher(keys);
           System.out.println(vc.encrypt(message));
       }
}
