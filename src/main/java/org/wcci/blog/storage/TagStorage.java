package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.storage.repositories.TagRepository;
import org.wcci.blog.entities.Tag;

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

    public void addTag(Tag tag){
        tagRepo.save(tag);
    }
}
