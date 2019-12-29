package io.github.whywhathow.books.service;

import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.utils.Result;
import io.github.whywhathow.books.vo.BookVo;
import io.github.whywhathow.books.vo.CategoryVo;
import io.github.whywhathow.books.vo.UserCategoryVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BookService {
    Result selectByDefault(Integer start, Integer rows);

    Result edit(Book Book, HttpServletRequest request);

    Result updateBook(Book Book, HttpServletRequest request);

//    Result insertProduct(Book Book, Category category);

    Result selectByCategory(CategoryVo category);

    Result deleteByBid(String pid);

    Result insertBook(Book Book, HttpServletRequest request);

    Result selectByLike(String pname);

    Result changeListByBidAndState(List<String> list, Integer state);

    Result selectTolist(BookVo vo);

    Result selectToSideShow();

    Result detailByBid(String pid);

    Result selectByGroupId(String uid);

    Result selectGroupStateByUid(String uid);

    Result selectByCidStateAndUid(String cname, String uid, Integer state);

    Result selectByBook(Book book);

    Result selectCategoryCount();

    Result selectUserPieByUser(String uid);

    Result getHotBook();

    Result getCategoryBook(Integer cid);

    Result getCategoryBookInUser(UserCategoryVo vo);

    Result getSideShow();
}
