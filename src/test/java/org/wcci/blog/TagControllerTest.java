package org.wcci.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TagControllerTest {
    private CategoryStorage mockCategoryStorage;
    private AuthorStorage mockAuthorStorage;
    private TagStorage mockTagStorage;
    private TagsController underTest;
    private Model model;

    @BeforeEach
    void setUp() {
        mockCategoryStorage = mock(CategoryStorage.class);
        mockAuthorStorage = mock(AuthorStorage.class);
        mockTagStorage = mock(TagStorage.class);
        underTest = new TagsController(mockCategoryStorage, mockAuthorStorage, mockTagStorage);
        model = Mockito.mock(Model.class);
    }

    @Test
    public void shouldReturnCorrectTag() {
        Tag testTag = new Tag("Test Tag");
        when(mockTagStorage.getTagByName("Test Tag")).thenReturn(testTag);

        underTest.showSingleTag("Test Tag", model);

        verify(mockTagStorage).getTagByName("Test Tag");
        verify(model).addAttribute("tag", testTag);
    }

    @Test
    public void shouldReturnTagsTemplate() {
        String templateName = underTest.showSingleTag("Test", model);
        assertThat(templateName).isEqualTo("tag-template");
    }

    @Test
    public void addsCategoriesTagsAndAuthorsAttributes() {
        ;
        underTest.showSingleTag("Test Tag", model);
        verify(mockCategoryStorage).getAllCategories();
        verify(mockAuthorStorage).getAllAuthors();
        verify(mockTagStorage).getAllTags();
        verify(model).addAttribute("categories", Collections.EMPTY_LIST);
        verify(model).addAttribute("tags", Collections.EMPTY_LIST);
        verify(model).addAttribute("authors", Collections.EMPTY_LIST);
        ;
    }
}
