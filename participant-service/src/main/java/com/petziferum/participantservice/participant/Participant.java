package com.petziferum.participantservice.participant;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Builder
@Data
public class Participant {
    private String id;
    private String firstName;
    private String lastName;
    private String eventId;
    private ArrayList<String> events;

}
