package laserowy_labirynt;

import java.awt.*;
import java.util.Objects;

public class Granice extends Obiekty{                                   //klasa wyznaczająca granice planszy
    Obsluga obsluga;                                                    //zmienna umożliwiająca korzystanie z obsługi
    public static boolean koniecPlanszy = false;                        //zmienna informująca o przekroczeniu granicy planszy

    public Granice(int x, int y, Nazwa nazwa, Obsluga obsluga){         //główny konstruktor klasy
        super(x, y, nazwa);
        this.obsluga = obsluga;
    }


    public Rectangle getBounds(){                                       //granice wykrywania zderzenia - rysowane w odpowiednim miejscu w zależności od kierunku przesuwania się lasera
        if(Objects.equals(Laser.kierunek, "gora")){
            return new Rectangle(1, 1, 1000, 8);
        }
        if(Objects.equals(Laser.kierunek, "dol")){
            return new Rectangle(1, 580, 1000, 8);
        }
        if(Objects.equals(Laser.kierunek, "lewo")){
            return new Rectangle(1, 1, 8, 766);
        }
        if(Objects.equals(Laser.kierunek, "prawo")){
            return new Rectangle(1000, 1, 8, 766);
        }
        return new Rectangle();
    }

    @Override
    public void tick() {                        //odwołanie do tykania w klasie głównej i załadowanie metody kolizja jeśli wystąpiło zderzenie
        kolizja();

    }


    private void kolizja() {                                    //metoda odpowiedzialna za wykrywanie zderzenia
        for(int i = 0; i < obsluga.obiekt.size(); i++){

            Obiekty tempObiekt = obsluga.obiekt.get(i);

            if(tempObiekt.getNazwa() == Nazwa.Laser){
                if(getBounds().intersects(tempObiekt.getBounds())){
                    koniecPlanszy = true;
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {                    //narysowanie granic kolizji

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.white);
        g2d.draw(getBounds());

    }

}