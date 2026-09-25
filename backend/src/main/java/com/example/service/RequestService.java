package com.example.service;

import com.example.repository.RequestRepository;
import org.springframework.stereotype.Service;
import com.example.entity.Request;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository repository;
    private final EventsService eventsService;

    public RequestService(RequestRepository repository, EventsService eventsService) {
        this.repository = repository;
        this.eventsService = eventsService;
    }

    public List<Request> myRequests() {
        return repository.findAll();
    }

    public void addRequest(Request request) {
        /**
         * First we check if points are enough. get points of the user that made the
         * request. if they are > 1 then proceed else raise exception
         */
        repository.save(request);
        eventsService.broadCastRequests(request);

    }
}
