package org.wcci.blog;

import org.springframework.ui.Model;

public class AuthorController {
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private TagStorage tagStorage;

    public AuthorController(CategoryStorage categoryStorage, AuthorStorage authorStorage, TagStorage tagStorage) {

        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.tagStorage = tagStorage;
    }

    public String showSingleAuthor(String name, Model model) {
        Author author = authorStorage.findAuthorByName(name);
        model.addAttribute("author", author);
        model.addAttribute("categories", categoryStorage.getAllCategories());
        model.addAttribute("tags", tagStorage.getAllTags());
        model.addAttribute("authors", authorStorage.getAllAuthors());
        return "author-template";
    }
}
