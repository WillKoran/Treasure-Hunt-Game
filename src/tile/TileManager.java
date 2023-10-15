package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        this.tile = new Tile[10];
        this.mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        this.getTileImage();
        this.loadMap("/maps/World01");
    }

    private void getTileImage() {

        try {

            this.tile[3] = new Tile();
            this.tile[3].image = ImageIO.read(
                    this.getClass().getResourceAsStream("/tiles/earth.png"));

            this.tile[0] = new Tile();
            this.tile[0].image = ImageIO.read(
                    this.getClass().getResourceAsStream("/tiles/grass.png"));

            this.tile[5] = new Tile();
            this.tile[5].image = ImageIO.read(
                    this.getClass().getResourceAsStream("/tiles/sand.png"));

            this.tile[4] = new Tile();
            this.tile[4].image = ImageIO.read(
                    this.getClass().getResourceAsStream("/tiles/tree.png"));
            this.tile[4].collision = true;

            this.tile[1] = new Tile();
            this.tile[1].image = ImageIO.read(
                    this.getClass().getResourceAsStream("/tiles/wall.png"));
            this.tile[1].collision = true;

            this.tile[2] = new Tile();
            this.tile[2].image = ImageIO.read(
                    this.getClass().getResourceAsStream("/tiles/water.png"));
            this.tile[2].collision = true;

        } catch (IOException e) {
            System.out.println("UNABLE TO LOAD TILE PNG!");
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {

        try {
            InputStream is = this.getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {

                String line = br.readLine();

                while (col < this.gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    this.mapTileNum[col][row] = num;
                    col++;
                }
                if (col == this.gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("UNABLE TO LOAD MAP FILE!");
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < this.gp.maxWorldCol
                && worldRow < this.gp.maxWorldRow) {

            int tileNum = this.mapTileNum[worldCol][worldRow];

            int worldX = worldCol * this.gp.tileSize;
            int worldY = worldRow * this.gp.tileSize;
            int screenX = worldX - this.gp.player.worldX
                    + this.gp.player.screenX;
            int screenY = worldY - this.gp.player.worldY
                    + this.gp.player.screenY;

            if (worldX + this.gp.tileSize > this.gp.player.worldX
                    - this.gp.player.screenX
                    && worldX - this.gp.tileSize < this.gp.player.worldX
                            + this.gp.player.screenX
                    && worldY + this.gp.tileSize > this.gp.player.worldY
                            - this.gp.player.screenY
                    && worldY - this.gp.tileSize < this.gp.player.worldY
                            + this.gp.player.screenY) {
                g2.drawImage(this.tile[tileNum].image, screenX, screenY,
                        this.gp.tileSize, this.gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == this.gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }

        }

    }
}
