
/**
 * Write a description of CaesarCipherViaFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class CaesarCipherViaFiles {
    public CaesarCipherViaFiles(int k) {
       key = k;
    }
    public void setKey(int k) {
       key = k;
    }
    public void encryptViaFile() {
       CaesarCipher cc = new CaesarCipher(key);
       FileResource fr = new FileResource();
       String message = fr.asString();
       String encrypted = cc.encrypt(message);
       System.out.println("key is " + key + "\n" + encrypted);
    }
    public void decryptWithKeyViaFile() {
       CaesarBreaker cb = new CaesarBreaker();
       FileResource fr = new FileResource();
       String encrypted = fr.asString();
       String decrypted = cb.decryptWithKey(encrypted, key);
       System.out.println("key is " + key + "\n" + decrypted);
    }
    public void decryptWithoutKeyViaFile() {
       CaesarBreaker cb = new CaesarBreaker();
       FileResource fr = new FileResource();
       String encrypted = fr.asString();
       String decrypted = cb.decryptWithoutKey(encrypted);
       System.out.println(decrypted);
    }
    public void encryptTwoKeysViaFile(int key1, int key2) {
       CaesarCipherTwo cct = new CaesarCipherTwo();
       FileResource fr = new FileResource();
       String input = fr.asString();
       String output = cct.encryptTwoKeys(input, key1, key2);
       System.out.println("key1 is " + key1 + "   key2 is " + key2);
       System.out.println(output);
    }
    public void decryptTwoKeysWithoutViaFile() {
       CaesarBreakerTwo cbt = new CaesarBreakerTwo();
       FileResource fr = new FileResource();
       String encrypted = fr.asString();
       String decrypted = cbt.decryptTwoKeysWithout(encrypted);
       System.out.println(decrypted);
    }
    private int key;
}
