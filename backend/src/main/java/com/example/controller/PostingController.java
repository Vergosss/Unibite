package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.entity.Posting;
import com.example.service.PostingService;
import com.example.dto.PostingDTO;

@RestController
@RequestMapping("/postings")
@CrossOrigin(origins = "http://localhost:3000")
// @CrossOrigin("https:")

public class PostingController {
    private final PostingService service;

    public PostingController(PostingService service) {
        this.service = service;
    }

    @GetMapping // equivalent to GET /postings
    public List<Posting> getPostings() {
        return service.getPostings();
    }

    @PostMapping
    public Posting createPosting(@RequestBody PostingDTO posting) {
        return service.createPosting(posting);
    }

    @DeleteMapping("/{id}")
    public void deletePosting(@PathVariable("id") String id) {
        service.deletePosting(id);
    }
    @PutMapping("/{id}")
    public void updatePosting(@PathVariable("id") String id,@RequestBody Object data){
        service.updatePosting(id,data);
    }

}
