package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProgrammEventi {
    private final String title;
    private final List<Evento> events;

    public ProgrammEventi(String title) {
        this.title = title;
        this.events = new ArrayList<>();
    }

    public void addEvent(Evento evento) {
        events.add(evento);
    }

    public List<Evento> eventsPerDate(LocalDate data) {
        List<Evento> eventsPerDate = new ArrayList<>();
        for (Evento e : events) {
            if (e.getDate().equals(data)) {
                eventsPerDate.add(e);
            }
        }
        return eventsPerDate;
    }

    public int numberOfEvents() {
        return events.size();
    }

    public void clearEvent() {
        events.clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(title + "\n");
        for (Evento e : events) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String FormattedDate = e.getDate().format(formatter);
            result.append(FormattedDate).append(" - ").append(e.getTitle()).append("\n");
        }
        return result.toString();
    }
}
