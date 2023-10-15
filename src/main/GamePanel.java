package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // implements runnable is necessary for threat

    /**
     *
     */

    // Screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // scales up the image by 3 for larger screens
    final public int tileSize = this.originalTileSize * this.scale; // 48x48 tile
    final public int maxScreenCol = 16;
    final public int maxScreenRow = 12;
    final public int screenWidth = this.tileSize * this.maxScreenCol; //768 pixels
    final public int screenHeight = this.tileSize * this.maxScreenRow; // 576 pixels;

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
//    public final int worldWidth = this.tileSize * this.maxWorldCol;
//    public final int worldHeight = this.tileSize * this.maxWorldRow;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    Sound sfx = new Sound();
    KeyHandler keyH = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, this.keyH);
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {

        this.setPreferredSize(
                new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {

        this.aSetter.setObject();

        this.playMusic(0);
    }

    public void startGameThread() {

        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / this.FPS; // 0.01666 seconds

        // alternate loop
//        double nextDrawTime = System.nanoTime() + drawInterval;

        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (this.gameThread != null) {

//            long currentTime = System.nanoTime();

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                this.update();
                this.repaint();
                delta--;

            }

            if (timer >= 1000000000) {
                // System.out.println("FPS: " + drawCount);
                timer = 0;

            }

            // 1 UPDATE: update information such as character positions
            //this.update();

            // 2 DRAW: draw the screen with the updated information
            //this.repaint(); // how to call the paint method

//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//
//                if (remainingTime < 0) {
//                    remainingTime = 0; // just in case
//                }
//
//                Thread.sleep((long) remainingTime); // this method recieves time as miliseconds
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//
//                e.printStackTrace();
//            }
        }

    }

    public void update() {

        this.player.update();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // make sure to draw tiles before character
        // TILE
        this.tileM.draw(g2);

        //OBJECT
        for (int i = 0; i < this.obj.length; i++) {
            if (this.obj[i] != null) {
                this.obj[i].draw(g2, this);
            }
        }

        // PLAYER
        this.player.draw(g2);

        // UI
        this.ui.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {

        this.music.setFile(i);
        this.music.play();
        this.music.loop();
    }

    public void stopMusic() {

        this.music.stop();
    }

    public void playSFX(int i) {

        this.sfx.setFile(i);
        this.sfx.play();
    }
}
