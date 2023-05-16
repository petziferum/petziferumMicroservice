package com.petziferum.eventservice.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Event {

    private String id;
    private String name;
    private String description;

    public Event(String name, String description){
        this.name = name;
        this.description = description;
    }
}
