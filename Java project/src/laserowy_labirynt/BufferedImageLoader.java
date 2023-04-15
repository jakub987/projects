package laserowy_labirynt;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BufferedImageLoader {                                          //klasa odpowiedzialna za ładowania siatki obrazów z odpowiedniej ścieżki

    BufferedImage obrazek;                                                  //zmienna przechowująca ikonę

    public BufferedImage zaladujObrazek(String sciezka){                    //metoda ładująca siatkę obrazów
        try {
            obrazek = ImageIO.read(Objects.requireNonNull(getClass().getResource(sciezka)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return obrazek;
    }

}
