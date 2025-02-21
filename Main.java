package GestoreEventi;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Inserimento dati da passare al costruttore
        System.out.println("Inserisci il titolo dell'evento");
        String titolo = sc.nextLine();
        System.out.println("Inserisci la data in cui si terrà l'evento (yyyy/mm/dd)");
        LocalDate data = LocalDate.parse(sc.nextLine());
        System.out.println("Inserisci il numero di posti totali");
        int posti = sc.nextInt();

        Evento e = new Evento(titolo, data, posti);

        sc.nextLine();
        System.out.println("Vuoi effettuare delle prenotazioni? (y/n)");
        String scelta = sc.nextLine().trim().toLowerCase();

        while(scelta.equals("y")) {
            System.out.println("Inserisci il numero di posti che vuoi prenotare");
            int numeroPostiInput = sc.nextInt();

            //Se viene superato il limite dei posti faccio prosegure per inserirne nuovi
            try{
                e.prenotaPosti(numeroPostiInput);
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
            e.stampaPosti();

            sc.nextLine();
            System.out.println("Vuoi effettuare nuove prenotazioni? (y/n)");
            scelta = sc.nextLine().trim().toLowerCase();
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
            }
            e.stampaPosti();

            sc.nextLine();
            System.out.println("Vuoi effettuare nuove disdette? (y/n)");
            sceltaDisdetta = sc.nextLine().trim().toLowerCase();
        }


        //System.out.println(e.toString());
    }
}