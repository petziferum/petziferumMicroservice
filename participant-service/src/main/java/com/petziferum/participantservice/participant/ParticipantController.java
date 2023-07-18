package com.petziferum.participantservice.participant;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@OpenAPIDefinition(
        info = @Info(
                title = "Participant API",
                version = "1.0",
                description = "Documentation Participant API v1.0"
        ), externalDocs = @ExternalDocumentation(
                description = "OpenAPI Annotations",
                url = "https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations"
        ),
        tags = {
                @Tag(name = "participants", description = "Participant API", externalDocs = @ExternalDocumentation(
                        description = "OpenAPI Annotations",
                        url = "https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations"))
        }
)
@Slf4j
@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantController.class);

    @Autowired
    ParticipantRepository participantRepository;

    @PostMapping("/new")
    public Participant postParticipant(@RequestBody Participant participant) {
        LOGGER.info("Participant: " + participant);
        return participantRepository.addParticipant(participant);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Participant>> findAllParticipants() {
        return ResponseEntity.ok(participantRepository.findAll());
    }

    @GetMapping("/{id}")
public ResponseEntity<Participant> getParticipantById(@PathVariable String id) {
        LOGGER.info("### GET Participant ID: " + id);
        Participant participant = participantRepository.findById(id);
        if (participant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(participant);
    }

    @GetMapping("/event/{eventId}")
    public List<Participant> getParticipantByEvent(@PathVariable String eventId) {
       LOGGER.info("Event ID: " + eventId);
        return participantRepository.findByEvent(eventId);
    }
}
