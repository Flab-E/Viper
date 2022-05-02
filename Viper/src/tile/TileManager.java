package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Graphics2D;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapLayout[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        
        tile = new Tile[10];
        mapLayout = new int[gp.maxScreenRow][gp.maxScreenCol];

        getTileImage();
        loadMap();

        for(int i=0; i<gp.maxScreenRow; i++) {
            for(int j=0; j<gp.maxScreenCol; j++) {
                System.out.print(this.mapLayout[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/sand.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/tree.png"));
            tile[5].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map01");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(row<gp.maxScreenRow) {
                col = 0;
                String line = br.readLine();
                String nums[] = line.split(" ");
                while(col<gp.maxScreenCol){
                    int num = Integer.parseInt(nums[col]);
                    this.mapLayout[row][col] = num;
                    col++;    
                }
                row++;
            }
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        // g2.setColor(Color.white);               // set color to white
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);       // create a rectangle of 1 tile at 100,100
    
        //drawing tiles as a matrix

        for(int y=0; y<12; y++) {
            for(int x=0; x<16; x++){
                g2.drawImage(tile[this.mapLayout[y][x]].image, x*48, y*48, gp.tileSize, gp.tileSize, null);
            }
        }

        // g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
    }
}
