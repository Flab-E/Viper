package main;

import object.OBJ_Food;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp=gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Food();
        gp.obj[0].worldX = 6*gp.tileSize;
        gp.obj[0].worldY = 7*gp.tileSize;

        gp.obj[1] = new OBJ_Food();
        gp.obj[1].worldX = 4*gp.tileSize;
        gp.obj[1].worldY = 4*gp.tileSize;
    }
}
