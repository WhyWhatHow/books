package io.github.whywhathow.books.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu  implements Serializable {
    private ArrayList<Menu> childrenList;

    public ArrayList<Menu> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(ArrayList<Menu> childrenList) {
        this.childrenList = childrenList;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    private Integer id;

    private String name;

    private String uri;

    private Integer parentid;

    private Integer type;

    private Boolean isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}