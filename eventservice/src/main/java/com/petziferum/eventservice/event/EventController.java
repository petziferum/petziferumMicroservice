package com.petziferum.eventservice.event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/events")
public class EventController {

    @PostMapping("/new")
    public ResponseEntity<String> postEvent() {
        return ResponseEntity.ok("Passt 👍👍👍");
    }

    @GetMapping("/all")
    public ResponseEntity<String> allEvents() {
        return ResponseEntity.ok("Hier hast deine Events --> 🤡");
    }
}
