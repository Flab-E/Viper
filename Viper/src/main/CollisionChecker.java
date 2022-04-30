package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp
    }

    public void checkTile(Entity entity) {

        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopx = entity.y + entity.solidArea.y;
        int entityBottomx = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = entityRightX/gp.tileSize;
        int entityTopRow = entityTopx/gp.tileSize;
        int entityBottomRow = entityBottomx/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopx - entity.speed)/gp.tileSize;
                tileNum1 = gp.TileM.mapLayout[entityTopRow][entityLeftCol];
                tileNum2 = gp.TileM.mapLayout[entityTopRow][entityRightCol];
                if(gp.TileM.tile[tileNum1].collision == true || gp.TileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityTopRow = (entityTopx - entity.speed)/gp.tileSize;
                tileNum1 = gp.TileM.mapLayout[entityTopRow][entityLeftCol];
                tileNum2 = gp.TileM.mapLayout[entityTopRow][entityRightCol];
                if(gp.TileM.tile[tileNum1].collision == true || gp.TileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityTopRow = (entityTopx - entity.speed)/gp.tileSize;
                tileNum1 = gp.TileM.mapLayout[entityTopRow][entityLeftCol];
                tileNum2 = gp.TileM.mapLayout[entityTopRow][entityRightCol];
                if(gp.TileM.tile[tileNum1].collision == true || gp.TileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityTopRow = (entityTopx - entity.speed)/gp.tileSize;
                tileNum1 = gp.TileM.mapLayout[entityTopRow][entityLeftCol];
                tileNum2 = gp.TileM.mapLayout[entityTopRow][entityRightCol];
                if(gp.TileM.tile[tileNum1].collision == true || gp.TileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

        }
    }
}
