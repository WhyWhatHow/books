package io.github.whywhathow.books.vo;

import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.pojo.User;

/**
 * @Author whywhathow
 * 用户借书接受前端数据的封装类
 **/
public class BorrowVo {

    String uid, bid;

    public BorrowVo() {
    }

    public BorrowVo(String uid, String bid) {
        this.uid = uid;
        this.bid = bid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
