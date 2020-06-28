package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.storage.repositories.PostRepository;
import org.wcci.blog.entities.Post;

@Service
public class PostStorage {
    PostRepository postRepo;

    public PostStorage(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    public Post findPostByTitle(String title) {
        return postRepo.findByTitle(title);
    }

    public void addPost(Post post) {
        postRepo.save(post);
    }

    public Iterable<Post> getAllPosts() {
        return postRepo.findAll();
    }
}
