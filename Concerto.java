package GestoreEventi;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.DataFormatException;

public class Concerto extends Evento {

    private String ora;

    private float prezzo;


    public Concerto(String titolo, String data, int postiTotali, String oraInput, float prezzo){
        super(titolo, data, postiTotali);

        //verifco se la data è oggi e l'orario è già passato
        try {
            LocalTime ora = LocalTime.parse(oraInput);
            if (LocalDate.now().isEqual(getData()) && ora.isBefore(LocalTime.now())){
                throw new DateTimeException("ERROR!!Ora non valida, data odierna ma orario inferiore ad ora!!");
            }
        }catch (DateTimeException e){
            throw new DateTimeException("ERROR!!! L'orario deve essere passato nel seguente formato (HH:mm)");
        }
        this.ora = oraInput;

        if (prezzo <= 0){
            throw new ArithmeticException("Il prezzo non può essere minore uguale a 0");
        }
        this.prezzo = prezzo;
    }

    //formatto l'orario per la stampa
    public String formatData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return getOra().format(formatter);
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
        LocalTime ora = LocalTime.parse(this.ora);
        return ora;
    }

    public String setOra(String oraInput) {
        //verifco se la data è oggi e l'orario è già passato
        try {
            LocalTime ora = LocalTime.parse(oraInput);
            if (LocalDate.now().isEqual(getData()) && ora.isBefore(LocalTime.now())){
                throw new DateTimeException("ERROR!!Ora non valida, data odierna ma orario inferiore ad ora!!");
            }
        }catch (DateTimeException e){
            System.out.println("ERROR!!! L'orario deve essere passato nel seguente formato (HH:mm)");
        }
        return this.ora = oraInput;
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
