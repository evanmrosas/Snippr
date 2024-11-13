package com.evanrosas.snippr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.evanrosas.snippr.models.Snippet;
import com.evanrosas.snippr.repositories.SnippetRepository;

@Service
public class SnippetService {
    private final SnippetRepository snippetRepository;

    public SnippetService(SnippetRepository snippetRepository){
        this.snippetRepository = snippetRepository;
    }
    // Finds all the Snippets
    public List<Snippet>allSnippets(){
        return snippetRepository.findAll();
    }

    // Finds one Snippet by the id
    public Snippet findSnippet(Long id){
        Optional<Snippet> optionalSnippet = snippetRepository.findById(id);
        if(optionalSnippet.isPresent()){
            return optionalSnippet.get();
        } else {
            return null;
        }
    }

    // Creates a new Snippet
    public Snippet createSnippet(Snippet snippet){
        return snippetRepository.save(snippet);
    }
}
