package org.wcci.blog;

import org.springframework.stereotype.Service;

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
