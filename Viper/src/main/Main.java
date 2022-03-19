package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String argsp[]) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Viper");

        GamePanel gamePanel = new GamePanel();      // init gamePanel a chid of JPanel
        window.add(gamePanel);

        window.pack();                              // fits the windows to gamePanel layout

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}