package io.github.whywhathow.books.vo;

import io.github.whywhathow.books.pojo.Book;

import java.util.Date;

public class NowVo {
    String bid;
    String bname;
    String author;
    String imageUrl;
    Date borrowTime;
    Date realReturn;
    Date needReturn;

    public NowVo(String bid, String bname, String author, String imageUrl, Date borrowTime, Date realReturn, Date needReturn) {
        this.bid = bid;
        this.bname = bname;
        this.author = author;
        this.imageUrl = imageUrl;
        this.borrowTime = borrowTime;
        this.realReturn = realReturn;
        this.needReturn = needReturn;
    }

    public NowVo() {
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
