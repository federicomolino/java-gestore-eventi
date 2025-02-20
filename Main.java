package GestoreEventi;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Evento e = new Evento("Titolo", LocalDateTime.of(2025,2,21,21,45,0), 3);

        e.prenotaPosti(2);
        System.out.println(e.getPostiPrenotati());
        e.disdiciPosti(4);
        System.out.println(e.getPostiPrenotati());
        System.out.println(e.ToString());
    }
}