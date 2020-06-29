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
        Author sarah = new Author("Sarah");
        Author emma = new Author("Emma");
        authorRepo.save(alisyn);
        authorRepo.save(sarah);
        authorRepo.save(emma);
        Category inMedia = new Category("In Media");
        Category sightings = new Category("Sightings");
        Category science = new Category("Science");
        categoryRepo.save(inMedia);
        categoryRepo.save(sightings);
        categoryRepo.save(science);
        Tag stereoType = new Tag("Stereotyping");
        Tag whatACutie = new Tag("WhatACutie");
        Tag foxBehaviors = new Tag("FoxBehaviors");
        Tag saveTheFoxes = new Tag("SaveTheFoxes");
        tagRepo.save(stereoType);
        tagRepo.save(whatACutie);
        tagRepo.save(foxBehaviors);
        tagRepo.save(saveTheFoxes);
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
                        "very charming.", alisyn, "2020-06-25", inMedia, stereoType);
        postRepo.save(post1);
        Post post2 = new Post("Breath of the Wild Review", "I have been playing Zelda: Breath of the Wild " +
                "lately and the foxes in this game are too cute to describe. I have spent hours just chasing them " +
                "around and taking pictures of them. I have found I can get a lot closer if I quietly approach them " +
                "but once they start running away their is no way to keep up with them on foot, and they are scared " +
                "of horses so that is out of the question. For those of you who like to fox watch I highly recommend " +
                "this game. 10/10", sarah, "2020-06-29", inMedia, whatACutie, foxBehaviors);
        postRepo.save(post2);
        Post post3 = new Post("Sighting at Deer Creek", "I went out camping with my family recently and got to witness" +
                " the magesty of foxes first hand. I think the cutie was out chasing rabbits but I wasnt able to get " +
                "close enough to confirm. It was very quick so keeping track of it was hard enough. I only got to peek " +
                "at it for a few minutes but it was a great addition to our trip.", emma, "2020-06-28",
                sightings, foxBehaviors, whatACutie, saveTheFoxes);
        postRepo.save(post3);
    }

}
