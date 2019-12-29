package io.github.whywhathow.books.vo;

public class CategoryCountVo {
    Integer cid;// 分类编号
    String cname;//分类名称
    Integer num;//该分类下的图书数量

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public CategoryCountVo() {
    }

    public CategoryCountVo(Integer cid, String cname, Integer num) {
        this.cid = cid;
        this.cname = cname;
        this.num = num;
    }
}
