package io.github.whywhathow.books.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.github.whywhathow.books.mapper.BookMapper;
import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.service.BookService;
import io.github.whywhathow.books.utils.MD5Util;
import io.github.whywhathow.books.utils.Result;
import io.github.whywhathow.books.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service

public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper mapper;

    /**
     * @return io.github.whywhathow.books.utils.Result
     * @Author whywhathow
     * @description: 编辑页面跳转, 如果这本书的bid存在, 说明, 进行的操作是修改操作, 否则则是插入操作
     * @Param [book, request]
     **/
    @Override
    public Result edit(Book book, HttpServletRequest request) {
        System.err.println(book);
        if (StringUtils.isEmpty(book.getBid())) {
            return insertBook(book, request);
        } else {
            return updateBook(book, request);
        }
    }

    @Override
    public Result insertBook(Book book, HttpServletRequest request) {
        Result result = new Result();
        result.setSuccess(false);
        try {
            Integer total = book.getTotal();
            book.setBid(MD5Util.setUUID());
            book.setCreateTime(new Date());
            if (book.getTotal() == 0) { //
                total = 3;
            }
            book.setCurrent(3);
            book.setTotal(3);
            book.setState(1);
            book.setCreateTime(new Date());
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server's problem,  -- add book ");
            return result;
        }
        int res = 0;
        try {
            res = mapper.insertSelective(book);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server's problem,  -- add book ");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setData(res);
        result.setMessage("Success in add Book ,");
        return result;
    }

    @Override
    public Result updateBook(Book book, HttpServletRequest request) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            book.setUpdateTime(new Date());
            res = mapper.updateByPrimaryKeySelective(book);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server's problem,  --error update book");
            return result;
        }
        result.setCode(202);
        if (res == 1) {
            result.setSuccess(true);
            result.setMessage("Susscess update book info");
        } else {
            result.setMessage("error in update book,check your input!");
        }
        result.setData(res);
        return result;
    }

    @Override
    public Result deleteByBid(String bid) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            res = mapper.updateToDeleted(bid);
//            res = mapper.deleteByPrimaryKey(bid);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server's problem,  --");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in delete book");
        result.setData(res);
        return result;
    }

    @Override
    public Result selectByLike(String bname) {
        Result result = new Result();
        result.setSuccess(false);
        List<Book> list = null;
        try {
            list = mapper.selectByLike(bname);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server's problem,  -- select book by   like ");
            return result;
        }
        result.setCode(202);
        if (list != null && list.size() != 0) {
            result.setData(list);
            result.setSuccess(true);
            result.setMessage("Success in get product info ");
        } else {
            result.setMessage("No product contented !!!");
        }
        return result;
    }

    @Override // 以分页的形式返回
    public Result selectByDefault(Integer start, Integer rows) {// 以分页的形式返回给用户
        Result result = new Result();
        result.setSuccess(false);
//        PageHelper
        PageInfo<Book> of = null;
        try {
            PageHelper.startPage(start, rows);
            of = PageInfo.of(mapper.selectAll());
            of.setTotal(mapper.selectCount());
            System.err.println(of);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("服务器异常,请联系管理员,500 - 返回页面");
            return result;
        }
        result.setData(of);
        result.setSuccess(true);
        result.setMessage("加载成功!!!");
        result.setCode(202);
        return result;
    }


    @Override // 通过分类查询图书,分页形式返回给用户
    public Result selectByCategory(CategoryVo vo) {
        Result result = new Result();
        result.setSuccess(false);
        PageInfo<Book> of = new PageInfo<>();
        System.err.println(vo);
        try {
            PageHelper.startPage(vo.getStart(), vo.getRows());
            Integer cid = vo.getCategory().getCid();
            System.err.println(cid);
//            list = mapper.selectByCategory(category.getCategory());
            of = PageInfo.of(mapper.selectByCategory(cid));
            of.setTotal(mapper.selectCountByCategory(cid));
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server's problem,  -- get product  by category ");
            return result;
        }
        result.setCode(202);
        List<Book> list = of.getList();
        if (list == null || list.size() == 0) {
            result.setMessage("抱歉,该分类暂时没有商品, 请联系管理员进行添加");
        } else {
            result.setSuccess(true);
            result.setMessage("通过分类获取用户商品成功");
            result.setData(of);
        }
        return result;

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result changeListByBidAndState(List<String> list, Integer state) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            Date date = new Date();
            for (String pid : list) {
                res += mapper.updateByPidToChangeState(pid, state, date);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//
            return result;
        }
        result.setData(res);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in update product list to change state ");
        return result;

    }

    @Override
    public Result selectTolist(BookVo vo) {
        Result result = new Result();
        result.setSuccess(false);
        PageInfo<Book> info = null;
        try {
            PageHelper.startPage(vo.getStart(), vo.getRows());
            info = PageInfo.of(mapper.selectToList(vo.getBook()));
            info.setTotal(mapper.selectToListCount(vo.getBook()));
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- page  like search");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setData(info);
        result.setMessage("Success in get product list background ");
        return result;
    }

    @Override
    public Result selectToSideShow() {
        return null;
    }

    @Override
    public Result detailByBid(String bid) {
        Result result = new Result();
        result.setSuccess(false);
        Book book = null;
//        int res = 0;
        try {
            book = mapper.selectByPrimaryKey(bid);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server's problem,  -- detail by bid ");
            return result;
        }
        result.setData(book);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in delete book");
//        result.setData(res);
        return result;
    }


    @Override
    public Result selectByGroupId(String uid) {
        return null;
    }

    @Override
    public Result selectGroupStateByUid(String uid) {
        return null;
    }

    @Override
    public Result selectByCidStateAndUid(String cname, String uid, Integer state) {
        return null;
    }

    @Override
    public Result selectByBook(Book book) {
        return null;
    }

    @Override
    public Result selectCategoryCount() {
        Result result = new Result();
        result.setSuccess(false);
        List list = null;
        try {
            list = mapper.selectCategoryCount();
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get category count pie ");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setData(list);
        result.setMessage("success in category count pie");
        return result;
    }

    @Override//查询用户所有的借书信息
    public Result selectUserPieByUser(String uid) {
        Result result = new Result();
        result.setSuccess(false);
        List list = null;
        try {
            list = mapper.selectUserPieByUid(uid);

        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        if (list.size() == 0) {
            result.setMessage("this user is a bad boy, never borrows a book. ");
        } else {
            result.setMessage("success in get user's borrow category count list");
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setData(list);
        return result;
    }

    @Override
    public Result getHotBook() {
        Result result = new Result();
        result.setSuccess(false);

        List<BookHotVo> list = null;
        try {
            list = mapper.selectHotBook();
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get hot book ");
            return result;
        }
        result.setData(list);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("success in get hot book ");
        return result;
    }

    @Override
    public Result getCategoryBook(Integer cid) {
        Result result = new Result();
        result.setSuccess(false);
        List list = null;
        try {
            list = mapper.selectByCategory(cid);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get category book");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("get category book success");
        result.setData(list);
        return result;

    }

    @Override
    public Result getCategoryBookInUser(UserCategoryVo vo) {
        Result result = new Result();
        result.setSuccess(false);
        List list = null;
        try {
            list = mapper.selectCategoryInUser(vo.getUid(), vo.getCid());
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }

        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("succcess in get user borrow books in category");
        result.setData(list);
        return result;


    }

    @Override
    public Result getSideShow() {
        Result result = new Result();
        result.setSuccess(false);
        List list = null;
        try {
            list = mapper.selectToSideShow();
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --get book side show");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setData(list);
        result.setMessage("success in get book side show ");
        return result;

    }
}
