package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {

    public OBJ_Key() {

        this.name = "key";
        try {
            this.image = ImageIO.read(
                    this.getClass().getResourceAsStream("/objects/key.png"));

        } catch (IOException e) {
            System.out.println("KEY PNG NOT FOUND");
            e.printStackTrace();
        }
    }

}
