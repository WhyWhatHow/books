package io.github.whywhathow.books.vo;


import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.pojo.User;

public class BookVo {

    private User user;

    private Book Book;

    private Integer quantity = 1;
    private Integer start = 1;
    private Integer rows = 10;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return Book;
    }

    public void setBook(Book Book) {
        this.Book = Book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
