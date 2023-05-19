package com.petziferum.eventservice.client;

import java.util.List;

public record Participant(String id, String firstName, String lastName, List<String> events) {
}
