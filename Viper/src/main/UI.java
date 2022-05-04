package main;

import java.awt.Graphics2D;

import entity.Entity;
import entity.Player;

import java.awt.Color;
import java.awt.Font;

public class UI {
    GamePanel gp;
    Font ariel_40,maruMonica, purisaB;
    public boolean messageOn =false;
    public int commandNum=0;

    public UI(GamePanel gp) {
        this.gp = gp;
        ariel_40 = new Font("Ariel", Font.PLAIN,28);
    }    

    public void draw(Graphics2D g2, Entity entity){
        g2.setFont(ariel_40);
        g2.setColor(Color.white);

        if(gp.gameState==gp.titleState){
            drawTitleScreen(g2);   
        }
        if(gp.gameState==gp.gameOverState){
            drawGameOverScreen(g2, entity);   
        }

    }

    public void drawTitleScreen(Graphics2D g2){
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text= "Viper";
        int x = getXForCenteredText(text, g2);
        int y = gp.tileSize*3;
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x=gp.screenWidth/2 - (gp.tileSize*2)/2;
        y+=gp.tileSize*2;
        g2.drawImage(gp.player.left1,x , y, gp.tileSize*2,gp.tileSize*2,null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text="NEW GAME";
        x=getXForCenteredText(text, g2);
        y+=gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum ==0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text="QUIT";
        x=getXForCenteredText(text, g2);
        y+=gp.tileSize;
        g2.drawString(text, x, y+13);
        if(commandNum ==1) {
            g2.drawString(">", x-gp.tileSize, y+13);
        }


    }
    public int getXForCenteredText(String text, Graphics2D g2){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x= gp.screenWidth/2 -length/2;
        return x;
    }

    public void drawGameOverScreen(Graphics2D g2, Entity entity) {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        String text = "Game Over";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 98f));
        g2.setColor(Color.BLACK);
        int x=getXForCenteredText(text, g2);
        int y=gp.tileSize*4;
        g2.drawString(text, x, y);

        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        g2.setFont(g2.getFont().deriveFont(40f));
        text="Retry";
        x = getXForCenteredText(text, g2);
        y= gp.tileSize*4;
        g2.drawString(text, x, y+170);
        if(commandNum==0){
            g2.drawString(">", x-40, y+170);
        }

        text= "Quit";
        x=getXForCenteredText(text, g2);
        y +=45;
        g2.drawString(text, x, y+175);
        if(commandNum==1){
            g2.drawString(">", x-40, y+175);
        }

        g2.setFont(g2.getFont().deriveFont(30f));
        text = ""+entity.highScore;
        x=getXForCenteredText(text, g2);
        y= gp.tileSize*4;
        g2.drawString("Highscore: "+text, x-85, y+90);


    }
}


