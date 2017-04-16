
/**
 * Write a description of tricks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class tricks {
       public ImageResource enlarge(ImageResource image, int n) {
           ImageResource nim = new ImageResource(image.getWidth() * 2, image.getHeight() * n);
           for(Pixel p : nim.pixels()) {
              int x = p.getX();
              int y = p.getY();
              Pixel imPixel = image.getPixel((x / n), (y / n));
              nim.setPixel(x, y, imPixel);
           }  
           return nim;
       }
       public void enlargeAndSave(int n) {
           DirectoryResource dr = new DirectoryResource();
           for(File f : dr.selectedFiles()) {
              ImageResource image = new ImageResource(f);
              ImageResource nim = enlarge(image, n);
              nim.draw();
              String name = f.getName();
              String newName = n + "enlarged-" + name ;
              nim.setFileName(newName);
              nim.save();
            }
        }
       public ImageResource chop (ImageResource image, int newHeight, int newWidth) {
               ImageResource nim = new ImageResource(newWidth, newHeight);
               for(Pixel p : image.pixels()) {
                   int x = p.getX();
                   int y = p.getY();
                   Pixel imagePixel = image.getPixel(x,y);
                   int width = image.getWidth();
                   int height = image.getHeight();
                   if(x > (width - newWidth) / 2 && x < (width + newWidth) / 2 && y > (height - newHeight) / 2 && y < (height + newHeight) / 2) {
                       nim.setPixel(x - (width - newWidth) / 2,y - (height - newHeight) / 2,imagePixel);
                   }
               } 
               return nim;
        } 
       public void chopAndSave(int newHeight, int newWidth) {
           DirectoryResource dr = new DirectoryResource();
           for(File f : dr.selectedFiles()) {
                ImageResource image = new ImageResource(f);
                ImageResource nim = chop(image, newHeight, newWidth);
                String name = image.getFileName();
                String newName = "chop-" + name;
                nim.setFileName(newName);
                nim.draw();
                nim.save();
           }
        }
       public Pixel chop2hide(Pixel pixel) {
          pixel.setRed((pixel.getRed()/16) * 16);
          pixel.setGreen((pixel.getGreen()/16)*16);
          pixel.setBlue((pixel.getBlue()/16)*16);
          return pixel;
       }
       public Pixel shift(Pixel pixel) {
         pixel.setRed((pixel.getRed()/16));
         pixel.setGreen((pixel.getGreen()/16));
         pixel.setBlue((pixel.getBlue()/16));
         return pixel;
       }
       public ImageResource encrypt(ImageResource im2show, ImageResource im2hide) {  
          ImageResource nim = new ImageResource(im2show.getWidth(), im2show.getHeight());
          im2hide = chop(im2hide,im2show.getHeight(), im2show.getWidth());
          for(Pixel p : nim.pixels()) {
             int x = p.getX();
             int y = p.getY();
             Pixel showPixel = chop2hide(im2show.getPixel(x,y));
             Pixel hidePixel = shift(im2hide.getPixel(x,y));
             p.setRed(showPixel.getRed() + hidePixel.getRed());
             p.setGreen(showPixel.getGreen() + hidePixel.getGreen());
             p.setBlue(showPixel.getBlue() + hidePixel.getBlue());
          }
            return nim;
        }
       public void encryptAndSave() {
          DirectoryResource dr = new DirectoryResource();
          ImageResource im2hide = new ImageResource(20,20);
          ImageResource im2show = new ImageResource(30,20);
          for(File f : dr.selectedFiles()) {
              if(im2show.getWidth() == 40) {
                   im2show = new ImageResource(f);
                   im2show.setFileName("1");
                } 
              if(im2hide.getWidth() == 20) {
                  im2hide = new ImageResource(f);
                  im2show = new ImageResource(40,20);
               }
              if(im2show.getFileName() == "1") {
                 ImageResource nim = encrypt(im2show, im2hide);
                 String name = im2show.getFileName();
                 String newName = "encrypted" + name;
                 nim.setFileName(newName);
                 nim.draw();
                 nim.save();
                 return;
                }
            }
          System.out.println("----------------------");
        }
       public ImageResource fillImage(ImageResource image, int newWidth, int newHeight) {
              ImageResource nim = new ImageResource(newWidth, newHeight);
              int width = image.getWidth();
              int height = image.getHeight();
              for(Pixel p : nim.pixels()) {
                 int x = p.getX();
                 int y = p.getY();
                 if(x > (newWidth - width) / 2 && x < (newWidth + width) / 2 && y > (newHeight - height) / 2 && y < (newHeight + height) / 2) {
                     Pixel imPixel = image.getPixel(x - (newWidth - width) / 2, y - (newHeight - height) / 2);
                     nim.setPixel(x , y , imPixel);
                    }
                 else {
                    p.setRed(255);
                    p.setGreen(255);
                    p.setBlue(255);
                 }
              }
              return nim;
        }
       public void decrypt() {
         ImageResource im2decrypt = new ImageResource();
         ImageResource nim = new ImageResource(im2decrypt.getWidth(), im2decrypt.getHeight());
         for(Pixel p : nim.pixels()) {
            int  x = p.getX();
            int y = p.getY();
            Pixel im2decryptPixel = im2decrypt.getPixel(x,y);
            p.setRed((im2decryptPixel.getRed()-(im2decryptPixel.getRed()/16) * 16) * 16);
            p.setGreen((im2decryptPixel.getGreen()-(im2decryptPixel.getGreen()/16) * 16) * 16);
            p.setBlue((im2decryptPixel.getBlue()-(im2decryptPixel.getBlue()/16) * 16) * 16);
         }
         nim.setFileName("decrypted");
         nim.draw();
       }
}
