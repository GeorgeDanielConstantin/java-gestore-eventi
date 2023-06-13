package org.lessons.java;

import org.lessons.java.exceptions.EventException;
import org.lessons.java.exceptions.InvalidCapacity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Titolo: ");
        String title = scan.nextLine();

        System.out.print("Data (formato dd/MM/yyyy): ");
        boolean inputDate = false;
        LocalDate date = null;
        while (!inputDate) {
            try {
                date = LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                inputDate = true;
            } catch (Exception e) {
                System.out.println("Formato data non valido. Inserisci una data nel formato dd/MM/yyyy.");
                System.out.print("Data (formato dd/MM/yyyy): ");
            }
        }
        System.out.print("Numero di posti totali: ");
        int capacity = Integer.parseInt(scan.nextLine());

        System.out.print("Ora (formato HH:mm): ");
        boolean inputHour = false;
        LocalTime hour = null;
        while (!inputHour) {
            try {
                hour = LocalTime.parse(scan.nextLine(), DateTimeFormatter.ofPattern(("HH:mm")));
                inputHour = true;
            } catch (Exception e) {
                System.out.println("Formato ora non valido. Inserisci un orario nel formato HH:mm.");
                System.out.print("Ora (formato HH:mm): ");
            }
        }

        System.out.print("Prezzo: ");
        BigDecimal price = new BigDecimal(scan.nextLine());

        try {
            Concerto concerto = new Concerto(title, date, capacity, hour, price);
            System.out.println("Concerto creato con successo!");
            System.out.println("Titolo: " + concerto.getTitle());
            System.out.println("Data: " + concerto.getDate());
            System.out.println("Posti totali: " + concerto.getCapacity());

            boolean bookingSwitch = false;
            boolean cancelSwitch = false;

            do {
                System.out.print("Vuoi effettuare delle prenotazioni? (Sì/No): ");
                String bookingChoice = scan.nextLine();

                if (bookingChoice.equalsIgnoreCase("Sì")) {
                    System.out.print("Inserisci il numero di prenotazioni da effettuare: ");
                    int nBooked = Integer.parseInt(scan.nextLine());
                    try {
                        for (int i = 0; i < nBooked; i++) {
                            concerto.book(nBooked);
                        }
                        System.out.println("Prenotazione effettuata con successo.");
                    } catch (InvalidCapacity e) {
                        System.out.println("Impossibile effettuare la prenotazione: " + e.getMessage());
                    }
                } else if (bookingChoice.equalsIgnoreCase("No")) {
                    System.out.println("Nessuna prenotazione effettuata.");
                    bookingSwitch = true;
                } else System.out.println("Scelta non valida. Riprova");
            } while (!bookingSwitch);


            System.out.println("Numero di posti prenotati: " + concerto.getBooked());
            System.out.println("Posti disponibili: " + (concerto.getCapacity() - concerto.getBooked()));


            do {
                System.out.print("Vuoi disdire dei posti? (Sì/No): ");
                String cancelChoice = scan.nextLine();

                if (cancelChoice.equalsIgnoreCase("Sì")) {
                    System.out.print("Inserisci il numero di posti da disdire: ");
                    int nCancelled = Integer.parseInt(scan.nextLine());
                    try {
                        for (int i = 0; i < nCancelled; i++) {
                            concerto.cancel(nCancelled);
                        }
                        System.out.println("Disdetta effettuata con successo.");
                    } catch (InvalidCapacity e) {
                        System.out.println("Impossibile effettuare la disdetta: " + e.getMessage());
                    }
                } else if (cancelChoice.equalsIgnoreCase("No")) {
                    System.out.println("Nessuna disdetta effettuata.");
                    cancelSwitch = true;
                } else System.out.println("Scelta non valida. Riprova");
            } while (!cancelSwitch);

            System.out.println("Numero di posti prenotati: " + concerto.getBooked());
            System.out.println("Posti disponibili: " + (concerto.getCapacity() - concerto.getBooked()));

            System.out.println(concerto);

        } catch (EventException e) {
            System.out.println("Errore durante la creazione del concerto: " + e.getMessage());
        }
        scan.close();
    }
}