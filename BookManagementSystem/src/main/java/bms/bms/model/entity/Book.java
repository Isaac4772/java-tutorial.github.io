package bms.bms.model.entity;

import java.time.LocalDate;

public class Book {
    private int code;
    private String title;
    private LocalDate publishedDate;
    private Category category;
    private Author author;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getAuthorName(){
        return this.author.getName();
    }

    public String getCategoryName(){
        return this.category.getName();
    }
}
