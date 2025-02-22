package GestoreEventi;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            //Inserimento dati da passare al costruttore
            System.out.println("Inserisci il titolo dell'evento");
            String titolo = sc.nextLine().trim();
            System.out.println("Inserisci la data in cui si terrà l'evento (yyyy-mm-dd)");
            LocalDate data = LocalDate.parse(sc.nextLine());
            System.out.println("Inserisci l'orario dell'evento (HH:mm)");
            LocalTime ora = LocalTime.parse(sc.nextLine());
            System.out.println("Inserisci il numero di posti totali");
            int posti = sc.nextInt();
            System.out.println("Inserisci il prezzo dell'evento");
            float prezzo = sc.nextFloat();

            //Verifico se nel costruttore i dati passati sono ok
            Concerto e = null;
            try {
                new Concerto(titolo, data, posti,ora,prezzo);
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
                return;//blocco il codice
            }catch (DateTimeException exceptionDate){
                System.out.println(exceptionDate.getMessage());
                return;//blocco il codice
            }catch (ArithmeticException ex){
                System.out.println(ex.getMessage());
                return;//blocco il codice
            }

        System.out.println(e.toString());
        sc.nextLine();
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

        //Disdetta dei posti
        System.out.println("Vuoi effettuare qualche disdetta? (y/n)");
        String sceltaDisdetta = sc.nextLine().trim().toLowerCase();
        if (e.getPostiPrenotati() == 0){
            System.out.println("Non hai effettuato nessuna prenotazione, non puoi effettuare nessuna disdetta");
            return;
        }
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
    }
}