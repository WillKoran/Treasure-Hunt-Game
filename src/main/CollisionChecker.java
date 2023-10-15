package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x
                + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y
                + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / this.gp.tileSize;
        int entityRightCol = entityRightWorldX / this.gp.tileSize;
        int entityTopRow = entityTopWorldY / this.gp.tileSize;
        int entityBottomRow = entityBottomWorldY / this.gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)
                        / this.gp.tileSize;
                tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                // check if the tiles that the player is walking into are solid
                if (this.gp.tileM.tile[tileNum1].collision == true
                        || this.gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)
                        / this.gp.tileSize;
                tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                // check if the tiles that the player is walking into are solid
                if (this.gp.tileM.tile[tileNum1].collision == true
                        || this.gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)
                        / this.gp.tileSize;
                tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = this.gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                // check if the tiles that the player is walking into are solid
                if (this.gp.tileM.tile[tileNum1].collision == true
                        || this.gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)
                        / this.gp.tileSize;
                tileNum1 = this.gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                // check if the tiles that the player is walking into are solid
                if (this.gp.tileM.tile[tileNum1].collision == true
                        || this.gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }

    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < this.gp.obj.length; i++) {

            if (this.gp.obj[i] != null) {

                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get object's solid area position
                this.gp.obj[i].solidArea.x = this.gp.obj[i].worldX
                        + this.gp.obj[i].solidArea.x;
                this.gp.obj[i].solidArea.y = this.gp.obj[i].worldY
                        + this.gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea
                                .intersects(this.gp.obj[i].solidArea)) {
                            if (this.gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea
                                .intersects(this.gp.obj[i].solidArea)) {
                            if (this.gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea
                                .intersects(this.gp.obj[i].solidArea)) {
                            if (this.gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea
                                .intersects(this.gp.obj[i].solidArea)) {
                            if (this.gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                this.gp.obj[i].solidArea.x = this.gp.obj[i].solidAreaDefaultX;
                this.gp.obj[i].solidArea.y = this.gp.obj[i].solidAreaDefaultY;

            }
        }
        return index;
    }
}
