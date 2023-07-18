package com.petziferum.eventservice.event;

import com.petziferum.eventservice.client.Participant;
import com.petziferum.eventservice.client.ParticipantClient;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("events")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private ParticipantClient participantClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @PostMapping("/new")
    public ResponseEntity<Event> postEvent(@RequestBody Event event) {
        LOGGER.info("Event: " + event);
        Event e = eventRepository.addEvent(event);
        return ResponseEntity.ok(e);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> allEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/withparticipants")
    public List<Event> findAllEventsWithParticipants() {
        LOGGER.info("####### Find all Events with Participants");
       List<Event> events = eventRepository.findAll();
        events.forEach(event -> {
            LOGGER.info("Get Event: " + event.getId());
//            List<Participant> pList = participantClient.findByEvent(event.getId());
                WebClient.Builder builder = WebClient.builder();
                List<Participant> pList = builder.build()
                        .get()
                        .uri("http://localhost:8082/participants/event/" + event.getId())
                        .retrieve()
                        .bodyToFlux(Participant.class)
                        .collectList()
                        .block();
            LOGGER.info("Participants: " + pList);
            event.setParticipants(pList);
        });
        return events;
    }

    @GetMapping("/allparticipants")
    public List<Participant> findAllParticipants() {
        LOGGER.info("####### Find all Participants");
        List<Participant> pList = participantClient.findAll();
        LOGGER.info("Participants: " + pList);
        return pList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        LOGGER.info("Event ID: " + id);
        Event event = eventRepository.findById(id);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(event);
    }
}
