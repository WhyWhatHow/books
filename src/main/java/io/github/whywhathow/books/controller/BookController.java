package io.github.whywhathow.books.controller;

import com.github.pagehelper.PageInfo;
import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.pojo.User;
import io.github.whywhathow.books.service.BookService;
import io.github.whywhathow.books.utils.Result;
import io.github.whywhathow.books.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//import io.github.whywhathow.books.vo.BookChangeVo;

@Api("图书接口文档-- Pass")
@RequestMapping("/book")
@CrossOrigin

@RestController
public class BookController {
    @Autowired
    private BookService service;

    @ApiOperation(value = "添加一本图书", notes = "需要给出图书分类的编号(外键 可以取值1 ,没有办法),可以保证这个数据一定不会出错\n" +
            "原因是，添加 图书的分类编号是来自数据库，可以保证数据不会出错... " +
            "我在测试是默认使用的1")
    @PostMapping("/add")
    public Result insertBook(@RequestBody Book book, HttpServletRequest request) {
        return service.insertBook(book, request);
    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO:
     * 前端: 表单修改 book{bid} 需要提供 bid (必须)
     * 后端:  直接返回message ,result.data 不涉及,
     * @Param [book]
     **/
    @ApiOperation(value = "根据图书编号修改图书信息", notes = "Constraint: 分类编号:(存在外键约束),不可以少, 数据类型的修改")
    @PostMapping("/update")
    public Result updateBook(@RequestBody Book book, HttpServletRequest request) {
        return service.updateBook(book, request);
    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 添加图书信息
     * 前端:  基本选项 不包括 bid(后端生成) , 图书分类
     * 后端:   返回提示信息,result.success == true or false
     * @Param [book]
     **/
    @ApiOperation(value = "添加图书", notes = "book 的各种信息, 以及category 的 cid ,不需要填充book 里面的category," +
            "只需要添加cid即可---book.getCid() ")
    @PostMapping("/edit")// 编辑页面跳转
    public Result edit(@RequestBody Book book, HttpServletRequest request) {
        return service.edit(book, request);
    }

//
//    @ApiOperation("批量修改图书")
//    @PostMapping("/change")
//    public Result changeListByBidAndState(@RequestBody BookChangeVo vo) {
//        return service.changeListByBidAndState(vo.getList(), vo.getState());
//    }


    @ApiOperation(value = "根据图书id 删除一个图书", response = Integer.class)
    @GetMapping("/del/{bid}")
    public Result deleteByBid(@PathVariable("bid") String bid) {
        return service.deleteByBid(bid);
    }

    @ApiOperation(value = "根据图书id 查询图书详情", response = Integer.class)
    @GetMapping("/detail/{bid}")// 图书详情
    public Result detailByBid(@PathVariable("bid") String bid) {
        return service.detailByBid(bid);
    }

    @ApiOperation(value = "对图书的模糊查询", notes = "返回值是list", response = Book.class)
    @GetMapping("/like/{name}")// 模糊查询, 书名,作者, 出版社
    public Result selectByLike(@PathVariable("name") String name) {
        return service.selectByLike(name);
    }

    @ApiOperation(value = "图书分页展示,感觉有点没有用", response = Book.class)
    @PostMapping("/all")// 默认查询方式, 分页返回,这个方法没有用, 仅用作测试
    public Result selectByDefault(Integer start, Integer rows) {
        if (StringUtils.isEmpty(start)) {
            start = 1;
        }
        if (StringUtils.isEmpty(rows)) {
            rows = 10;
        }
        return service.selectByDefault(start, rows);
    }

    @ApiOperation(value = "图书按分类查询,分页显示", notes = "{\n" +
            "  \"category\": \n" +
            "{ \"cid\":1\n" +
            "}\n" +
            "}", response = PageInfo.class)
    @PostMapping("/cat")
//     notes =  "留下了不加requestBody的泪水" 。哦真的吗！真不愧是faker
    public Result selectByCategory(@RequestBody CategoryVo vo) {
        return service.selectByCategory(vo);
    }


    //TODO 2019年11月4日23:01:49, 感觉没有用处
    @ApiOperation("后台的模糊查询")
    @PostMapping("/list")
    public Result selectToList(@RequestBody BookVo book) {
        return service.selectTolist(book);
    }


    @ApiOperation("获取用户所有借阅图书的分类信息")
    @PostMapping("/userpie")
    public Result selectUserPieByUser(User user) {
        return service.selectUserPieByUser(user.getUid());
    }


    @ApiOperation("获取图书分类分布状态图")
    @GetMapping("/pie")
    public Result getBookCategoryCount() {
        return service.selectCategoryCount();
    }

    @ApiOperation("获取当前借阅的热门图书前十本")
    @GetMapping("/hot")
    public Result getHotBook() {
        return service.getHotBook();
    }

    @ApiOperation("获取当前借阅的热门图书前十本")
    @GetMapping("/sideShow")
    public Result getSideShow() {
        return service.getSideShow();
    }


    @ApiOperation("获取该分类下的图书")
    @GetMapping("/see")
    public Result getCategoryBook(Integer cid) {
        return service.getCategoryBook(cid);
    }

    @ApiOperation("获取用户借阅图书在该分类下的图书列表")
    @PostMapping("/userSee")
    public Result getCategoryBookInUser(UserCategoryVo vo) {
        return service.getCategoryBookInUser(vo);
    }


    @ApiOperation("批量修改图书状态信息， 正常状态，下架状态")
    @PostMapping("/change")
    public Result changeBookSateInListByBid(@RequestBody ChangeVo vo) {
        return service.changeListByBidAndState(vo.getList(), vo.getState());
    }

}
