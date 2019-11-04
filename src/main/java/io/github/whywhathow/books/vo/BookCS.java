package io.github.whywhathow.books.vo;

// 通过用户id , 分类名称,资产状态查找的封装类
public class BookCS {
    private String cname;
    private String uid;
    private Integer State;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }
}
