package io.github.whywhathow.books.vo;

public class BookHotVo {
    String bid;
    String bname;
    Integer num;

    public BookHotVo() {
    }

    public BookHotVo(String bid, String bname, Integer num) {
        this.bid = bid;
        this.bname = bname;
        this.num = num;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
