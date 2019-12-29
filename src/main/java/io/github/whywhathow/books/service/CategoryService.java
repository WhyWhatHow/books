package io.github.whywhathow.books.service;

import io.github.whywhathow.books.pojo.Category;
import io.github.whywhathow.books.utils.Result;
import io.github.whywhathow.books.vo.CategoryVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CategoryService {

    Result getAll();

    Result insertCategory(Category category, HttpServletRequest request);

    Result updateCategory(Category category, HttpServletRequest request);

    Result deleteCategory(Integer cid);

    Result selectTolist(CategoryVo book);

    Result changeListByBidAndState(List<Integer> list, boolean state);

    Result detailByCid(Integer cid);

    Result edit(Category category, HttpServletRequest request);
}
