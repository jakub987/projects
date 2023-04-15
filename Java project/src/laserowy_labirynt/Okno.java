package laserowy_labirynt;

import javax.swing.*;
import java.awt.*;

public class Okno extends Canvas {                                          //klasa głównego okna programu

    private static final long serialVersionUID = -1329501289791014037L;

    public Okno(int width, int high, String title, Gra gra){                //główny konstruktor klasy - ustawienie parametrów
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, high));
        frame.setMaximumSize(new Dimension(width, high));
        frame.setMinimumSize(new Dimension(width, high));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(gra);
        frame.setVisible(true);
        gra.start();


    }

}
