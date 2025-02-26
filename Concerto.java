package GestoreEventi;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.DataFormatException;

public class Concerto extends Evento {

    private LocalTime ora;

    private float prezzo;


    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, float prezzo){
        super(titolo, data, postiTotali);

        //verifco se la data è oggi e l'orario è già passato
        if (LocalDate.now().isEqual(getData()) && ora.isBefore(LocalTime.now())){
            throw new DateTimeException("ERROR!!Ora non valida, data odierna ma orario inferiore ad ora!!");
        }
        this.ora = ora;

        if (prezzo <= 0){
            throw new ArithmeticException("Il prezzo non può essere minore uguale a 0");
        }
        this.prezzo = prezzo;
    }

    //formatto l'orario per la stampa
    public String formatData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return ora.format(formatter);
    }

    //formatto il prezzo
    public String formatPrezzoEvento() {
        return String.format("%.2f €", prezzo);
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

    public float setPrezzo(float prezzo) throws ArithmeticException {
        if (prezzo <= 0){
            throw new ArithmeticException("Il prezzo non può essere minore uguale a 0");
        }
        return this.prezzo = prezzo;
    }
}
