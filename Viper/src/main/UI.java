package main;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class UI {
    GamePanel gp;
    Font ariel_40,maruMonica, purisaB;
    public boolean messageOn =false;

    public UI(GamePanel gp) {
        this.gp = gp;
        ariel_40 = new Font("Ariel", Font.PLAIN,28);
    }    

    public void draw(Graphics2D g2){
        g2.setFont(ariel_40);
        g2.setColor(Color.white);

        if(gp.gameState==gp.titleState){
            drawTitleScreen(g2);   
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
    }
    public int getXForCenteredText(String text, Graphics2D g2){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x= gp.screenWidth/2 -length/2;
        return x;
    }
}


