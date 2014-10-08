package persistance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import util.DBIO;

import javax.persistence.Embeddable;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by denislavrov on 10/9/14.
 */

@Data
@EqualsAndHashCode(exclude = "image")
@Embeddable
public class DBImage {
    private byte[] image = DBIO.imageNotAvailable;

    public void imageToDatabase(File f){
        image = DBIO.imageFromFile(f);
    }

    public BufferedImage imageFromDatabase(){
        BufferedImage ret = DBIO.bImageFromArray(image);
        if (ret != null) return ret;
        return DBIO.bImageNotAvailable;
    }
}
