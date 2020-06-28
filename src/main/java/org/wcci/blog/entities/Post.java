package org.wcci.blog.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @Column(length = 2000)
    private String content;
    @ManyToOne
    private Author author;
    private String publishDate;
    @ManyToOne
    private Category category;
    @ManyToMany
    private Collection<Tag> tags;

    protected Post() {
    }

    public Post(String title, String content, Author author, String publishDate, Category category, Tag... tags) {

        this.title = title;
        this.content = content;
        this.author = author;
        this.publishDate = publishDate;
        this.category = category;
        this.tags = new ArrayList<>(Arrays.asList(tags));
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Author getAuthor() {
        return author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public Category getCategory() {
        return category;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public String getFirstLine(){
        String[] splitLines = content.split("\\. ");
        return splitLines[0] + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(title, post.title) &&
                Objects.equals(content, post.content) &&
                Objects.equals(author, post.author) &&
                Objects.equals(publishDate, post.publishDate) &&
                Objects.equals(category, post.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author, publishDate, category);
    }

    public void addTag(Tag tagToAdd) {
        tags.add(tagToAdd);
    }
}
