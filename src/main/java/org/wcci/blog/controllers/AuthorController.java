package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wcci.blog.entities.Author;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
public class AuthorController {
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private TagStorage tagStorage;

    public AuthorController(CategoryStorage categoryStorage, AuthorStorage authorStorage, TagStorage tagStorage) {

        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.tagStorage = tagStorage;
    }

    @GetMapping("/authors/{name}")
    public String showSingleAuthor(@PathVariable String name, Model model) {
        Author author = authorStorage.findAuthorByName(name);
        model.addAttribute("author", author);
        model.addAttribute("categories", categoryStorage.getAllCategories());
        model.addAttribute("tags", tagStorage.getAllTags());
        model.addAttribute("authors", authorStorage.getAllAuthors());
        return "author-template";
    }
}
