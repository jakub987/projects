package laserowy_labirynt;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {                                    //klasa tworząca menu gry

    private Gra gra;                                                        //zmienna umożliwiająca korzystanie z klasy Gra
    private Obsluga obsluga;                                                //zmienna umożliwiająca korzystanie z obsługi
    private Stoper stoper;                                                  //zmienna umożliwiająca korzystanie ze stopera

    public Menu(Gra gra, Obsluga obsluga, Stoper stoper){                   //główny konstruktor klasy
        this.gra = gra;
        this.obsluga = obsluga;
        this.stoper = stoper;
    }

    public void mousePressed(MouseEvent e){                                 //wykrywanie kliknięcia myszą w odpowiednim okienku menu
        int mx = e.getX();
        int my = e.getY();

        if(gra.stanGry == Gra.STAN.Menu){
            //start
            if(opcjaMenu(mx, my, 387,200,250,100) && Gra.licznikPoziomu == 1){
                gra.stanGry = Gra.STAN.Gra;
                obsluga.dodajObiekt(new Start_lasera(50, 50, Nazwa.Start_lasera, obsluga));
                Lustro.obrot_gd = true;
                obsluga.dodajObiekt(new Lustro(450,50, Nazwa.Lustro, obsluga));
                Lustro_2.obrot_lp = true;
                obsluga.dodajObiekt(new Lustro_2(450,400, Nazwa.Lustro_2, obsluga));
                obsluga.dodajObiekt(new Detektor(730,400, Nazwa.Detektor, obsluga));
                obsluga.dodajObiekt(new Granice(0,0, Nazwa.Granice, obsluga));

            } else {
                gra.stanGry = Gra.STAN.NastepyPoziom;
            }

            //pomoc
            if(opcjaMenu(mx, my, 387,330,250,100)){
                gra.stanGry = Gra.STAN.Pomoc;
            }

            //wyjście
            if(opcjaMenu(mx, my,387,460,250,100)){
                System.exit(1);
            }

        }

        //powrót z pomocy
        if(gra.stanGry == Gra.STAN.Pomoc && Gra.licznikPoziomu == 1){
            if(opcjaMenu(mx, my, 422,640,180,70)){
                gra.stanGry = Gra.STAN.Menu;
            }
        } else if(gra.stanGry == Gra.STAN.Pomoc && Gra.licznikPoziomu > 1){
            if(opcjaMenu(mx, my, 422,640,180,70)){
                gra.stanGry = Gra.STAN.NastepyPoziom;
            }
        }

        //powrót do menu/pomocy z gry
        if(gra.stanGry == Gra.STAN.Gra){
            if(opcjaMenu(mx, my, 146,608,200,60)){
                gra.stanGry = Gra.STAN.Menu;
            } else if(opcjaMenu(mx, my, 412,608,200,60)) {
                gra.stanGry = Gra.STAN.Pomoc;
            }
        }

        //powrót do poziomu/pomocy po zestrzeleniu balona
        if(gra.stanGry == Gra.STAN.Powtorz){
            if(opcjaMenu(mx, my, 372,250,280,100)){
                gra.stanGry = Gra.STAN.Menu;
            } else if(opcjaMenu(mx, my, 372,430,280,100)) {
                gra.stanGry = Gra.STAN.Pomoc;
            }
        }

        //przejście do następnego poziomu
        if(gra.stanGry == Gra.STAN.NastepyPoziom && Gra.licznikPoziomu == 2){
            if(opcjaMenu(mx, my, 352,250,320,100)){
                gra.stanGry = Gra.STAN.Gra;
                obsluga.dodajObiekt(new Start_lasera(50, 280, Nazwa.Start_lasera, obsluga));
                Laser.kierunek = "prawo";
                obsluga.dodajObiekt(new Lustro(450, 280, Nazwa.Lustro, obsluga));
                obsluga.dodajObiekt(new Portal(350, 280, Nazwa.Portal, obsluga));
                obsluga.dodajObiekt(new Portal(550, 280, Nazwa.Portal, obsluga));
                obsluga.dodajObiekt(new Detektor(450, 100, Nazwa.Detektor, obsluga));
                obsluga.dodajObiekt(new Granice(0,0, Nazwa.Granice, obsluga));
            } else if(opcjaMenu(mx, my, 352,430,320,100)) {
                gra.stanGry = Gra.STAN.Menu;
            }
        }

        if(gra.stanGry == Gra.STAN.NastepyPoziom && Gra.licznikPoziomu == 3){
            if(opcjaMenu(mx, my, 352,250,320,100)){
                gra.stanGry = Gra.STAN.Gra;
                obsluga.dodajObiekt(new Start_lasera(50, 50, Nazwa.Start_lasera, obsluga));
                Laser.kierunek = "prawo";
                obsluga.dodajObiekt(new Lustro(230, 50, Nazwa.Lustro, obsluga));
                Lustro_2.obrot_lp = true;
                obsluga.dodajObiekt(new Lustro_2(230, 340, Nazwa.Lustro_2, obsluga));
                obsluga.dodajObiekt(new Portal(425, 200, Nazwa.Portal, obsluga));
                obsluga.dodajObiekt(new Portal(625, 200, Nazwa.Portal, obsluga));
                obsluga.dodajObiekt(new Balon(525, 340, Nazwa.Balon, obsluga));
                obsluga.dodajObiekt(new Detektor(850, 340, Nazwa.Detektor, obsluga));
                obsluga.dodajObiekt(new Granice(0,0, Nazwa.Granice, obsluga));
            } else if(opcjaMenu(mx, my, 352,430,320,100)) {
                gra.stanGry = Gra.STAN.Menu;
            }
        }

        //przejście do następnego poziomu
        if(gra.stanGry == Gra.STAN.KoniecGry){
            if(opcjaMenu(mx, my, 352,250,320,100)){
                System.exit(1);
            }
        }

    }

    public void mouseReleased(MouseEvent e){

    }

    private boolean opcjaMenu(int mx, int my, int x, int y, int width, int height){     //sprawdzenie czy przycisk myszy został kliknięty w odpowiednim miejscu
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            } else return false;
        } else return false;
    }

    public void tick(){                                 //sprawdzenie kolizji z obiektami przerywającymi grę
        if(Balon.trafionyBalon){
            gra.stanGry = Gra.STAN.Powtorz;
            Balon.trafionyBalon = false;
        }
        if(Detektor.wykrycie){
            if(Gra.licznikPoziomu == 4){
                gra.stanGry = Gra.STAN.KoniecGry;
            }else {gra.stanGry = Gra.STAN.NastepyPoziom;
                Detektor.wykrycie = false;
            }

        }
        if(Granice.koniecPlanszy){
            gra.stanGry = Gra.STAN.Powtorz;
            Granice.koniecPlanszy = false;
        }
    }

    public void render(Graphics g){                     //wyrenderowanie odpowiednich okienek opcji i napisów
        if(gra.stanGry == Gra.STAN.Menu){

            obsluga.wyczyscObiekty();

            Font font1 = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 30);

            g.setColor(Color.black);
            g.setFont(font1);
            g.drawString("Menu", 450,120);

            g.setFont(font2);
            g.drawRect(387,200,250,100);
            g.drawString("Rozpocznij grę", 407,260);

            g.drawRect(387,330,250,100);
            g.drawString("Pomoc", 465,390);

            g.drawRect(387,460,250,100);
            g.drawString("Wyjście", 460,520);

        } else if(gra.stanGry == Gra.STAN.Pomoc){
            obsluga.wyczyscObiekty();

            Font font1 = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 15);
            Font font3 = new Font("arial", 1, 30);

            g.setColor(Color.black);
            g.setFont(font1);
            g.drawString("Pomoc", 430,60);

            g.setFont(font3);
            g.drawString("Celem gry jest odpowiednie ustawienie elementów",120,110);
            g.drawString("na planszy tak, aby doprowadzić laser do detektora.",120,140);

            g.setFont(font2);
            obsluga.dodajObiekt(new Start_lasera(100, 150, Nazwa.Start_lasera, obsluga));
            g.drawString("Źródło lasera - kliknij LPM, aby wystrzelić laser.",250,205);
            Lustro.obrot_gd = false;
            obsluga.dodajObiekt(new Lustro(100, 250, Nazwa.Lustro, obsluga));
            g.drawString("Lustro - kliknij LPM, aby obrócić.",250,305);
            obsluga.dodajObiekt(new Portal(100, 350, Nazwa.Portal, obsluga));
            g.drawString("Portal - przenosi wiązkę lasera do pobliskiego portalu.",250,395);
            g.drawString("Niektóre z nich możesz przesuwać za pomocą strzałek.",250,415);
            obsluga.dodajObiekt(new Balon(100, 450, Nazwa.Balon, obsluga));
            g.drawString("Balon - jeśli trafisz laserem ten obiekt, będziesz powtarzać poziom.",250,505);
            obsluga.dodajObiekt(new Detektor(100, 550, Nazwa.Detektor, obsluga));
            g.drawString("Detektor - Twój cel w każdym poziomie - doprowadzić do niego laser.",250,605);

            g.setFont(font3);
            g.drawRect(422,640,180,70);
            g.drawString("Powrót", 460,685);

        } else if(gra.stanGry == Gra.STAN.Gra){
            Font font2 = new Font("arial", 1, 30);

            g.setColor(Color.black);
            g.setFont(font2);

            g.drawRect(80,588,864,100);

            g.drawRect(146,608,200,60);
            g.drawString("Menu", 205,648);

            g.drawRect(412,608,200,60);
            g.drawString("Pomoc", 465, 648);

            g.drawString("Aktualny poziom: " + Gra.licznikPoziomu, 650,648);

        } else if(gra.stanGry == Gra.STAN.Powtorz){

            obsluga.wyczyscObiekty();

            Font font1 = new Font("arial", 1, 30);
            Font font2 = new Font("arial", 1, 30);

            g.setColor(Color.red);
            g.setFont(font1);

            g.drawString("Balon został zestrzelony lub laser przesunął się poza planszę.", 52,120);
            g.drawString("Poziom niezaliczony. Wróć do menu, aby powtórzyć poziom.", 52,160);

            g.setFont(font2);
            g.drawRect(372,250,280,100);
            g.drawString("Powrót do menu", 396,307);

            g.drawRect(372,430,280,100);
            g.drawString("Pomoc", 460,487);

        } else if(gra.stanGry == Gra.STAN.NastepyPoziom){

            obsluga.wyczyscObiekty();

            Font font1 = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 30);

            g.setColor(Color.red);
            g.setFont(font1);

            g.drawString("Gratulacje! Poziom zakończony.", 145,130);

            g.setFont(font2);
            g.drawRect(352,250,320,100);
            g.drawString("Rozpocznij następny", 365,290);
            g.drawString("poziom", 365, 327);

            g.drawRect(352,430,320,100);
            g.drawString("Menu", 472,487);

            g.drawString("Aktualny czas twojej gry wynosi: " + stoper.elapsedTime() + "sekund.", 150,600);

        } else if(gra.stanGry == Gra.STAN.KoniecGry){

            obsluga.wyczyscObiekty();

            Font font1 = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 30);

            g.setColor(Color.red);
            g.setFont(font1);

            g.drawString("Gratulacje! Gra ukończona.", 200,130);

            g.setFont(font2);
            g.drawRect(387,200,250,100);
            g.drawString("Wyjście", 460,260);
            g.drawString("Laczny czas twojej gry wynosi: " + stoper.elapsedTime() + "sekund.", 150,600);
        }

    }

}
