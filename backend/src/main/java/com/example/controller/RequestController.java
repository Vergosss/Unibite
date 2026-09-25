package com.example.controller;

import com.example.service.RequestService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Request;

@RestController
@RequestMapping("/requests")
@CrossOrigin(origins = "http://localhost:3000")
public class RequestController {
    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @GetMapping
    public List<Request> getRequests() {
        return service.myRequests();
    }

    @PostMapping
    public void addRequest(@RequestBody Request request) {
        service.addRequest(request);
    }

}
