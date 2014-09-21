package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by denislavrov on 9/14/14.
 */
public class DBIO {
    public static File im = new File("/Users/denislavrov/Documents/Developing/Java/Library-Management-System/LMS/resources/missing.jpg");
    public static byte[] imageNotAvailable =  imageFromFile(im);
    public static BufferedImage bImageNotAvailable = bImageFromArray(imageNotAvailable);


    public static byte[] imageFromFile(File f){
        byte[] ret  = new byte[(int) f.length()];
        try(FileInputStream fis = new FileInputStream(f)){
            fis.read(ret);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static BufferedImage bImageFromArray(byte[] arr){
        InputStream in;
        try {
            in = new ByteArrayInputStream(arr);
        } catch (NullPointerException e){
            return null;
        }
        try {
            return ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
