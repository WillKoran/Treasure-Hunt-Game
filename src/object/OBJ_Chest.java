package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {

    public OBJ_Chest() {

        this.name = "chest";
        try {
            this.image = ImageIO.read(
                    this.getClass().getResourceAsStream("/objects/chest.png"));

        } catch (IOException e) {
            System.out.println("CHEST PNG NOT FOUND");
            e.printStackTrace();
        }
    }

}
