package org.wcci.blog;

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
}
