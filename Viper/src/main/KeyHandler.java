package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    // Key Listener is an abstract class used to handle user inputs using key board
    
    public GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    // requires 3 methods to be implemented:
    @Override
    public void keyTyped(KeyEvent e) {
        // keyTyped is not required
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();              // returns the integer value assigned to each key on the board

        if(gp.gameState==gp.titleState){

            if(code == KeyEvent.VK_W) {
                // if 'w' is pressed
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=1;
                }
            }
            if (code == KeyEvent.VK_S) {
                // if 's' is pressed
                gp.ui.commandNum++;
                if(gp.ui.commandNum>1){
                    gp.ui.commandNum=0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum ==0){
                    gp.gameState=1;
                }

                if(gp.ui.commandNum ==1){
                    System.exit(0);

                }

            }

        }
        else{
            if(code == KeyEvent.VK_W) {
                // if 'w' is pressed
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                // if 's' is pressed
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                // if 'a' is pressed
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                // if 'd' is pressed
                rightPressed = true;
            }
        }
}


    @Override 
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            // if 'w' is pressed
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            // if 's' is pressed
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            // if 'a' is pressed
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            // if 'd' is pressed
            rightPressed = false;
        }
    }
}
