package io.github.whywhathow.books.vo;

import java.util.List;

public class ProductChangeVo {
    private List<String> list;

    private Integer state;

    public List<String> getList() {
        return list;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

//    public boolean isState() {
//        return state;
//    }
//
//    public void setState(boolean state) {
//        this.state = state;
//    }
}
