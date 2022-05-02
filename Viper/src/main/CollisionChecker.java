package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
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
                if(entityTopRow < 0) {
                    // map boundary
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapLayout[entityTopRow][entityLeftCol];
                    tileNum2 = gp.tileM.mapLayout[entityTopRow][entityRightCol];
                    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                        // Collision detected
                        entity.collisionOn = true;
                    }
                }
                break;
            case "down":
                entityBottomRow = (entityBottomx + (entity.speed))/gp.tileSize;
                if(entityBottomRow >= 12){
                    // map boundary
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapLayout[entityBottomRow][entityLeftCol];
                    tileNum2 = gp.tileM.mapLayout[entityBottomRow][entityRightCol];
                    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                        // collision detected
                        entity.collisionOn = true;
                    }
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
                if(entityRightCol >= 16){
                    // map boundary
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapLayout[entityTopRow][entityRightCol];
                    tileNum2 = gp.tileM.mapLayout[entityBottomRow][entityRightCol];
                    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                        // collision detected
                        entity.collisionOn = true;
                    }
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
                if(entityLeftCol < 0){
                    // map boundary
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapLayout[entityTopRow][entityLeftCol];
                    tileNum2 = gp.tileM.mapLayout[entityBottomRow][entityLeftCol];
                    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                        // collision detected
                        entity.collisionOn = true;
                    }
                }
                break;

        }
    }
    public int checkObject(Entity entity, boolean player)
    {
        int index=999;
        for(int i=0;i<gp.obj.length; i++) {
            if(gp.obj[i]!= null) {
                //entity solid area position
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;

                //object solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;

                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY;
            }

        }
        return index;
    }

}
