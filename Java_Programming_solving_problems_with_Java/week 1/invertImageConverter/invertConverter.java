
/**
 * Write a description of invertConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class invertConverter {
       public ImageResource makeInvert (ImageResource image) {
            ImageResource nim = new ImageResource(image.getWidth(), image.getHeight());
            for(Pixel p : nim.pixels()) {
                Pixel inPixel = image.getPixel(p.getX(), p.getY());
                p.setRed(255 - inPixel.getRed());
                p.setGreen(255 - inPixel.getGreen());
                p.setBlue(255 - inPixel.getBlue());
            }
            return nim;
        }
       public void selectAndConvert() {
           DirectoryResource dr = new DirectoryResource();
           for(File f : dr.selectedFiles()) {
               ImageResource image = new ImageResource(f);
               ImageResource nim = makeInvert(image);
               nim.draw();
            }
        }
       public void selectAndConvertAndSave() {
           DirectoryResource dr = new DirectoryResource();
           for(File f : dr.selectedFiles()) {
              ImageResource image = new ImageResource(f);
              ImageResource newImage = makeInvert(image);
              String name = image.getFileName();
              String newName = "invert- " + name;
              newImage.setFileName(newName);
              newImage.draw();
              newImage.save();
            } 
        }    
}
