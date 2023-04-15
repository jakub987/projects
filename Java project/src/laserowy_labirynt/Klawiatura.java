package laserowy_labirynt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Klawiatura extends KeyAdapter {                        //klasa umożliwiająca przesuwanie portali

    private Obsluga obsluga;                                        //zmienna umożliwiająca korzystanie z obsługi
    public Klawiatura(Obsluga obsluga){
        this.obsluga = obsluga;
    }  //główny konstruktor klasy

    public void keyPressed(KeyEvent e){                             //metoda wykrywająca wciśnięcie klawisza strzałki i przesuwająca obiekt w górę lub w dół
        int key = e.getKeyCode();

        for(int i = 0; i < obsluga.obiekt.size(); i++){
            Obiekty tempObject = obsluga.obiekt.get(i);

            if(tempObject.getNazwa() == Nazwa.Portal){
                if(key == KeyEvent.VK_UP) tempObject.setY(tempObject.getY() - 2);
                if(key == KeyEvent.VK_DOWN) tempObject.setY(tempObject.getY() + 2);
            }
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

    }

}
