package org.lessons.java;

import org.lessons.java.exceptions.DateBefore;
import org.lessons.java.exceptions.EventException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {

    private BigDecimal price;
    private LocalTime hour;

    public Concerto(String title, LocalDate date, int capacity, LocalTime hour, BigDecimal price) throws EventException {
        super(title, date, capacity);
        this.hour = hour;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) throws EventException {
        if (hour.isBefore(LocalTime.now())) {
            throw new DateBefore("L'ora' del concerto non può essere passata.");
        }
        this.hour = hour;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getDate().format(formatter);
    }

    public String getFormattedHour() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return hour.format(formatter);
    }

    public String getFormattedPrice() {
        return price.setScale(2, RoundingMode.HALF_EVEN) + "€";
    }

    @Override
    public String toString() {
        return getFormattedDate() + " - " + getFormattedHour() + " - " + getTitle() + " - " + getFormattedPrice();
    }
}
