package org.wcci.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.storage.repositories.PostRepository;
import org.wcci.blog.storage.repositories.TagRepository;

@Component
public class Populator implements CommandLineRunner {
    @Autowired
    AuthorRepository authorRepo;
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    PostRepository postRepo;
    @Autowired
    TagRepository tagRepo;

    @Override
    public void run(String... args) {
        Author alisyn = new Author("Alisyn");
        authorRepo.save(alisyn);
        Category inMedia = new Category("In Media");
        categoryRepo.save(inMedia);
        Tag badStereoType = new Tag("BadStereotype");
        Tag whatACutie = new Tag("WhatACutie");
        tagRepo.save(badStereoType);
        tagRepo.save(whatACutie);
        Post post1 = new Post("Zootopia Review",
                "I recently rewatched Zootopia and, while their animation style " +
                "definitely shows how cute foxes are, their representation of them in " +
                "this movie as \"sly foxes\" is a bit problematic. Even with the prevalent " +
                "predator/prey dynamic in Zootopia foxes are treated especially poorly in " +
                "this society and are considered outcasts that cannot be trusted. Their " +
                "solution to this seems to be more fox cops but as we know from what this " +
                "is alluding to, more representation in the police force doesn't really " +
                "change much. If this dynamic is too upsetting for you then I'd recommend " +
                "passing on this movie but otherwise Nick Wilde and Finnick are cute and " +
                "very charming.", alisyn, "2020-06-25", inMedia, badStereoType);
        postRepo.save(post1);


    }

}
