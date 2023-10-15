package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {

    public OBJ_Door() {

        this.name = "door";
        try {
            this.image = ImageIO.read(
                    this.getClass().getResourceAsStream("/objects/door.png"));

        } catch (IOException e) {
            System.out.println("DOOR PNG NOT FOUND!");
            e.printStackTrace();
        }
        this.collision = true;
    }

}
