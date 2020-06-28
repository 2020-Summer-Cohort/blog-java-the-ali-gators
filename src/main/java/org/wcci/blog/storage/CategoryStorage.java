package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.entities.Category;

@Service
public class CategoryStorage {
    private CategoryRepository categoryRepo;

    public CategoryStorage(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category findCategoryByName(String name) {
        return categoryRepo.findByName(name);
    }

    public void addCategory(Category category) {
        categoryRepo.save(category);
    }
}
