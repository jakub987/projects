package laserowy_labirynt;

public class Stoper {                                       //klasa do odmierzania czasu
    private final long start;                               //zmienna zliczająca milisekundy

    public Stoper() {                                       //główny kosntruktor klasy
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {                           //metoda przeliczająca milisekundy na sekundy
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
