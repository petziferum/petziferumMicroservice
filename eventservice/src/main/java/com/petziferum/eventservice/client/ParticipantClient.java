package com.petziferum.eventservice.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface ParticipantClient {

    //GetExchange ist dazu da um den Endpunkt eines anderen Service zu zeigen (Expose)
    // GetMapping ist um einen Endpunkt aufzurufen (Call)!
    @GetExchange("/participants/event/{eventId}")
    public List<Participant> findByEvent(@PathVariable("eventId") String eventId);

    @GetExchange("/participants/all")
    public List<Participant> findAll();
}
