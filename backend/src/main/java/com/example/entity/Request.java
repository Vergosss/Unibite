package com.example.entity;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "requests")
public class Request {
    @Id
    private String id;
    // the request_id
    private String requesterId; // who made the request. from session, username-id
    private String postingId; // id of the specific posting that the above user showed interest. the one he
                              // clicked
    private String requestDate; // time of the request

    //
    public String getId() {
        return id;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public String getPostingId() {
        return postingId;
    }

    public String getRequestDate() {
        return requestDate;
    }
    // setters

}
