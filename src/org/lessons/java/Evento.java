package org.lessons.java;

import org.lessons.java.exceptions.DateBefore;
import org.lessons.java.exceptions.InvalidCapacity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    private String title;
    private LocalDate date;
    private final int capacity;
    private int booked;

    public Evento(String title, LocalDate date, int capacity) throws DateBefore, InvalidCapacity {
        if (date.isBefore(LocalDate.now())) throw new DateBefore("La date dell'evento non può essere passata.");
        if (capacity <= 0) throw new InvalidCapacity("Il numero di posti totali deve essere positivo.");
        this.title = title;
        this.date = date;
        this.capacity = capacity;
        this.booked = 0;

    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBooked() {
        return booked;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDate date) throws DateBefore {
        if (date.isBefore(LocalDate.now())) throw new DateBefore("La nuova date dell'evento non può essere passata.");
        this.date = date;
    }

    public void book() throws DateBefore,InvalidCapacity {
        if (date.isBefore(LocalDate.now())) throw new DateBefore("Impossibile prenotare un evento passato.");

        if (booked >= capacity) throw new InvalidCapacity("Non ci sono posti disponibili.");

        booked++;
    }

    public void cancel() throws DateBefore, InvalidCapacity {
        if (date.isBefore(LocalDate.now())) throw new DateBefore("Impossibile disdire un evento passato.");

        if (booked <= 0) throw new InvalidCapacity("Non ci sono prenotazioni da disdire.");

        booked--;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter) + " - " + title;
    }
}
