package laserowy_labirynt;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lustro_2 extends Obiekty{                                  //klasa obiektu "Lustro2" - ładuje odpowiedni obrazek i wykrywa zderzenie

    Obsluga obsluga;                                                    //zmienna umożliwiająca korzystanie z obsługi
    public boolean trafioneLustro2 = false;                             //zmienna informująca o trafieniu w lustro
    public static boolean obrot_lp = false;                             //zmienna do obrotu lustra lewo/prawo
    private BufferedImage lustro_obrazek;                               //zmienna ikony lustra

    public Lustro_2(int x, int y, Nazwa nazwa, Obsluga obsluga){        //główny konstruktor klasy
        super(x, y, nazwa);
        this.obsluga = obsluga;

        Obrazki ob = new Obrazki(Gra.obrazek);

        lustro_obrazek = ob.pobierzObrazek(1, 2, 100, 100);

    }

    public Rectangle getBounds(){
        return new Rectangle(x + 50, y + 50, 8, 8);
    }       //granice wykrywania zderzenia

    private void obrot() {                                          //metoda ładująca odpowiedni obrazek w przypadku obrócenia lustra
        Obrazki ob = new Obrazki(Gra.obrazek);
        if(!obrot_lp){
            lustro_obrazek = ob.pobierzObrazek(1, 2, 100, 100);
        }
        if(obrot_lp){
            lustro_obrazek = ob.pobierzObrazek(1, 1, 100, 100);
        }
    }

    @Override
    public void tick() {                                    //metoda obracająca lustro oraz zmieniająca kierunek lasera lewo/prawo
        obrot();
        kolizja();
        if (trafioneLustro2 && !obrot_lp){
            Laser.kierunek = "prawo";
        }
        if (trafioneLustro2 && obrot_lp){
            Laser.kierunek = "lewo";
        }
    }

    private void kolizja() {                                                    //metoda odpowiedzialna za wykrywanie zderzenia
        for(int i = 0; i < obsluga.obiekt.size(); i++){

            Obiekty tempObiekt = obsluga.obiekt.get(i);

            if(tempObiekt.getNazwa() == Nazwa.Laser){
                if(getBounds().intersects(tempObiekt.getBounds())){
                    trafioneLustro2 = true;
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {                            //wyrenderowanie obrazka i jego granic kolizji

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());

        g.drawImage(lustro_obrazek, x, y, null);
    }


}