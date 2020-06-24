package org.wcci.blog;

import org.springframework.stereotype.Service;

@Service
public class TagStorage {
    TagRepository tagRepo;

    public TagStorage(TagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }

    public Tag getTagByName(String name) {
        return tagRepo.findByName(name);
    }

    public Iterable<Tag> getAllTags() {
        return tagRepo.findAll();
    }
}
