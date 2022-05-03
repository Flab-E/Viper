package main;

import entity.Entity;
import object.OBJ_Food;
import object.OBJ_Bomb;

public class AssetSetter {
    GamePanel gp;
    Entity entity;
    public AssetSetter(GamePanel gp) {
        this.gp=gp;
    }

    public void setObject(GamePanel gp, Entity entity) {
        // set assets 0 to food
        gp.obj[0] = new OBJ_Food();
        gp.obj[0].generate(gp, entity);
        gp.obj[0].worldX = gp.obj[0].y*gp.tileSize;
        gp.obj[0].worldY = gp.obj[0].x*gp.tileSize;

        //set assets 1 and 2 to bomb
        gp.obj[1] = new OBJ_Bomb();
        gp.obj[1].generate(gp, entity);
        gp.obj[1].worldX = gp.obj[1].y*gp.tileSize;
        gp.obj[1].worldY = gp.obj[1].x*gp.tileSize;
        
        gp.obj[2] = new OBJ_Bomb();
        gp.obj[2].generate(gp, entity);
        gp.obj[2].worldX = gp.obj[1].y*gp.tileSize;
        gp.obj[2].worldY = gp.obj[1].x*gp.tileSize;
    }

    public void genFood(GamePanel gp, Entity entity) {
        // set assets 0 to food
        gp.obj[0] = new OBJ_Food();
        gp.obj[0].generate(gp, entity);
        gp.obj[0].worldX = gp.obj[0].y*gp.tileSize;
        gp.obj[0].worldY = gp.obj[0].x*gp.tileSize;
    }

    public void genBomb(GamePanel gp, Entity entity, int id){
       //set assets 1 and 2 to bomb
       gp.obj[id] = new OBJ_Bomb();
       gp.obj[id].generate(gp, entity);
       gp.obj[id].worldX = gp.obj[id].y*gp.tileSize;
       gp.obj[id].worldY = gp.obj[id].x*gp.tileSize;
    }
}
