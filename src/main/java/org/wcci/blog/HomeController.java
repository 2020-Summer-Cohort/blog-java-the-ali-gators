package org.wcci.blog;

import org.springframework.ui.Model;

public class HomeController {
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private TagStorage tagStorage;

    public HomeController(CategoryStorage categoryStorage, AuthorStorage authorStorage, TagStorage tagStorage) {
        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.tagStorage = tagStorage;
    }

    public String ShowHome(Model model) {
        model.addAttribute("categories", categoryStorage.getAllCategories());
        model.addAttribute("tags", tagStorage.getAllTags());
        model.addAttribute("authors", authorStorage.getAllAuthors());
        return "home-template";
    }
}
