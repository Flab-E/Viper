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
    public int hasKey;
    public int level;
    public static int highScore;

    public Player(GamePanel gp, main.KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX =solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width = 8;
        solidArea.height = 8;

        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 1;
        speedCounter = 0;
        direction = "down";
        hasKey = 10;
        level = 1;
        highScore = hasKey;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/snake_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/snake_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/snake_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/snake_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/snake_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/snake_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/snake_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/snake_right_2.png"));

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
            // check if object is food
            if(gp.obj[i] != null && gp.obj[i].name.equals("Food")) {
                gp.playSE(0);
                gp.obj[i] = null;
                hasKey++;
                if(highScore < hasKey) {
                    highScore = hasKey;
                }
                gp.foodExists = false;
                //System.out.println("Score ="+hasKey);
                this.speedCounter++;
                if(speedCounter == 4) {
                    speedCounter = 0;
                    speed++;
                }
                if (hasKey%12 == 0) {
                    level++;
                }
                if(hasKey%20 == 0) {
                    gp.bombNo++;
                    if(gp.bombNo>9) {
                        gp.bombNo = 9;
                    }
                }
            }
            // check if object is bomb
            if(gp.obj[i] != null && gp.obj[i].name.equals("Bomb")) {
                gp.playSE(3);
                gp.obj[i] = null;
                hasKey--;
                for(int b=1; b<=gp.bombNo; b++){
                    if(i==b) {
                        gp.bombsExist[i-1] = false;
                    }
                }
                if(hasKey ==-1){
                    gp.gameState = gp.gameOverState;
                }
                //System.out.println("Score ="+hasKey);
                if(hasKey<0) {
                    hasKey = 0;
                }
            }
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