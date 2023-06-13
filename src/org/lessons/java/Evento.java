package org.lessons.java;

import org.lessons.java.exceptions.DateBefore;
import org.lessons.java.exceptions.EventException;
import org.lessons.java.exceptions.InvalidCapacity;

import java.time.LocalDate;

public abstract class Evento {
    private final int capacity;
    private String title;
    private LocalDate date;
    private int booked;

    public Evento(String title, LocalDate date, int capacity) throws EventException {
        if (date.isBefore(LocalDate.now())) {
            throw new DateBefore("La date dell'evento non può essere passata.");
        } else if (capacity <= 0) {
            throw new InvalidCapacity("Il numero di posti totali deve essere positivo.");
        } else if (title.isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere vuoto");
        } else {
            this.title = title;
            this.date = date;
            this.capacity = capacity;
            this.booked = 0;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws EventException {
        if (date.isBefore(LocalDate.now())) {
            throw new DateBefore("La nuova date dell'evento non può essere passata.");
        }
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBooked() {
        return booked;
    }

    public void book(int nBooked) throws EventException {
        if (date.isBefore(LocalDate.now())) {
            throw new DateBefore("Impossibile prenotare un evento passato.");
        }

        if (booked + nBooked > capacity) {
            throw new InvalidCapacity("Non ci sono abbastanza posti disponibili.");
        }


        booked += nBooked;
    }

    public void cancel(int nCancelled) throws EventException {
        if (date.isBefore(LocalDate.now())) {
            throw new DateBefore("Impossibile disdire un evento passato.");
        }

        if (booked - nCancelled < 0) {
            throw new InvalidCapacity("Non ci sono abbastanza prenotazioni da disdire.");
        }

        booked -= nCancelled;
    }

    @Override
    public abstract String toString();
}
