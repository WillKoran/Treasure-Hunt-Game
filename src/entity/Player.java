package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // player collision box is smaller than the size of the tile
        // this is ideal for player movement mechanics
        this.solidArea = new Rectangle();
        this.solidArea.x = 10;
        this.solidArea.y = 18;
        this.solidArea.width = 28;
        this.solidArea.height = 30;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        this.setDefaultValues();
        this.getPLayerImage();
    }

    public void setDefaultValues() {

        this.worldX = this.gp.tileSize * 23;
        this.worldY = this.gp.tileSize * 21;
        this.speed = 4;
        this.direction = ("down");
    }

    public void getPLayerImage() {

        try {
            this.DOWN0 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/DOWN0.png"));
            this.DOWN1 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/DOWN1.png"));

            this.DOWN2 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/DOWN2.png"));
            this.DOWN3 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/DOWN3.png"));
            this.LEFT0 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/LEFT0.png"));
            this.LEFT1 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/LEFT1.png"));
            this.LEFT2 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george//LEFT2.png"));
            this.LEFT3 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/LEFT3.png"));
            this.RIGHT0 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/RIGHT0.png"));
            this.RIGHT1 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/RIGHT1.png"));
            this.RIGHT2 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/RIGHT2.png"));
            this.RIGHT3 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/RIGHT3.png"));
            this.UP0 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/UP0.png"));
            this.UP1 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/UP1.png"));
            this.UP2 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/UP2.png"));
            this.UP3 = ImageIO.read(
                    this.getClass().getResourceAsStream("/george/UP3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {

        if (this.keyH.upPressed == true || this.keyH.downPressed == true
                || this.keyH.leftPressed == true
                || this.keyH.rightPressed == true) {
            if (this.keyH.upPressed == true) {
                this.direction = "up";
            } else if (this.keyH.downPressed == true) {
                this.direction = "down";
            } else if (this.keyH.leftPressed == true) {
                this.direction = "left";
            } else if (this.keyH.rightPressed == true) {
                this.direction = "right";
            }

            // CHECK TILE COLLISION
            this.collisionOn = false;
            this.gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = this.gp.cChecker.checkObject(this, true);
            this.pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (this.collisionOn == false) {
                switch (this.direction) {
                    case "up":
                        this.worldY -= this.speed;
                        break;

                    case "down":
                        this.worldY += this.speed;
                        break;

                    case "left":
                        this.worldX -= this.speed;
                        break;

                    case "right":
                        this.worldX += this.speed;
                        break;
                }
            }
            this.spriteCounter++;
            if (this.spriteCounter > 8) {
                if (this.spriteNum == 0) {
                    this.spriteNum = 1;
                } else if (this.spriteNum == 1) {
                    this.spriteNum = 2;
                } else if (this.spriteNum == 2) {
                    this.spriteNum = 3;
                } else if (this.spriteNum == 3) {
                    this.spriteNum = 0;
                }
                this.spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = this.gp.obj[i].name;

            switch (objectName) {
                case "key":

                    this.gp.playSFX(1);
                    this.hasKey++;
                    this.gp.obj[i] = null;
                    this.gp.ui.showMessage("You got a key!");
                    break;
                case "door":
                    if (this.hasKey > 0) {
                        this.gp.playSFX(3);
                        this.gp.obj[i] = null;
                        this.hasKey--;
                        this.gp.ui.showMessage("You opened the door!");
                    } else {
                        this.gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "boots":
                    this.gp.playSFX(2);
                    this.speed += 2;
                    this.gp.obj[i] = null;
                    this.gp.ui.showMessage("Faster!");
                    break;

                case "chest":
                    this.gp.ui.gameFinished = true;
                    this.gp.stopMusic();
                    this.gp.playSFX(4);

                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (this.direction) {

            case "up":
                if (this.spriteNum == 0) {
                    image = this.UP0;
                }
                if (this.spriteNum == 1) {
                    image = this.UP1;
                }
                if (this.spriteNum == 2) {
                    image = this.UP2;
                }
                if (this.spriteNum == 3) {
                    image = this.UP3;
                }
                break;

            case "down":
                if (this.spriteNum == 0) {
                    image = this.DOWN0;
                }
                if (this.spriteNum == 1) {
                    image = this.DOWN1;
                }
                if (this.spriteNum == 2) {
                    image = this.DOWN2;
                }
                if (this.spriteNum == 3) {
                    image = this.DOWN3;
                }

                break;

            case "left":
                if (this.spriteNum == 0) {
                    image = this.LEFT0;
                }
                if (this.spriteNum == 1) {
                    image = this.LEFT1;
                }
                if (this.spriteNum == 2) {
                    image = this.LEFT2;
                }
                if (this.spriteNum == 3) {
                    image = this.LEFT3;
                }

                break;

            case "right":
                if (this.spriteNum == 0) {
                    image = this.RIGHT0;
                }
                if (this.spriteNum == 1) {
                    image = this.RIGHT1;
                }
                if (this.spriteNum == 2) {
                    image = this.RIGHT2;
                }
                if (this.spriteNum == 3) {
                    image = this.RIGHT3;
                }
                break;
        }
        g2.drawImage(image, this.screenX, this.screenY, this.gp.tileSize,
                this.gp.tileSize, null);

    }
}
