package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
public class HomeController {
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private TagStorage tagStorage;
    private PostStorage postStorage;

    public HomeController(CategoryStorage categoryStorage, AuthorStorage authorStorage, TagStorage tagStorage, PostStorage postStorage) {
        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.tagStorage = tagStorage;
        this.postStorage = postStorage;
    }

    @GetMapping({"", "/"})
    public String ShowHome(Model model) {
        model.addAttribute("categories", categoryStorage.getAllCategories());
        model.addAttribute("tags", tagStorage.getAllTags());
        model.addAttribute("authors", authorStorage.getAllAuthors());
        model.addAttribute("posts", postStorage.getAllPosts());
        return "home-template";
    }
}
