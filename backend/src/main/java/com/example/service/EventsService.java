package com.example.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.concurrent.CopyOnWriteArrayList;
import com.example.entity.Request;

@Service
public class EventsService {

    // emitters. Here we will store the different persistent connections to this
    // backend
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    //
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);// when frontend creates a event source connection subscribe
        // that connection to the emitters list.Long.MAX_VALUE is the timeout for the
        // connection
        // if i dont remove emitter on timeout or completion or error frontend may
        // initiate a new connection
        // closing the old but the emitter stays in emiiter list in backend
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter)); // after add because i remove from the list
        emitter.onTimeout(() -> {
            emitters.remove(emitter);
            emitter.complete();
        });
        //
        emitter.onError((exception) -> emitters.remove(emitter));
        return emitter;

    }

    // broadcast
    public void broadCastRequests(Request request) {

        System.out.println("Number of emitters: " + emitters.size());

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(request); // if alone it produces unhandled IOException
            } catch (IOException exception) {
                emitters.remove(emitter);// try inside loop prevents dead emitters that have not been removed by spring
                                         // due to late callback execution
                // from stopping the loop resulting in rest emitters on the list not receiving
                // the broadcast
                System.out.println(exception);
            }

        }
    }

}
