package com.petziferum.participantservice.participant;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParticipantRepository {

    private List<Participant> participants = new ArrayList<>();

    public Participant addParticipant(Participant participant) {
        participants.add(participant);
        return participant;
    }

    public Participant findById(String id) {
        return participants.stream().filter(participant -> participant.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Participant> findAll() {
        return participants;
    }

    public List<Participant> findByEvent(String eventId) {
        return participants.stream()
                .filter(participant -> participant.getEventId().equals(eventId))
                .toList();
    }
}
