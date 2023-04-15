package laserowy_labirynt;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



class Click implements MouseListener {                                  //klasa wykrywająca kliknięcie myszką

    private Obsluga obsluga;                                        //zmienna umożliwiająca korzystanie z obsługi
    public Click(Obsluga obsluga){                                  //główny konstruktor klasy
        this.obsluga = obsluga;
    }

    @Override
    public void mouseClicked(MouseEvent e) {                            //metoda wykrywająca kliknięcie w danym obszarze - dzięki niej można wejść w interakcję z obiektami

        for(int i = 0; i < obsluga.obiekt.size(); i++){
            Obiekty tempObject = obsluga.obiekt.get(i);

            if(tempObject.getNazwa() == Nazwa.Start_lasera){
                if(e.getX() >= tempObject.getX() && e.getX() <= tempObject.getX() + 100 && e.getY() >= tempObject.getY() && e.getY() <= tempObject.getY() + 100){
                    Start_lasera.startuj = true;
                }
            }

            if(tempObject.getNazwa() == Nazwa.Lustro){
                if(e.getX() >= tempObject.getX() && e.getX() <= tempObject.getX() + 100 && e.getY() >= tempObject.getY() && e.getY() <= tempObject.getY() + 100){
                    Lustro.obrot_gd = !Lustro.obrot_gd;
                }
            }

            if(tempObject.getNazwa() == Nazwa.Lustro_2){
                if(e.getX() >= tempObject.getX() && e.getX() <= tempObject.getX() + 100 && e.getY() >= tempObject.getY() && e.getY() <= tempObject.getY() + 100){
                    Lustro_2.obrot_lp = !Lustro_2.obrot_lp;
                }
            }

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}