package io.github.whywhathow.books.vo;

import java.util.List;

public class ChangeCategoryVo {

    public ChangeCategoryVo() {
    }

    public ChangeCategoryVo(List<Integer> list, Boolean state) {
        this.list = list;
        this.state = state;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    List<Integer> list;

    Boolean state;
}
