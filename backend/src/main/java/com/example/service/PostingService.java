package com.example.service;

import com.example.entity.Loc;
import com.example.entity.Posting;
import com.example.repository.PostingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

import org.springframework.stereotype.Service;
import com.example.dto.PostingDTO;

@Service
public class PostingService {
    private final PostingRepository repository;

    public PostingService(PostingRepository repository) {
        this.repository = repository;
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
        LocalDateTime creationDate = LocalDateTime.now();
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
    public void updatePosting(String id, Object data) {

    }

}
