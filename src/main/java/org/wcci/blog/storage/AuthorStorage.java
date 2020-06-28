package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.entities.Author;

@Service
public class AuthorStorage {
    AuthorRepository authorRepo;

    public AuthorStorage(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    public Author findAuthorByName(String name) {
        return authorRepo.findByName(name);
    }

    public void addAuthor(Author author) {
        authorRepo.save(author);
    }

    public Iterable<Author> getAllAuthors() {
        return authorRepo.findAll();
    }
}
