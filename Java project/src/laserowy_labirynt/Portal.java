package laserowy_labirynt;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Portal extends Obiekty{                                    //klasa obiektu "Portal" - ładuje odpowiedni obrazek i wykrywa zderzenie
    Obsluga obsluga;                                                    //zmienna umożliwiająca korzystanie z obsługi
    public boolean trafionyPortal = false;                              //zmienna informująca o trafieniu w portal

    private BufferedImage portal_obrazek;                               //zmienna ikony portalu

    public Portal(int x, int y, Nazwa nazwa, Obsluga obsluga){          //główny konstruktor klasy
        super(x, y, nazwa);
        this.obsluga = obsluga;

        Obrazki ob = new Obrazki(Gra.obrazek);

        portal_obrazek = ob.pobierzObrazek(2, 1, 100, 100);

    }

    public Rectangle getBounds(){
        return new Rectangle(x + 46, y + 10 , 8, 80);
    }       //granice wykrywania zderzenia

    @Override
    public void tick() {                        //odwołanie do tykania w klasie głównej i załadowanie metody kolizja jeśli wystąpiło zderzenie
        kolizja();

    }


    private void kolizja() {
        for(int i = 0; i < obsluga.obiekt.size(); i++){                 //metoda odpowiedzialna za wykrywanie zderzenia

            Obiekty tempObiekt = obsluga.obiekt.get(i);

            if(tempObiekt.getNazwa() == Nazwa.Laser){
                if(getBounds().intersects(tempObiekt.getBounds())){
                    trafionyPortal = true;
                    Laser.kierunek = "skok";
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {                                    //wyrenderowanie obrazka i jego granic kolizji

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());

        g.drawImage(portal_obrazek, x, y, null);
    }

}