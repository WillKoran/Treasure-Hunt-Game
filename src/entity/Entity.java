package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage DOWN0, DOWN1, DOWN2, DOWN3, LEFT0, LEFT1, LEFT2, LEFT3,
            RIGHT0, RIGHT1, RIGHT2, RIGHT3, UP0, UP1, UP2, UP3;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 0;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int solidAreaDefaultX, solidAreaDefaultY;

}
