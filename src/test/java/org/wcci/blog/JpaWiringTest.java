package org.wcci.blog;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Post;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.storage.repositories.PostRepository;
import org.wcci.blog.storage.repositories.TagRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    PostRepository postRepo;
    @Autowired
    AuthorRepository authorRepo;
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    TagRepository tagRepo;
    @Autowired
    TestEntityManager entityManager;

    @Test
    public void postsShouldHaveAnAuthorAndCategoryAndTags() {
        Author testAuthor = new Author("author name");
        Category testCategory = new Category("category name");
        Tag testTag = new Tag("Tag Name");
        Post testPost = new Post("Title", "Body", testAuthor, "Publish Date", testCategory, testTag);
        authorRepo.save(testAuthor);
        categoryRepo.save(testCategory);
        tagRepo.save(testTag);
        postRepo.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Author retrievedAuthor = authorRepo.findById(testAuthor.getId()).get();
        Category retrievedCategory = categoryRepo.findById(testCategory.getId()).get();
        Tag retrievedTag = tagRepo.findById(testTag.getId()).get();
        Post retrievedPost = postRepo.findById(testPost.getId()).get();
        assertThat(retrievedAuthor).isEqualTo(testAuthor);
        assertThat(retrievedAuthor.getPosts()).containsExactlyInAnyOrder(testPost);
        assertThat(retrievedCategory).isEqualTo(testCategory);
        assertThat(retrievedCategory.getPosts()).containsExactlyInAnyOrder(testPost);
        assertThat(retrievedTag).isEqualTo(testTag);
        assertThat(retrievedTag.getPosts()).containsExactlyInAnyOrder(testPost);
        assertThat(retrievedPost).isEqualTo(testPost);

    }
}
