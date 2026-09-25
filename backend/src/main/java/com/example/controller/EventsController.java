package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.example.service.EventsService;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:3000")

public class EventsController {

    private final EventsService service;

    // constructor
    public EventsController(EventsService service) {
        this.service = service;
    }

    // the following function must respond(produces) with Media type text event
    // stream.
    // Means that this is not a normal response then close. it will remain open
    // SseEmitter represents a client-server SSE connection
    @GetMapping(value = "/requests", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter myRequests() {
        return service.subscribe();
    }

}