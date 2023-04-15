package laserowy_labirynt;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Balon extends Obiekty{                                     //klasa obiektu "Balon" - ładuje odpowiedni obrazek i wykrywa zderzenie

    Obsluga obsluga;                                                    //zmienna umożliwiająca korzystanie z obsługi
    public static boolean trafionyBalon = false;                        //zmienna informująca o trafieniu w balon

    private BufferedImage balon_obrazek;                                //zmienna ikony balona

    public Balon(int x, int y, Nazwa nazwa, Obsluga obsluga){           //główny konstruktor klasy
        super(x, y, nazwa);
        this.obsluga = obsluga;

        Obrazki ob = new Obrazki(Gra.obrazek);

        balon_obrazek = ob.pobierzObrazek(3, 1, 100, 100);

    }


    public Rectangle getBounds(){
        return new Rectangle(x + 50, y + 50, 8, 8);
    }       //granice wykrywania zderzenia

    @Override
    public void tick() {                                                //odwołanie do tykania w klasie głównej i załadowanie metody kolizja jeśli wystąpiło zderzenie
        kolizja();

    }


    private void kolizja() {                                            //metoda odpowiedzialna za wykrywanie zderzenia
        for(int i = 0; i < obsluga.obiekt.size(); i++){

            Obiekty tempObiekt = obsluga.obiekt.get(i);

            if(tempObiekt.getNazwa() == Nazwa.Laser){
                if(getBounds().intersects(tempObiekt.getBounds())){
                    trafionyBalon = true;
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {                                    //wyrenderowanie obrazka i jego granic kolizji

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());

        g.drawImage(balon_obrazek, x, y, null);
    }

}