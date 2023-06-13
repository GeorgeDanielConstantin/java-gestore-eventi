package org.lessons.java;

import org.lessons.java.exceptions.EventException;
import org.lessons.java.exceptions.InvalidCapacity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Titolo: ");
        String title = scan.nextLine();

        System.out.print("Data (formato dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Numero di posti totali: ");
        int capacity = Integer.parseInt(scan.nextLine());

        try {
            Evento evento = new Evento(title, date, capacity);
            System.out.println("Evento creato con successo!");
            System.out.println("Titolo: " + evento.getTitle());
            System.out.println("Data: " + evento.getDate());
            System.out.println("Posti totali: " + evento.getCapacity());

            boolean bookingSwitch = false;
            boolean cancelSwitch = false;

            do {
                System.out.print("Vuoi effettuare delle prenotazioni? (Sì/No): ");
                String bookingChoice = scan.nextLine();

                if (bookingChoice.equalsIgnoreCase("Sì")) {
                    try {
                        System.out.print("Inserisci il numero di prenotazioni da effettuare: ");
                        int nBooked = Integer.parseInt(scan.nextLine());

                        for (int i = 0; i < nBooked; i++) {
                            evento.book(nBooked);
                            System.out.println("Prenotazione effettuata con successo.");
                        }
                    } catch (InvalidCapacity e) {
                        System.out.println("Impossibile effettuare la prenotazione: " + e.getMessage());
                    }
                } else if (bookingChoice.equalsIgnoreCase("No")) {
                    System.out.println("Nessuna prenotazione effettuata.");
                    bookingSwitch = true;
                } else System.out.println("Scelta non valida. Riprova");
            } while (!bookingSwitch);


            System.out.println("Numero di posti prenotati: " + evento.getBooked());
            System.out.println("Posti disponibili: " + (evento.getCapacity() - evento.getBooked()));


            do {
                System.out.print("Vuoi disdire dei posti? (Sì/No): ");
                String cancelChoice = scan.nextLine();

                if (cancelChoice.equalsIgnoreCase("Sì")) {
                    try {
                        System.out.print("Inserisci il numero di posti da disdire: ");
                        int nCancelled = scan.nextInt();

                        for (int i = 0; i < nCancelled; i++) {
                            evento.cancel(nCancelled);
                            System.out.println("Disdetta effettuata con successo.");
                        }
                    } catch (InvalidCapacity e) {
                        System.out.println("Impossibile effettuare la disdetta: " + e.getMessage());
                    }
                } else if (cancelChoice.equalsIgnoreCase("No")) {
                    System.out.println("Nessuna prenotazione effettuata.");
                    cancelSwitch = true;
                } else System.out.println("Scelta non valida. Riprova");
            } while (!cancelSwitch);

            System.out.println("Numero di posti prenotati: " + evento.getBooked());
            System.out.println("Posti disponibili: " + (evento.getCapacity() - evento.getBooked()));

        } catch (EventException e) {
            System.out.println("Errore durante la creazione dell'evento: " + e.getMessage());
        }
    }
}
