package com.petziferum.eventservice.event;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    private List<Event> events = new ArrayList<>();

    public void initEvents() {
        //add Events to List
        this.events.add(Event.builder().id("1").name("Event1").description("Event1").build());
        this.events.add(Event.builder().id("2").name("Event2").description("Event2").build());
        this.events.add(Event.builder().id("3").name("Event3").description("Event3").build());
        this.events.add(Event.builder().id("4").name("Event4").description("Event4").build());
    }

    public Event addEvent(Event event) {
        events.add(event);
        return event;
    }

    public Optional<Event> findById(String id) {
        return events.stream().filter(event -> event.getId().equals(id)).findFirst();
    }

    public List<Event> findAll() {
        return events;
    }

    public Event save(Event eventToUpdate) {
        events.add(eventToUpdate);
        return eventToUpdate;
    }
}
