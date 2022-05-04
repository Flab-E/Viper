package main;
import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class GamePanel extends JPanel implements Runnable{
    // JPanel for rendering a GUI Panel
    // Runnable to implement threads for game/render loops

    // screen settings
    final int originalTileSize = 12;                    // 16x16 pixels is the size of tile
    final int scale = 3;                                // this is to handle higher resolution systems were tile size becomes too small
    public final int tileSize = originalTileSize * scale;      // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;                     // 768 pixels ==> 16 tiles
    public final int screenHeight = tileSize * maxScreenRow;                    // 576 pixel ==> 12 tiles
    public boolean foodExists = false;
    public int bombNo = 1;
    public boolean bombsExist[] = new boolean[10];

    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;                                                          // for game render loop
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[10];                             // 5 slots o objects in the game
    public UI ui =new UI(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public final int titleState = 0;
    public int gameState=titleState;
    public int gameOverState = 6;
    

    // FPS
    int fps = 60;

    // set player's default position
    int playerx = 100;
    int playery = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));        // set width and height of Jpanel
        this.setBackground(Color.black);                                        // set bg color to black as default
        this.setDoubleBuffered(true);                                           // JPanel's double buffer helps better rendering
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void scoreScreen(Graphics2D g2) {
        int y = (int)((maxScreenRow-1.6)*tileSize);
        int x = 0*tileSize;
        int width = 4*tileSize;
        int height = (int)(1.5*tileSize);
        
        Color c = new Color(0, 0, 0, 150);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+1, y+1, width-2, height-2, 35, 35);

        String scoreStr = "Score:        " + player.hasKey;
        String levelStr = "Level:        " + player.level;
        String highScore = "High Score:  " + player.highScore;
        x += tileSize/2;
        y += tileSize/2;
        g2.drawString(scoreStr, x, y);
        g2.drawString(levelStr, x, y+12);
        g2.drawString(highScore, x, y+24);
    }

    public void setupGame() {
        foodExists = true;
        bombsExist[0] = true;
        for(int i = 1; i<bombNo; i++) {
            bombsExist[i] = false;
        }
        aSetter.setObject(this, player);
        playMusic(2);
    }

    public void startGameThread() {
        gameThread = new Thread(this);                      // pass gamePanel to the costructor
        gameThread.start();                                 // calls run() method
    }

    // run() is used whenever we implement a Runnable class
    // and is used to create a thread.
    // starting the thread causes the run() to be called
    // for execution in that thread
    @Override
    public void run() {

        // Delat Accumulator method for game loop:
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        // to display fps
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null)  {
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>=1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer>=1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
        
        update();
        repaint();
         
    }

    public void update() {
        // if food does not exist genFood
        if(foodExists == false) {
            foodExists = true;
            aSetter.genFood(this, player);
        }
        // if bomb does not exist genbomb
        for(int i = 0; i<bombNo; i++){
            if(bombsExist[i] == false) {
                bombsExist[i] = true;
                aSetter.genBomb(this, player, i+1);
            }
        }
        if(gameState==1) {
            player.update();
        }
    }

    // Graphics is n inbuild awt class used to render/draw objects onto the screen/window
    public void paintComponent(Graphics g) {
        super.paintComponent(g);                // super => parent, i.e. JPanel
        Graphics2D g2 = (Graphics2D)g;          // typecasting Graphics g to Graphics2D

        // title screen
        if(gameState==titleState){
            ui.draw(g2, player);

        }
        else if(gameState==gameOverState) {
            ui.drawGameOverScreen(g2, player);
        }
        else{
            // tile
        tileM.draw(g2);
        for(int i =1; i<obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        if(obj[0] != null){
            obj[0].draw(g2, this);
        }
        player.draw(g2);
        scoreScreen(g2);       
        //ui.draw(g2);
        
        g2.dispose();

        }

        
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
