package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Bomb extends SuperObject{
    public OBJ_Bomb() {
        name="Bomb";

        try{ 
            image = ImageIO.read(getClass().getResourceAsStream("/assets/objects/bomb.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}