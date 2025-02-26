package GestoreEventi;

import java.time.LocalDate;
import java.util.*;

public class ProgrammaEventi {

    private String titolo;

    private static int count;

    private static List<Evento> eventi = new ArrayList<>();

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
    }

    public static void aggiuntaEvento(Evento evento) {
        eventi.add(evento);
        System.out.println(eventi);
    }

    public static int numeroEventiTotoli(){
        //Vado a contare il numero totale di eventi presenti nella lista
        for (int i =0; i < eventi.size(); i++){
            count ++;
        }
        System.out.println("Gli eventi totali presenti sono: " + count + " " + eventi);
        return count;
    }

    public static int svuotaListaEventi() throws ArithmeticException {
        if (eventi.size() == 0){
            throw new ArithmeticException("Non è presente nessun evento nella lista");
        }
        eventi.clear();
        System.out.println("La lista degli elementi è stata svuotata");
        return eventi.size();
    }

    public static void listaEventiPresenti(LocalDate data){
        System.out.println("Gli eventi per la data inserita sono: ");
        boolean trovato = false;
        for (int i =0; i < eventi.size(); i++){
            //Verifico se ci sono eventi nella data inserita dall'utente
           if (data.isEqual(eventi.get(i).getData())) {
               System.out.println("Il " + eventi.get(i));
               trovato = true;
           }
        }
        if (!trovato){
            System.out.println("Nessun evento trovato per il " + "'" +data + "'");
        }
    }

    public static void ordinaEventi() throws ArithmeticException {
        /*Ordino la lista in base ai valori restituiti nel getData
           per ogni oggetto Evento.
        */
        if (eventi.size() == 0){
            throw new ArithmeticException("Non sono presenti eventi!!!");
        }
        Collections.sort(eventi, Comparator.comparing(Evento::getData));
        //Itero la lista eventi che contiene gli oggetti della classe Evento e stampo in ordine di data
        for (Evento evento : eventi){
            System.out.println(evento.getFormatData() + " " + evento.getTitolo());
        }
    }
}