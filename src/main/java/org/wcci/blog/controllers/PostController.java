package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wcci.blog.entities.Post;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
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

    @GetMapping("/post/{title}")
    public String showSinglePost(@PathVariable String title, Model model) {
        Post postToAdd = postStorage.findPostByTitle(title);
        model.addAttribute("post", postToAdd);
        model.addAttribute("categories", categoryStorage.getAllCategories());
        model.addAttribute("tags", tagStorage.getAllTags());
        model.addAttribute("authors", authorStorage.getAllAuthors());
        return "post-template";
    }
}
