package GestoreEventi;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;


public class Main {
    public static void main(String[] args) {
        //Verranno aggiunti tutti gli eventi che l'utente crea
        List<Evento> eventiTotali = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        boolean creaNuovoEvento  = true;
        while (creaNuovoEvento) {
                //Inserimento dati da passare al costruttore
                System.out.println("Inserisci il titolo dell'evento");
                String titolo = sc.nextLine().trim();

                System.out.println("Inserisci la data in cui si terrà l'evento (yyyy-mm-dd)");
                String dataInput = sc.nextLine().trim();

                //faccio inserire l'orario e ne verifico il formato
                System.out.println("Inserisci l'orario dell'evento (HH:mm)");
                String oraInput = sc.nextLine().trim();
//                LocalTime ora;
//                try {
//                    String timeInput = sc.nextLine().trim();
//                    ora = LocalTime.parse(timeInput);
//                }catch (DateTimeException e){
//                    System.out.println("ERROR!!! L'orario deve essere passato nel seguente formato (HH:mm)");
//                    return;
//                }

                System.out.println("Inserisci il numero di posti totali");
                int posti = sc.nextInt();

                System.out.println("Inserisci il prezzo dell'evento");
                float prezzo = sc.nextFloat();

                //Verifico se nel costruttore i dati passati sono ok
                Concerto e;
                try {
                    e = new Concerto(titolo, dataInput, posti,oraInput,prezzo);
                }catch (IllegalArgumentException exTitolo){
                    System.out.println(exTitolo.getMessage());
                    return;//blocco il codice
                }catch (DateTimeException exceptionDateOra){
                    System.out.println(exceptionDateOra.getMessage());
                    return;//blocco il codice
                }catch (ArithmeticException exPostiPrezzo){
                    System.out.println(exPostiPrezzo.getMessage());
                    return;//blocco il codice
                }
            System.out.println(e.toString());
            sc.nextLine();

            //Inserire gli eventi creati nella lista e ritorno
            // il conto totale degli eventi nella lista
            System.out.println("Vuoi l'evento in lista?");
            String aggiungiEvento = sc.nextLine().trim().toLowerCase();
            if (aggiungiEvento.equals("y")) {
                ProgrammaEventi.aggiuntaEvento(e);
                ProgrammaEventi.numeroEventiTotoli();
            }else if (aggiungiEvento.equals("n")) {
                System.out.println("Nessun evento è stato aggiunto.");
            }

            System.out.println("Vuoi effettuare delle prenotazioni? (y/n)");
            String scelta = sc.nextLine().trim().toLowerCase();

            while(scelta.equals("y")) {
                System.out.println("Inserisci il numero di posti che vuoi prenotare");
                int numeroPostiInput = sc.nextInt();

                //Se viene superato il limite dei posti faccio prosegure per inserirne nuovi
                try{
                    e.prenotaPosti(numeroPostiInput);
                } catch (DataFormatException ex2){
                    System.out.println(ex2.getMessage());
                }
                e.stampaPosti();

                sc.nextLine();
                System.out.println("Vuoi effettuare nuove prenotazioni? (y/n)");
                scelta = sc.nextLine().trim().toLowerCase();
            }

            if (e.getPostiPrenotati() == 0){
                System.out.println("Non hai effettuato nessuna prenotazione");
                return;
            }

            //Disdetta dei posti
            System.out.println("Vuoi effettuare qualche disdetta? (y/n)");
            String sceltaDisdetta = sc.nextLine().trim().toLowerCase();
            while(sceltaDisdetta.equals("y")) {
                System.out.println("Quanti posti vuoi disdire?");
                int numeroPostiDisdettaInput = sc.nextInt();

                //Se viene superato il limite dei posti da disdire non blocco
                try{
                    e.disdiciPosti(numeroPostiDisdettaInput);
                }catch (IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                }catch (DataFormatException ex2){
                    System.out.println(ex2.getMessage());
                }
                e.stampaPosti();

                sc.nextLine();
                // Se i posti prenotati sono 0 faccio uscire
                if (e.getPostiPrenotati() == 0){
                    return;
                }
                System.out.println("Vuoi effettuare nuove disdette? (y/n)");
                sceltaDisdetta = sc.nextLine().trim().toLowerCase();
            }

            //Per ricerca l'evento in una deteriminata data, in caso li metto in ordine
            System.out.println("Devi ricercare un evento per una determinata data (y / n)");
            String ricercaData = sc.nextLine().trim().toLowerCase();
            if(ricercaData.equals("y")){
                System.out.println("Inserisci la data che vuoi ricercare (yyyy-MM-dd)");
                String ricercaDataInput = sc.nextLine().trim();
                ProgrammaEventi.listaEventiPresenti(ricercaDataInput);
                System.out.println("Gli eventi in ordine sono invece\n");
                ProgrammaEventi.ordinaEventi();
            }

            //verifico se si necessita di svuotare la lista!
            if (aggiungiEvento.equals("y")){
                    System.out.println("Vuoi svuotare la lista? (y/n)");
                    String cancellaEventiLista = sc.nextLine().trim().toLowerCase();
                if (cancellaEventiLista.equals("y")){
                    ProgrammaEventi.svuotaListaEventi();
                }else{
                    ProgrammaEventi.numeroEventiTotoli();
                }
            }
                System.out.println("Vuoi aggiungere un nuovo evento? (y/n)");
                creaNuovoEvento  = sc.nextLine().trim().toLowerCase().equals("y");
                if(creaNuovoEvento  == true){
                    /*In caso di creazione di un nuovo Evento creo ogni volta un nuovo
                    oggetto*/
                    Concerto nuovoConcerto = new Concerto(titolo, dataInput, posti, oraInput, prezzo);
                    eventiTotali.add(nuovoConcerto);
                }
        }
    }
}