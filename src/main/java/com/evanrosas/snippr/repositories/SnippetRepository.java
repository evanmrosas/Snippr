package com.evanrosas.snippr.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evanrosas.snippr.models.Snippet;

@Repository
public interface SnippetRepository extends CrudRepository<Snippet, Long>{
    List<Snippet>findAll();
    
}
