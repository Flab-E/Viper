package object;

import java.io.IOException;
import javax.imageio.ImageIO;


public class OBJ_Food extends SuperObject {

    public OBJ_Food() {
        name="Food";

        try{ 
            image = ImageIO.read(getClass().getResourceAsStream("/assets/objects/food.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
