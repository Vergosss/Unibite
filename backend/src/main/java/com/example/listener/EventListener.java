package com.example.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest.ChangeStreamRequestOptions;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.MessageListener;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;
import org.bson.Document;
import com.mongodb.client.model.changestream.ChangeStreamDocument;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import com.example.entity.Request;
import com.example.service.EventsService;

//@Component // this class is a bean managed by spring boot
public class EventListener {
    /* */
    private final MongoTemplate template;
    private final EventsService service;
    private MessageListenerContainer container;

    public EventListener(MongoTemplate template, EventsService service) {
        this.template = template;
        this.service = service;
    }

    @PostConstruct // after you initialize this class run the following method only once
    private void startRequestsStreamListener() {
        container = new DefaultMessageListenerContainer(template);
        //
        container.start();
        // ChangeStreamDocument<Document> is a mongdodb document java gets from the
        // stream that has body and other metadata
        MessageListener<ChangeStreamDocument<Document>, Request> listener = message -> {
            Request newRequest = message.getBody();
            System.out.println("Message:" + newRequest);
            service.broadCastRequests(newRequest);
        };
        /*
         * ChangeStreamRequestOptions options = new
         * ChangeStreamRequestOptions("unibite", "requests",
         * ChangeStreamOptions.empty());
         */

        //
        ChangeStreamRequest<Request> request = ChangeStreamRequest.<Request>builder(listener).database("unibite")
                .collection("requests").build();
        container.register(request, Request.class);
    }

    //
    @PreDestroy // just before the object of this class is destroyed stop the container
    private void stopRequestsStreamListener() {
        container.stop();

    }

}
