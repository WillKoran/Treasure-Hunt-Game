package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject {

    public OBJ_Boots() {

        this.name = "boots";
        try {
            this.image = ImageIO.read(
                    this.getClass().getResourceAsStream("/objects/boots.png"));

        } catch (IOException e) {
            System.out.println("BOOTS PNG NOT FOUND!");
            e.printStackTrace();
        }
        this.collision = true;
    }

}
