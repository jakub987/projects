package laserowy_labirynt;

import java.awt.*;

public class Detektor extends Obiekty{                                      //klasa obiektu "Detektor" - rysuje koło i wykrywa zderzenie
    Obsluga obsluga;                                                        //zmienna umożliwiająca korzystanie z obsługi
    public static boolean wykrycie = false;                                 //zmienna informująca o trafieniu w detektor

    public Detektor(int x, int y, Nazwa nazwa, Obsluga obsluga){            //główny konstruktor klasy
        super(x, y, nazwa);
        this.obsluga = obsluga;
    }

    public Rectangle getBounds(){
        return new Rectangle(x + 50, y + 50, 8, 8);
    }       //granice wykrywania zderzenia

    @Override
    public void tick() {                                        //odwołanie do tykania w klasie głównej i załadowanie metody kolizja jeśli wystąpiło zderzenie
        kolizja();

    }


    private void kolizja() {                                                //metoda odpowiedzialna za wykrywanie zderzenia i zmianę licznika poziomu
        for(int i = 0; i < obsluga.obiekt.size(); i++){

            Obiekty tempObiekt = obsluga.obiekt.get(i);

            if(tempObiekt.getNazwa() == Nazwa.Laser){
                if(getBounds().intersects(tempObiekt.getBounds())){
                    wykrycie = true;
                    Gra.licznikPoziomu++;
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {                                        //narysowanie koła i jego granic kolizji

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());

        g.fillOval(x+15, y+15, 70,70);
    }

}