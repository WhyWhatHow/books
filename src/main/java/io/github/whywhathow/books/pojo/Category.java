package io.github.whywhathow.books.pojo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Category  implements Serializable {

    private Boolean isDeleted ;

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    private Integer cid;

    private String cname;

    private Integer parentid;

    private Date createTime;

    private Date updateTime;


    private ArrayList<Category> ChildernList ;

    public ArrayList<Category> getChildernList() {
        return ChildernList;
    }

    public void setChildernList(ArrayList<Category> childernList) {
        ChildernList = childernList;
    }

    public Category() {
    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
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

}