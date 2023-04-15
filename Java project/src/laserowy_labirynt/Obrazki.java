package laserowy_labirynt;

import java.awt.image.BufferedImage;

public class Obrazki {                                                   //klasa wprowadzająca podział siatki obrazków na wiersze i kolumny

    private BufferedImage obrazek;                                       //zmienna obrazka

    public Obrazki(BufferedImage ob){                                   //główny konstruktor klasy
        this.obrazek = ob;
    }

    public BufferedImage pobierzObrazek(int kolumna, int wiersz, int szer, int wys){            //pobranie obrazka z siatki według oznaczeń wiersza i kolumny
        BufferedImage lustro = obrazek.getSubimage((wiersz * 100) - 100, (kolumna * 100) - 100, szer, wys);
        return lustro;
    }

}
