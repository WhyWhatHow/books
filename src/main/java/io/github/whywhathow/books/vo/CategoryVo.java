package io.github.whywhathow.books.vo;


import io.github.whywhathow.books.pojo.Category;

/**
 * @param ： category ： 书籍分类，只需要提供分类号
 *          example：
 *          {
 *          "category":
 *          { "cid":1
 *          },
 *          "start":1,
 *          "rows":10
 *          }
 * @Author whywhathow
 * @param: start : 开始的页数, 默认 1
 * @param： rows ：改页面含有的内容的数量 ，默认 10
 **/
public class CategoryVo {
    private Category category;
    private Integer start = 1;
    private Integer rows = 10;

    @Override
    public String toString() {
        return "CategoryVo{" +
                "category=" + category +
                ", start=" + start +
                ", rows=" + rows +
                '}';
    }

    public CategoryVo() {
        this.start = 1;
        this.rows = 10;
    }

    public CategoryVo(Category category, Integer start, Integer rows) {
        this.category = category;
        this.start = start;
        this.rows = rows;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
