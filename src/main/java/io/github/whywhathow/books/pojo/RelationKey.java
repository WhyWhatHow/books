package io.github.whywhathow.books.pojo;

public class RelationKey {
    private String uid;

    private String bid;

    public RelationKey() {
    }

    public RelationKey(String uid, String bid) {
        this.uid = uid;
        this.bid = bid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}