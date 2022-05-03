package main;

import entity.Entity;
import object.OBJ_Food;

public class AssetSetter {
    GamePanel gp;
    Entity entity;
    public AssetSetter(GamePanel gp) {
        this.gp=gp;
    }

    public void setObject(GamePanel gp, Entity entity) {
        gp.obj[0] = new OBJ_Food();
        gp.obj[0].generate(gp, entity);
        gp.obj[0].worldX = gp.obj[0].y*gp.tileSize;
        gp.obj[0].worldY = gp.obj[0].x*gp.tileSize;
    }
}
