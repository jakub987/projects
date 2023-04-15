package laserowy_labirynt;

import java.awt.*;
import java.util.LinkedList;

public class Obsluga {                                                  //klasa obsługująca obiekty

    LinkedList<Obiekty> obiekt = new LinkedList<Obiekty>();                   //utworzenie listy do której będą dodawane obiekty

    public void tick(){                                                 //umieszczenie obiektu w ticku
        for(int i = 0; i < obiekt.size(); i++){
            Obiekty tempObiekt = obiekt.get(i);

            tempObiekt.tick();
        }
    }
    public void render(Graphics g){                                     //wyrenderowanie obieku
        for(int i = 0; i < obiekt.size(); i++){
            Obiekty tempObiekt = obiekt.get(i);

            tempObiekt.render(g);
        }
    }

    public void wyczyscObiekty(){                                       //metoda do usunięcia wszystkich obiektów z planszy
        for(int i = 0; i < obiekt.size(); i++) {
            Obiekty tempObiekt = obiekt.get(i);

            if(tempObiekt.getNazwa() != Nazwa.Granice) usunObiekt(tempObiekt);
        }
    }

    public void dodajObiekt(Obiekty obiekt){
        this.obiekt.add(obiekt);
    }       //metoda umożliwiająca dodanie obiektu
    public void usunObiekt(Obiekty obiekt){
        this.obiekt.remove(obiekt);
    }     //meto umożliwiająca usunięcie obiektu

}
