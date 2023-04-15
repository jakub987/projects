package laserowy_labirynt;

import java.awt.*;

public class Laser extends Obiekty{                                     //klasa odpowiedzialna za wiązkę lasera i jego ruch

    Obsluga obsluga;                                                    //zmienna umożliwiająca korzystanie z obsługi
    public static String kierunek = "prawo";                            //zmienna przechowująca aktualny kierunek przesuwania się lasera


    public Laser(int x, int y, Nazwa nazwa, Obsluga obsluga){           //główny konstruktor klasy
        super(x, y, nazwa);
        this.obsluga = obsluga;

        vX = 8;
        vY = 8;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 1, 1);
    }           //granice wykrywania zderzenia

    @Override
    public void tick() {                                //metoda zmieniająca kierunek wiązki lasera w zależności od stanu zmiennej "kierunek"


        if(kierunek.equals("dol")){
            y += vY;
        } else {
            if(kierunek.equals("gora")){
                y -= vY;
            }
        }
        if(kierunek.equals("prawo")){
            x += vX;
        } else {
            if (kierunek.equals("lewo")) {
                x -= vX;
            }
        }
        if(kierunek.equals("skok")){
            x += vX + 200;
            kierunek = "prawo";
        }

    }


    @Override
    public void render(Graphics g) {                //wyrenderowanie wiązki o odpowiedniej orientacji w zależności od kierunku przesuwania się lub przesunięcie w przypadku trafienia portalu

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.black);
        g2d.draw(getBounds());

        if(kierunek.equals("dol") || kierunek.equals("gora")){
            g.setColor(Color.red);
            g.fillRect(x, y, 4, 30);
        }else if(kierunek.equals("prawo") || kierunek.equals("lewo")){
            g.setColor(Color.red);
            g.fillRect(x, y, 30, 4);
        }
        if(kierunek.equals("skok")){
            g.setColor(Color.red);
            g.fillRect(x, y, 30, 4);
        }
    }

}
