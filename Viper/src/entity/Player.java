package entity;
import main.GamePanel;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Player extends Entity {

    GamePanel gp;
    main.KeyHandler keyH;
    int hasKey;

    public Player(GamePanel gp, main.KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX =solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed == true) {
            direction = "up";
        }
        if(keyH.downPressed == true) {
            direction = "down";
        }
        if(keyH.rightPressed == true) {
            direction = "right";
        }
        if(keyH.leftPressed == true) {
            direction = "left";
        }

        // check for collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // check obj collision
        int objIndex= gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // if collision player can move
        if(collisionOn == false){
            if(animation_count == max_animation_count){
                if(animation_state == 1) {
                    animation_state = 2;
                } else {
                    animation_state = 1;
                }
                animation_count = 0;
            } else {
                animation_count++;
            }
            switch(direction) {
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "right": x += speed; break;
                case "left": x -= speed; break;
            }
        }
    }
    public void pickUpObject(int i) {
        if(i != 999) {
            gp.obj[i] = null;
            hasKey++;
            System.out.println("Score ="+hasKey);
        }
    }

    public void draw(Graphics2D g2) {

        // g2.setColor(Color.white);               // set color to white
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);       // create a rectangle of 1 tile at 100,100
    
        BufferedImage image = null;
        switch(direction) {
            case "up":
                if(animation_state == 1){ 
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if(animation_state == 1){
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if(animation_state == 1){
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if(animation_state == 1){
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
