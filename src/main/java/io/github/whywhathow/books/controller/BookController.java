package io.github.whywhathow.books.controller;

import com.github.pagehelper.PageInfo;
import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.service.BookService;
import io.github.whywhathow.books.utils.Result;
import io.github.whywhathow.books.vo.BookCS;
import io.github.whywhathow.books.vo.BookVo;
import io.github.whywhathow.books.vo.CategoryVo;
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

    @ApiOperation(value = "添加一本图书", notes = "需要给出图书分类的编号(外键 可以取值1 ,没有办法),可以保证这个数据一定不会出错")
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
    @GetMapping("/all")// 默认查询方式, 分页返回,这个方法没有用, 仅用作测试
    public Result selectByDefault(Integer start, Integer rows) {
        if (StringUtils.isEmpty(start)) {
            start = 1;
        }
        if (StringUtils.isEmpty(rows)) {
            rows = 10;
        }
        return service.selectByDefault(start, rows);
    }

    @ApiOperation(value = "图书按分类查询,分页显示", notes = "???2019年11月4日22:47:39", response = PageInfo.class)
    @PostMapping("/cat")
//     notes =  "留下了不加requestBody的泪水"
    public Result selectByCategory(@RequestBody CategoryVo vo) {
        return service.selectByCategory(vo);
    }


    //TODO 2019年11月4日23:01:49, 感觉没有用处
    @ApiOperation("后台的模糊查询")
    @PostMapping("/list")
    public Result selectToList(@RequestBody BookVo book) {
        return service.selectTolist(book);
    }

// TODO 查询用户借阅信息
//    @ApiOperation("获取当前用户下借阅信息")
//    @GetMapping("/cid")
//    public Result selectGroupByUid(String uid) {
//        return service.selectByGroupId(uid);
//    }

//    @ApiOperation("根据用户id ,按固定资产的状态进行分类 ")
//    @GetMapping("/state")
//    public Result selectGroupByState(String uid) {
//        return service.selectGroupStateByUid(uid);
//    }


//    @PostMapping ("/see")
//    @ApiOperation("根据用户id,分类名称,资产状态查找的资产详细信息")
//    public Result selectByCnameAndStateAndUid( BookCS Book){
//        return service.selectByCidStateAndUid(Book.getCname(),Book.getUid(),Book.getState());
//    }

}
