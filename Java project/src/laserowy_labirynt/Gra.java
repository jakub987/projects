package laserowy_labirynt;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * glowna klasa programu Gra
 */
public class Gra extends Canvas implements Runnable {

    private static final long serialVersionUID = -1729186985742266786L;

    public int timer = 0;
    /**
     * szerokosc glownego okna programu
     */
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;                                           //wysokość głównego okna programu
    private Thread thread;                                                          //utworzenie wątku programu
    private boolean running = false;                                                //włączenie programu
    private final Obsluga obsluga = new Obsluga();                                  //dodanie obsługi w głównej klasie
    public static BufferedImage obrazek;                                            //zmienna obrazków
    private final Menu menu;                                                        //dodanie menu
    public static int licznikPoziomu = 1;                                           //licznik aktualnego poziomu

    public enum STAN {                                                              //enum z nazwami stanów programu wykorzystywanych w menu
        Menu,
        Pomoc,
        Gra,
        Powtorz,
        NastepyPoziom,
        KoniecGry,
    };

    public STAN stanGry = STAN.Menu;                                                //zmienna stanu programu

    public Gra() {                                                                  //główny konstruktor klasy
        new Okno(WIDTH, HEIGHT, "Laserowy labirynt", this);

        Click click = new Click(obsluga);
        this.addMouseListener(click);

        menu = new Menu(this, obsluga, new Stoper());
        this.addMouseListener(menu);

        this.addKeyListener(new Klawiatura(obsluga));

        BufferedImageLoader zaladuj = new BufferedImageLoader();
        obrazek = zaladuj.zaladujObrazek("/image.png");

        /*if(stanGry == STAN.Gra && licznikPoziomu == 1){
            obsluga.dodajObiekt(new Start_lasera(50, 50, Nazwa.Start_lasera, obsluga));
            obsluga.dodajObiekt(new Lustro(850, 340, Nazwa.Lustro, obsluga));
            obsluga.dodajObiekt(new Lustro_2(230, 340, Nazwa.Lustro_2, obsluga));
            obsluga.dodajObiekt(new Portal(400, 340, Nazwa.Portal, obsluga));
            obsluga.dodajObiekt(new Portal(650, 340, Nazwa.Portal, obsluga));
            obsluga.dodajObiekt(new Balon(60, 340, Nazwa.Balon, obsluga));
            obsluga.dodajObiekt(new Detektor(230, 50, Nazwa.Detektor, obsluga));
        }
        if(stanGry == STAN.Gra && licznikPoziomu == 2){
            System.out.println("poprawne przejście do poziomu 2");
            obsluga.dodajObiekt(new Start_lasera(50, 50, Nazwa.Start_lasera, obsluga));
            obsluga.dodajObiekt(new Lustro(230, 50, Nazwa.Lustro, obsluga));
            obsluga.dodajObiekt(new Lustro_2(230, 340, Nazwa.Lustro_2, obsluga));
            obsluga.dodajObiekt(new Detektor(850, 340, Nazwa.Detektor, obsluga));
        }*/

    }

    public synchronized void start(){                                           //metoda uruchamiająca wątek
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){                                            //metoda zatrzymująca wątek
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){                                                          //metoda zliczająca czas i FPSy
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 100000000;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
                frames++;}

                if(System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
        }
        stop();
    }

    private void tick(){                                               //metoda "tykania" - stan gry jest sprawdzany i aktualizowany
        obsluga.tick();

        if(stanGry == STAN.Gra){
            menu.tick();
        } else if(stanGry == STAN.Menu){
            menu.tick();
        } else if(stanGry == STAN.Powtorz){
            menu.tick();
        } else if(stanGry == STAN.NastepyPoziom){
            menu.tick();
        } else if(stanGry == STAN.KoniecGry){
            menu.tick();
        }
    }

    private void render(){                                          //metoda renderowania - stan wyrenderowanych obiektów jest sprawdzany i aktualizowany
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        obsluga.render(g);

        if(stanGry == STAN.Gra) {
            menu.render(g);
        } else if(stanGry == STAN.Menu || stanGry == STAN.Pomoc){
            menu.render(g);
        } else if(stanGry == STAN.Powtorz){
            menu.render(g);
        } else if(stanGry == STAN.NastepyPoziom){
            menu.render(g);
        } else if(stanGry == STAN.KoniecGry){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) throws IOException {                     //main, uruchomienie gry
        new Gra();
    }

}
