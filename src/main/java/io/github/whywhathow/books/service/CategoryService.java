package io.github.whywhathow.books.service;

import io.github.whywhathow.books.pojo.Category;
import io.github.whywhathow.books.utils.Result;

public interface CategoryService {

    Result getAll();

    Result insertCategory(Category category);

    Result updateCategory(Category category);

    Result deleteCategory(Integer cid);
}
