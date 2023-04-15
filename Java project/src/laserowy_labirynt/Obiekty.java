package laserowy_labirynt;

import java.awt.*;

public abstract class Obiekty {                         //klasa odpowiedzialna za współrzędne obiektów i ich zmiany

    protected int x, y;                                 //współrzędne x i y
    protected Nazwa nazwa;                              //nazwa obiektu
    protected int vX, vY;                               //prędkość przesuwania się obiektu

    public Obiekty(int x, int y, Nazwa nazwa){          //główny konstruktor klasy
        this.x =x;
        this.y = y;
        this.nazwa = nazwa;
    }

    public abstract void tick();                        //metoda "tykania" - stan gry jest sprawdzany i aktualizowany
    public abstract void render(Graphics g);            //metoda renderowania - stan wyrenderowanych obiektów jest sprawdzany i aktualizowany
    public abstract Rectangle getBounds();              //metoda do tworzenia granic kolizji

    public void setX(int x){
        this.x = x;
    }             //zapisywanie wsp x
    public void setY(int y){
        this.y = y;
    }             //zapiswyanie wsp y
    public int getX(){
        return x;
    }                     //zczytywanie wsp x
    public int getY(){
        return y;
    }                     //zczytywanie wsp y
    public void setNazwa(Nazwa nazwa){
        this.nazwa = nazwa;
    }       //zapisywanie nazwy obiektu
    public Nazwa getNazwa(){
        return nazwa;
    }                       //zczytywanie nazwy obiektu
    public void setvX(int vX){                          //zapisywanie prędkości po wsp x
        this.vX = vX;
    }
    public void setvY(int vY){                          //zapisywanie prędkości po wsp y
        this.vY = vY;
    }
    public int getvX(){                                 //zczytywanie prędkości po wsp x
        return vX;
    }
    public int getvY(){                                 //zczytywanie prędkości po wsp y
        return vY;
    }

}

