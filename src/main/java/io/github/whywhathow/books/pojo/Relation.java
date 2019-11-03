package io.github.whywhathow.books.pojo;

import java.util.Date;

public class Relation extends RelationKey {
    private Date borrowTime;

    private Date realReturn;

    private Date needReturn;

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