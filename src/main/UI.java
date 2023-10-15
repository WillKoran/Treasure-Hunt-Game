package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;

    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {

        this.gp = gp;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        this.keyImage = key.image;

    }

    public void showMessage(String text) {

        this.message = text;
        this.messageOn = true;

    }

    public void draw(Graphics2D g2) {
        //don't instantiate the font like this because a new instance will be created 60 times per minute
//        g2.setFont(new Font("Arial", Font.PLAIN, 40));

        if (this.gameFinished == true) {

            String text;
            int textLength;
            int x;
            int y;

            g2.setFont(this.arial_40);
            g2.setColor(Color.white);

            text = "You found the treasure";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2)
                    .getWidth();
            x = this.gp.screenWidth / 2 - textLength / 2;
            y = this.gp.screenHeight / 2 - (this.gp.tileSize * 2);
            g2.drawString(text, x, y);

            text = "Your Time is: " + this.dFormat.format(this.playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2)
                    .getWidth();
            x = this.gp.screenWidth / 2 - textLength / 2;
            y = this.gp.screenHeight / 2 + (this.gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(this.arial_80B);
            g2.setColor(Color.cyan);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2)
                    .getWidth();
            x = this.gp.screenWidth / 2 - textLength / 2;
            y = this.gp.screenHeight / 2 + this.gp.tileSize * 2;
            g2.drawString(text, x, y);

            this.gp.gameThread = null;

        } else {
            g2.setFont(this.arial_40);
            g2.setColor(Color.white);
            g2.drawImage(this.keyImage, this.gp.tileSize / 2,
                    this.gp.tileSize / 2, this.gp.tileSize, this.gp.tileSize,
                    null);
            g2.drawString("x" + this.gp.player.hasKey, 74, 65);

            // TIME
            this.playTime += (double) 1 / 60;

            g2.drawString("Time: " + this.dFormat.format(this.playTime),
                    this.gp.tileSize * 11, 65);
            //MESSAGE
            if (this.messageOn == true) {

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(this.message, this.gp.tileSize / 2,
                        this.gp.tileSize * 2);

                this.messageCounter++;

                if (this.messageCounter > 120) {
                    this.messageCounter = 0;
                    this.messageOn = false;
                }
            }
        }
    }
}
