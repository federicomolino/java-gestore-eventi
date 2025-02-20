package GestoreEventi;

import java.time.LocalDateTime;

public class Evento {

    private String titolo;

    private LocalDateTime data;

    private static int postiTotali;

    private static int postiPrenotati;

    public Evento(String titolo, LocalDateTime data, int postiTotali) {

        this.titolo = titolo;

        this.postiPrenotati =0;

        //verifico la data inserita
        if (data.isBefore(LocalDateTime.now())) {
            System.out.println("Data non valida");
        }
        this.data = data;

        //verifico se i posti inseriti sono positivi
        if(postiTotali <=0){
            System.out.println("Posti totali non valida");
        }
        this.postiTotali = postiTotali;


    }

    //Aggiunta posti
    public int prenotaPosti(int numeroPosti){
        if(getData().isBefore(LocalDateTime.now()) || postiTotali < numeroPosti){
            System.out.println("Data non valida");
        } else if (postiTotali < numeroPosti || numeroPosti <= 0) {
            System.out.println("numero posti non valido");
        }
        postiPrenotati+= numeroPosti;
        return postiPrenotati;
    }

    //Togli posti
    public int disdiciPosti(int postiDisdetti){
        if(getData().isBefore(LocalDateTime.now())){
            System.out.println("Data non valida");
        } else if (postiDisdetti > postiPrenotati) {
            System.out.println("I posti che vuoi disdire sono di più di quelli prenotati");
        }
        postiPrenotati -= postiDisdetti;
        return postiPrenotati;
    }

    public String ToString(){
        return "Il titolo dello spettacolo è: " + titolo + "che si terrà il: " + data;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public static int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }
}
