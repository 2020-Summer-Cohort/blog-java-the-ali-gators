package org.wcci.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    public void displayAllCategories(Model model) {
        model.addAttribute("categories",
                categoryStorage.getAllCategories());
    }
    @GetMapping("/{category}")
    public String showSingleCategory(@PathVariable String category, Model model) {
        Category categoryToAdd = categoryStorage.findCategoryByName(category);
        model.addAttribute("category", categoryToAdd);
        model.addAttribute("categories", categoryStorage.getAllCategories());
        model.addAttribute("tags", tagStorage.getAllTags());
        model.addAttribute("authors", authorStorage.getAllAuthors());
        return "category-template";
    }
}
