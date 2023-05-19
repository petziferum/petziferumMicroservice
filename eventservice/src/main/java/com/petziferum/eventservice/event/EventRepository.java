package com.petziferum.eventservice.event;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    public Event findById(String id) {
        return events.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Event> findAll() {
        return events;
    }
}
