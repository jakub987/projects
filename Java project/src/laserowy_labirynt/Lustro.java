package laserowy_labirynt;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lustro extends Obiekty{                             //klasa obiektu "Lustro" - ładuje odpowiedni obrazek i wykrywa zderzenie

    Obsluga obsluga;                                             //zmienna umożliwiająca korzystanie z obsługi
    public boolean trafioneLustro = false;                       //zmienna informująca o trafieniu w lustro
    public static boolean obrot_gd = false;                      //zmienna do obrotu lustra góra/dół
    private BufferedImage lustro_obrazek;                        //zmienna ikony lustra

    public Lustro(int x, int y, Nazwa nazwa, Obsluga obsluga){   //główny konstruktor klasy
        super(x, y, nazwa);
        this.obsluga = obsluga;

        Obrazki ob = new Obrazki(Gra.obrazek);

        lustro_obrazek = ob.pobierzObrazek(1, 2, 100, 100);

    }

    public Rectangle getBounds(){
        return new Rectangle(x + 50, y + 50, 8, 8);
    }       //granice wykrywania zderzenia

    private void obrot() {                              //metoda ładująca odpowiedni obrazek w przypadku obrócenia lustra
        Obrazki ob = new Obrazki(Gra.obrazek);
        if(!obrot_gd){
            lustro_obrazek = ob.pobierzObrazek(1, 2, 100, 100);
        }
        if(obrot_gd){
            lustro_obrazek = ob.pobierzObrazek(1, 1, 100, 100);
        }
    }

    @Override
    public void tick() {                                //metoda obracająca lustro oraz zmieniająca kierunek lasera góra/dół
        obrot();
        kolizja();
        if (trafioneLustro && !obrot_gd){
            Laser.kierunek = "dol";
        }
        if (trafioneLustro && obrot_gd){
            Laser.kierunek = "gora";
        }
    }

    private void kolizja() {                                            //metoda odpowiedzialna za wykrywanie zderzenia
        for(int i = 0; i < obsluga.obiekt.size(); i++){

            Obiekty tempObiekt = obsluga.obiekt.get(i);

            if(tempObiekt.getNazwa() == Nazwa.Laser){
                if(getBounds().intersects(tempObiekt.getBounds())){
                    trafioneLustro = true;
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {                                //wyrenderowanie obrazka i jego granic kolizji

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());

        g.drawImage(lustro_obrazek, x, y, null);
    }

}