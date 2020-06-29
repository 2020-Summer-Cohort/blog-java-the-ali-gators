package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wcci.blog.entities.Category;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
public class CategoryController {
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private TagStorage tagStorage;

    public CategoryController(CategoryStorage categoryStorage, AuthorStorage authorStorage, TagStorage mockTagStorage, TagStorage tagStorage) {
        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.tagStorage = tagStorage;
    }

    @GetMapping("/category/{categoryName}")
    public String showSingleCategory(@PathVariable String categoryName, Model model) {
        Category categoryToAdd = categoryStorage.findCategoryByName(categoryName);
        model.addAttribute("category", categoryToAdd);
        model.addAttribute("categories", categoryStorage.getAllCategories());
        model.addAttribute("tags", tagStorage.getAllTags());
        model.addAttribute("authors", authorStorage.getAllAuthors());
        return "category-template";
    }
}
