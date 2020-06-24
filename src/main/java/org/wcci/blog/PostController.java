package org.wcci.blog;

import org.springframework.ui.Model;

public class PostController {
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private TagStorage tagStorage;
    private PostStorage postStorage;

    public PostController(CategoryStorage categoryStorage, AuthorStorage authorStorage, TagStorage tagStorage, PostStorage postStorage) {

        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.tagStorage = tagStorage;
        this.postStorage = postStorage;
    }

    public String showSinglePost(String title, Model model) {
        Post postToAdd = postStorage.findPostByTitle(title);
        model.addAttribute("post", postToAdd);
        model.addAttribute("categories", categoryStorage.getAllCategories());
        model.addAttribute("tags", tagStorage.getAllTags());
        model.addAttribute("authors", authorStorage.getAllAuthors());
        return "post-template";
    }
}
