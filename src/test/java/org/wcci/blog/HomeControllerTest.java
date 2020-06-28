package org.wcci.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.wcci.blog.controllers.HomeController;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.TagStorage;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HomeControllerTest {
    private CategoryStorage mockCategoryStorage;
    private AuthorStorage mockAuthorStorage;
    private TagStorage mockTagStorage;
    private HomeController underTest;
    private Model model;

    @BeforeEach
    void setUp() {
        mockCategoryStorage = mock(CategoryStorage.class);
        mockAuthorStorage = mock(AuthorStorage.class);
        mockTagStorage = mock(TagStorage.class);
        underTest = new HomeController(mockCategoryStorage, mockAuthorStorage, mockTagStorage, postStorage);
        model = Mockito.mock(Model.class);
    }

    @Test
    public void shouldReturnHomeTemplate() {
        String templateName = underTest.ShowHome(model);
        assertThat(templateName).isEqualTo("home-template");
    }

    @Test
    public void shouldAddAllAuthorsCategoriesAndTagsToModel() {
        underTest.ShowHome(model);
        verify(mockCategoryStorage).getAllCategories();
        verify(mockAuthorStorage).getAllAuthors();
        verify(mockTagStorage).getAllTags();
        verify(model).addAttribute("categories", Collections.EMPTY_LIST);
        verify(model).addAttribute("tags", Collections.EMPTY_LIST);
        verify(model).addAttribute("authors", Collections.EMPTY_LIST);

    }
}
