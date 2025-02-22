package GestoreEventi;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {

    private LocalTime ora;

    private float prezzo;


    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, float prezzo) {
        super(titolo, data, postiTotali);
        this.ora = ora;
        if (prezzo <= 0){
            throw new ArithmeticException("Il prezzo non può essere minore uguale a 0");
        }
        this.prezzo = prezzo;
    }

    //formatto i l'orario
    public String formatData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println(ora.format(formatter) + " " + "-" + " ");
        return ora.format(formatter);
    }

    //formatto il prezzo
    public String formatPrezzoEvento() {
        return String.format("%.2f", prezzo);
    }

    @Override
    public String toString() {
        return getFormatData() + " " + formatData() + " - " + getTitolo() + " - " + formatPrezzoEvento();
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        if (prezzo <= 0){
            throw new ArithmeticException("Il prezzo non può essere minore uguale a 0");
        }
        this.prezzo = prezzo;
    }
}
