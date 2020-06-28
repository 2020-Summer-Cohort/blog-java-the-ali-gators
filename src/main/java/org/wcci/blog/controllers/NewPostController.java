package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
public class NewPostController {
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private TagStorage tagStorage;
    private PostStorage postStorage;

    public NewPostController(CategoryStorage categoryStorage, AuthorStorage authorStorage, TagStorage tagStorage, PostStorage postStorage) {
        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.tagStorage = tagStorage;
        this.postStorage = postStorage;
    }
    @GetMapping("/new-post")
    public String NewPostPage(Model model) {
        model.addAttribute("categories", categoryStorage.getAllCategories());
        model.addAttribute("tags", tagStorage.getAllTags());
        model.addAttribute("authors", authorStorage.getAllAuthors());
        return "new-post-template";
    }
    @PostMapping("/post/add")
    public String createNewPost(String category, String author, String tags,
                                String title, String publishDate, String content){
        Category category1 = categoryStorage.findCategoryByName(category);
        Author author1 = authorStorage.findAuthorByName(author);
        Post postToAdd = new Post(title, content, author1, publishDate, category1);
        if (tags.length() > 0) {
            String[] splitTags = tags.split(",");
            for (String tag : splitTags) {
                Tag tagToAdd = tagStorage.getTagByName(tag);
                postToAdd.addTag(tagToAdd);
            }
        }
        postStorage.addPost(postToAdd);
        return "redirect:/post/" + postToAdd.getTitle();
    }
    @PostMapping("/category/add")
    public String createNewCategory(String category){
        if (categoryStorage.findCategoryByName(category)== null){
            Category categoryToAdd = new Category(category);
            categoryStorage.addCategory(categoryToAdd);
        }
        return "redirect:/new-post";
    }
    @PostMapping("/author/add")
    public String createNewAuthor(String author){
        if (authorStorage.findAuthorByName(author)== null){
            Author authorToAdd = new Author(author);
            authorStorage.addAuthor(authorToAdd);
        }
        return "redirect:/new-post";
    }
    @PostMapping("/tag/add")
    public String createNewTag(String tag){
        if (tagStorage.getTagByName(tag) == null) {
            Tag tagToAdd = new Tag(tag);
            tagStorage.addTag(tagToAdd);
        }
        return "redirect:/new-post";
    }

}
