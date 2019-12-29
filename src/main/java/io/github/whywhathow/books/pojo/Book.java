package io.github.whywhathow.books.pojo;

import java.util.Date;
/**
 * @Author whywhathow
 * state : 图书状态,0 表示未订购(尚未添加) 1.表示当前可以借阅, 2 表示图书已被借光, 图书不可以借阅的状态
 **/
public class Book {
    private String bid;

    private String bname;

    private String author;

    private String publish;

    private String isbn;

    private Long price;

    private String info;

    private Integer cid;

    private Integer borrow;

    private Integer total;

    private Integer state;

    private Integer current;

    private Date createTime;

    private Date updateTime;

    private String imageUrl;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname == null ? null : bname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getBorrow() {
        return borrow;
    }

    public void setBorrow(Integer borrow) {
        this.borrow = borrow;
    }

    public Integer getTotal() {
        return total;
    }
    /**
     * @return void
     * @Author whywhathow
     * @description: 设置用户借书后图书的状态, 图书借阅数量加一, 图书现存数量减一
     **/
    public void setBorrowStatus() {
        this.current--;
        this.borrow++;
        if (this.current == 0) {
            this.state = 2;
        }
    }

    public void setReturnBookStatus() {
        this.current++;
    }


    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }
}