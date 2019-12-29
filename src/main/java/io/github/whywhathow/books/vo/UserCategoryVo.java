package io.github.whywhathow.books.vo;

public class UserCategoryVo {

    String uid;
    Integer cid;
    String cname;

    public UserCategoryVo() {

    }

    public UserCategoryVo(String uid, Integer cid, String cname) {
        this.uid = uid;
        this.cid = cid;
        this.cname = cname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
        this.cname = cname;
    }
}
