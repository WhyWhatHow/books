package io.github.whywhathow.books.vo;

import java.util.List;

public class ChangeVo {
    List<String> list;
    Integer state;

    public ChangeVo() {
    }

    public ChangeVo(List<String> list, Integer state) {
        this.list = list;
        this.state = state;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
