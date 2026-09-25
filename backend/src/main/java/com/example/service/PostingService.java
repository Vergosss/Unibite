package com.example.service;

import com.example.entity.Loc;
import com.example.entity.Posting;
import com.example.repository.PostingRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.example.dto.PostingDTO;

@Service
public class PostingService {
    private final PostingRepository repository;
    private final MongoTemplate mongoTemplate;

    // mongoTemplates are used when repository methods are not enough, basically for
    // more complex operations aside from CRUD ones.
    public PostingService(PostingRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Posting> getPostings() {
        return repository.findAll();
    }

    public Posting createPosting(PostingDTO posting) {
        Posting newPosting = new Posting();
        newPosting.setTitle(posting.getTitle());
        newPosting.setNotes(posting.getNotes());
        newPosting.setPortions(posting.getPortions());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Clock clk = Clock.systemUTC();// time zone the system uses
        LocalDateTime creationDate = LocalDateTime.now(clk);
        newPosting.setCreationDate(creationDate.format(format));
        List<Float> coords = new ArrayList<>();
        coords.add(52.07f);
        coords.add(66.13f);
        Loc location = new Loc("Point", coords);
        newPosting.setLocation(location);
        return repository.save(newPosting);
    }

    //
    public void deletePosting(String id) {
        repository.deleteById(id);

    }

    //
    public void updatePosting(String id, PostingDTO data) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("title", data.getTitle()).set("notes", data.getNotes()).set("portions",
                data.getPortions());
        //
        mongoTemplate.updateFirst(query, update, Posting.class);
    }

}
