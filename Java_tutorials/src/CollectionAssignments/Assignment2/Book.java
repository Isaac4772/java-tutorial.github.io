package CollectionAssignments.Assignment2;

import java.time.LocalDate;

public class Book {
    private int code;
    private String title;
    private LocalDate publishDate;
    private String category;
    private Author author;

    public Book() {
    }

    public Book(int code, String title, LocalDate publishDate, String category, Author author) {
        this.code = code;
        this.title = title;
        this.publishDate = publishDate;
        this.category = category;
        this.author = author;
    }

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

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
