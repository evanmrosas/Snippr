package com.evanrosas.snippr.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.evanrosas.snippr.models.Snippet;
import com.evanrosas.snippr.repositories.SnippetRepository;

@Service
public class SnippetService {
    private final SnippetRepository snippetRepository;
    private final EncryptionService encryptionService;

    public SnippetService(SnippetRepository snippetRepository, EncryptionService encryptionService) {
        this.snippetRepository = snippetRepository;
        this.encryptionService = encryptionService;
    }

    // Finds all the Snippets
    public List<Snippet> allSnippets() {
        return snippetRepository.findAll().stream().map(snippet -> {
            try {
                snippet.setCode(encryptionService.decrypt(snippet.getCode()));
            } catch (Exception e) {
                throw new RuntimeException("Error decrypting snippet code", e);
            }
            return snippet;
        }).collect(Collectors.toList());
    }

    // Finds one Snippet by the id
    public Optional<Snippet> findSnippet(Long id) {
        return snippetRepository.findById(id).map(snippet -> {
            try {
                snippet.setCode(encryptionService.decrypt(snippet.getCode()));
            } catch (Exception e) {
                throw new RuntimeException("Error decrypting snippet code", e);
            }
            return snippet;
        });
    }

    // Creates a new Snippet
    public Snippet createSnippet(Snippet snippet) {
        try {
            snippet.setCode(encryptionService.encrypt(snippet.getCode()));
            return snippetRepository.save(snippet);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting snippet code", e);
        }
    }
}
