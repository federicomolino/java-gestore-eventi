package GestoreEventi;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.zip.DataFormatException;

public class Evento{

    private String titolo;

    private String data;

    private int postiTotali;

    private int postiPrenotati;

    public Evento(String titolo, String dataInput, int postiTotali) {
        if (titolo.trim().equals("")) {
            throw new IllegalArgumentException("Il titolo deve essere specificato");
        }
        this.titolo = titolo;

        //Verifico la data inserita
        try{
            LocalDate data = LocalDate.parse(dataInput);
            //verifico se i posti inseriti sono positivi
            if(postiTotali <=0){
                throw new ArithmeticException("I posti totali devono avere un numero positivo");
            }
        }catch (DateTimeException e){
            throw new DateTimeException("ERROR!!Data inserita non valida, il formato deve essere (yyyy-MM-dd)");
        }
        this.data = dataInput;

        this.postiTotali = postiTotali;
    }

    //Aggiunta posti
    public int prenotaPosti(int numeroPosti) throws DataFormatException{
        if (postiTotali < numeroPosti) {
            throw new IllegalArgumentException("Posti non disponibili");
        } else if (numeroPosti <= 0) {
            throw new IllegalArgumentException("Posti inseriti non validi");
        }
        //sommo i posti prenotati al totale già esistente
        this.postiPrenotati += numeroPosti;
        //Sottrango ai posti totali il numero di posti inserito
        this.postiTotali -= numeroPosti;
        return this.postiPrenotati;
    }

    //Togli posti
    public int disdiciPosti(int postiDisdetti) throws DataFormatException{
        if (postiDisdetti > postiPrenotati) {
            throw new IllegalArgumentException("I posti da disdire sono superiori a quelli prenotati");
        } else if (postiDisdetti <= 0) {
            throw new IllegalArgumentException("Posti inseriti non validi");
        }
        //Sottraggo ai posti prenotati i posti che deveno essere disdetti
        this.postiPrenotati -= postiDisdetti;
        this.postiTotali += postiDisdetti;
        return this.postiPrenotati;
    }

    @Override
    public String toString(){
        return getFormatData() + " " + "-" + " " + titolo;
    }

    //formatto la data
    public String getFormatData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getData().format(formatter);  // Restituisce la data come stringa formattata
    }

    public String getTitolo() {
        return titolo;
    }

    // Stampo i posti totali rimasti e i prenotati
    public void stampaPosti(){
        System.out.println("Il numero di posti che sono stati prenotati sono " + getPostiPrenotati()
                + "\ni posti rimasti sono " + getPostiTotali());
    }

    public void setTitolo(String titolo) throws IllegalArgumentException {
        if (titolo.trim().equals("")) {
            throw new IllegalArgumentException("Il titolo deve essere specificato");
        }else {
            this.titolo = titolo;
        }
    }

    public LocalDate getData() {
        //trasformo il LocalDate
        LocalDate data = LocalDate.parse(this.data);
        return data;
    }

    public String setData(String dataInput) throws DateTimeException {
        LocalDate data = LocalDate.parse(dataInput);
        try{
            data = LocalDate.parse(dataInput);
        }catch (DateTimeException e){
            System.out.println("ERROR!!! La data inserita non è nel formato corretto (yyyy-mm-dd)");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new DateTimeException("ERROR!!Data non valida,non può essere antecedente alla data odierna");
        }
        return this.data = dataInput;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }
}
