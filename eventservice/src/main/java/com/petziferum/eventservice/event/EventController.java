package com.petziferum.eventservice.event;

import com.petziferum.eventservice.client.Participant;
import com.petziferum.eventservice.client.ParticipantClient;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("events")
public class EventController {

    private final EventRepository eventRepository;
    private final ParticipantClient participantClient;

    @Autowired
    public EventController(EventRepository eventRepository, ParticipantClient participantClient) {
        this.eventRepository = eventRepository;
        this.participantClient = participantClient;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @PostMapping("/new")
    @Operation(summary = "Create a new Event", description = "Create a new Event")
    public ResponseEntity<Event> postEvent(@Valid @RequestBody Event event) {
        LOGGER.info("Post Event: {}", event);
        Event createdEvent = eventRepository.addEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all Events", description = "Get all Events")
    public ResponseEntity<List<Event>> allEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/withparticipants")
    @Operation(summary = "Get all Events with Participants", description = "Get all Events with Participants")
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
    @Operation(summary = "Get all Participants", description = "Get all Participants")
    public List<Participant> findAllParticipants() {
        LOGGER.info("####### Find all Participants");
        List<Participant> pList = participantClient.findAll();
        LOGGER.info("Participants: " + pList);
        return pList;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Event by ID", description = "Get Event by ID")
    public ResponseEntity<Optional<Event>> getEventById(@PathVariable String id) {
        LOGGER.info("Event ID: " + id);
        Optional<Event> event = eventRepository.findById(id);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an Event", description = "Update an existing Event by its ID")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @Valid @RequestBody Event updatedEvent) {
        LOGGER.info("Updating Event with ID: {}", id);

        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isEmpty()) {
            LOGGER.error("Event not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Update the existing event with new data
        Event eventToUpdate = existingEvent.get();
        eventToUpdate.setName(updatedEvent.getName());
        eventToUpdate.setDescription(updatedEvent.getDescription());
        // ... set other fields as needed

        Event updated = eventRepository.save(eventToUpdate);
        LOGGER.info("Event updated: {}", updated);
        return ResponseEntity.ok(updated);
    }
}
