package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    // JPanel for rendering a GUI Panel
    // Runnable to implement threads for game/render loops

    // screen settings
    final int originalTileSize = 16;                    // 16x16 pixels is the size of tile
    final int scale = 3;                                // this is to handle higher resolution systems were tile size becomes too small
    final int tileSize = originalTileSize * scale;      // 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;        // 768 pixels ==> 16 tiles
    final int screenHeight = tileSize * maxScreenRow;       // 576 pixel ==> 12 tiles

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;                                      // for game render loop

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
        while(gameThread != null) {
            // as long as the gameThread is running
            // System.out.println("Gane loop is running");

            // Update Game's instance
            update();
            // Draw the updated instance
            repaint();                                  // this is how the graphics paint component method is called
        }
    }

    public void update() {
        if(keyH.upPressed == true) {
            playery -= playerSpeed;                     // player moves up by 4 pixels
        }
        if(keyH.downPressed == true) {
            playery += playerSpeed;                     // player moves down by 4 pixels
        }
        if(keyH.rightPressed == true) {
            playerx += playerSpeed;                     // player moves right by 4 pixels
        }
        if(keyH.leftPressed == true) {
            playerx -= playerSpeed;                     // player moves left by 4 pixels
        }
    }

    // Graphics is n inbuild awt class used to render/draw objects onto the screen/window
    public void paintComponent(Graphics g) {
        super.paintComponent(g);                // super => parent, i.e. JPanel 
        Graphics2D g2 = (Graphics2D)g;          // typecasting Graphics g to Graphics2D

        g2.setColor(Color.white);               // set color to white
        g2.fillRect(playerx, playery, tileSize, tileSize);       // create a rectangle of 1 tile at 100,100
    }
}
