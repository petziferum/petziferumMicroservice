package com.petziferum.eventservice.event;

import com.petziferum.eventservice.client.Participant;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Getter
@Setter
public class Event {

    private String id;
    private String name;
    private String description;
    private List<Participant> participants = new ArrayList<>();
}
