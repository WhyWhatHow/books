package io.github.whywhathow.books.pojo;

import io.github.whywhathow.books.vo.BorrowVo;
import io.github.whywhathow.books.utils.TimeUtils;

import java.util.Date;

public class Relation extends RelationKey {
    private Date borrowTime;

    private Date realReturn;

    private Date needReturn;
    private Book book;
    private User user;

    public Relation(BorrowVo vo) {
        this.borrowTime = new Date();
        this.setUid(vo.getUid());
        this.setBid(vo.getBid());
        this.setNeedReturn(TimeUtils.add30Days(borrowTime));// 默认还书日期,借书后30天内还书
    }


    public Relation(Date borrowTime, Date realReturn, Date needReturn, Book book, User user) {
        this.borrowTime = borrowTime;
        this.realReturn = realReturn;
        this.needReturn = needReturn;
        this.book = book;
        this.user = user;

    }

    public Relation(Date borrowTime, Date needReturn, Book book, User user) {
        this.borrowTime = borrowTime;
        this.book = book;
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Relation() {
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getRealReturn() {
        return realReturn;
    }

    public void setRealReturn(Date realReturn) {
        this.realReturn = realReturn;
    }

    public Date getNeedReturn() {
        return needReturn;
    }

    public void setNeedReturn(Date needReturn) {
        this.needReturn = needReturn;
    }
}