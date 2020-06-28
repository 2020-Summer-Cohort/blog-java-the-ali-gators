package org.wcci.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.wcci.blog.controllers.PostController;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PostControllerTest {
    private CategoryStorage mockCategoryStorage;
    private AuthorStorage mockAuthorStorage;
    private TagStorage mockTagStorage;
    private PostController underTest;
    private Model model;
    private PostStorage mockPostStorage;

    @BeforeEach
    void setUp() {
        mockCategoryStorage = mock(CategoryStorage.class);
        mockAuthorStorage = mock(AuthorStorage.class);
        mockTagStorage = mock(TagStorage.class);
        mockPostStorage = mock(PostStorage.class);
        underTest = new PostController(mockCategoryStorage, mockAuthorStorage, mockTagStorage, mockPostStorage);
        model = Mockito.mock(Model.class);
    }

    @Test
    public void shouldReturnPostTemplate() {
        String templateName = underTest.showSinglePost("name", model);
        assertThat(templateName).isEqualTo("post-template");
    }

    @Test
    public void shouldAddPostToModel() {
        Author testAuthor = new Author("Testy");
        Category testCategory = new Category("Test Category");
        Post testPost = new Post("Test Title", "Test Content", testAuthor, "02/02/2020", testCategory);
        when(mockPostStorage.findPostByTitle("Test Title")).thenReturn(testPost);
        underTest.showSinglePost("Test Title", model);
        verify(mockPostStorage).findPostByTitle("Test Title");
        verify(model).addAttribute("post", testPost);
    }

    @Test
    public void addsCategoriesTagsAndAuthorsAttributes() {
        ;
        underTest.showSinglePost("Test Post", model);
        verify(mockCategoryStorage).getAllCategories();
        verify(mockAuthorStorage).getAllAuthors();
        verify(mockTagStorage).getAllTags();
        verify(model).addAttribute("categories", Collections.EMPTY_LIST);
        verify(model).addAttribute("tags", Collections.EMPTY_LIST);
        verify(model).addAttribute("authors", Collections.EMPTY_LIST);
        ;
    }
}
