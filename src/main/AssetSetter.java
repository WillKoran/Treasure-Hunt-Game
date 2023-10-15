package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        this.gp.obj[0] = new OBJ_Key();
        this.gp.obj[0].worldX = 23 * this.gp.tileSize;
        this.gp.obj[0].worldY = 7 * this.gp.tileSize;

        this.gp.obj[1] = new OBJ_Key();
        this.gp.obj[1].worldX = 23 * this.gp.tileSize;
        this.gp.obj[1].worldY = 40 * this.gp.tileSize;

        this.gp.obj[2] = new OBJ_Key();
        this.gp.obj[2].worldX = 38 * this.gp.tileSize;
        this.gp.obj[2].worldY = 8 * this.gp.tileSize;

        this.gp.obj[3] = new OBJ_Door();
        this.gp.obj[3].worldX = 10 * this.gp.tileSize;
        this.gp.obj[3].worldY = 11 * this.gp.tileSize;

        this.gp.obj[4] = new OBJ_Door();
        this.gp.obj[4].worldX = 8 * this.gp.tileSize;
        this.gp.obj[4].worldY = 28 * this.gp.tileSize;

        this.gp.obj[5] = new OBJ_Door();
        this.gp.obj[5].worldX = 12 * this.gp.tileSize;
        this.gp.obj[5].worldY = 22 * this.gp.tileSize;

        this.gp.obj[6] = new OBJ_Chest();
        this.gp.obj[6].worldX = 10 * this.gp.tileSize;
        this.gp.obj[6].worldY = 7 * this.gp.tileSize;

        this.gp.obj[7] = new OBJ_Boots();
        this.gp.obj[7].worldX = 37 * this.gp.tileSize;
        this.gp.obj[7].worldY = 42 * this.gp.tileSize;
    }
}
