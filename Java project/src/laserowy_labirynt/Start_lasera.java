package laserowy_labirynt;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Start_lasera extends Obiekty{                                      //klasa obiektu źródła lasera - ładuje odpowiedni obrazek i strzela laserem

    Obsluga obsluga;                                                            //zmienna umożliwiająca korzystanie z obsługi
    public static boolean startuj = false;                                      //zmienna informująca o wypuszczeniu wiązki

    private BufferedImage laser_obrazek;                                        //zmienna ikony źródła lasera

    public Start_lasera(int x, int y, Nazwa nazwa, Obsluga obsluga){            //główny konstruktor klasy
        super(x, y, nazwa);
        this.obsluga = obsluga;

        Obrazki ob = new Obrazki(Gra.obrazek);

        laser_obrazek = ob.pobierzObrazek(3, 2, 100, 100);

    }


    @Override
    public void tick() {                    //odwołanie do tykania w klasie głównej i wypuszczenie lasera
        startuj();

    }

    private void startuj() {                                //metoda odpowiedzialna za wypuszczenie lasera
        if(startuj){
            for(int i = 0; i < obsluga.obiekt.size(); i++) {
                Obiekty tempObject = obsluga.obiekt.get(i);

                if (tempObject.getNazwa() == Nazwa.Start_lasera) {
                    obsluga.dodajObiekt(new Laser(tempObject.getX() + 50, tempObject.getY() + 50, Nazwa.Laser, obsluga));
                    startuj = false;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {                        //wyrenderowanie obrazka

        g.drawImage(laser_obrazek, x, y, null);

    }

    @Override
    public Rectangle getBounds() {return new Rectangle(x, y, 0, 0);}

}