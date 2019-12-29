package io.github.whywhathow.books.controller;

import io.github.whywhathow.books.pojo.Category;
import io.github.whywhathow.books.service.CategoryService;
import io.github.whywhathow.books.utils.Result;
import io.github.whywhathow.books.vo.CategoryVo;
import io.github.whywhathow.books.vo.ChangeCategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api("图书分类的相关接口-- Pass")
@RequestMapping("/category")
@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    CategoryService service;

    @ApiOperation(value = "填写表单添加一个分类,pass" ,notes = "{\n" +
            "\t\"cname\":\"手环\",\n" +
            "\t\"parentid\":0\n" +
            "}" )
    @PostMapping("/add")
    public Result insertCategory(@RequestBody Category category, HttpServletRequest request) {
        return service.insertCategory(category, request);
    }

    /**
     * @return
     * @Author whywhathow
     * 前端:
     * 后端:  返回一个result.data 封装的是数组
     * @Param [category]
     * todo may be wrong
     **/
    @ApiOperation(value = "删除 一个分类", notes = "级联删除的处理方案： 将parentId=cid 或者 cid =cid")
    @DeleteMapping("/del/{cid}")
    public Result deleteCategory(@PathVariable("cid") Integer cid) {
        return  service.deleteCategory(cid);
    }

    /**
     * 后端: 通过category 修改category 对应的属性
     * 前端: 通过判断 result.success 对象进行判断*
     * @param category
     * @return result ,
     */
    @ApiOperation( value = "更新一个分类")
    @PostMapping("/update")
    public Result updateCategory(@RequestBody Category category, HttpServletRequest request) {

        return service.updateCategory(category, request);

    }
// TODo  product controller 重新检查
//    @Autowired
//    BookService productService;

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 用户点击某个category, 获取 图书列表
     * 前端: {category: category,start:start, rows:rows}
     * 后端:  PageInfo{list[],total,...}
     * @Param [category]
     **/
//    @ApiOperation(value = "通过图书分类获取图书信息", notes = "与http://ip/product/cat方法重复", response = Book.class)
//    @PostMapping("/get")
//    public Result getCategory(@RequestBody CategoryVo categoryVo) {
//        return productService.selectByCategory(categoryVo);
//    }

    @ApiOperation(value = "查询全部分类 ", notes = "返回的是一个list", response = Category.class)
    @GetMapping("/all")
    public Result getAllCategory() {
        return service.getAll();
    }

    //TODO 2019年11月4日23:01:49, 感觉没有用处
    @ApiOperation("category后台的模糊查询")
    @PostMapping("/list")
    public Result selectToList(@RequestBody CategoryVo vo) {
        return service.selectTolist(vo);
    }


    @ApiOperation("批量修改图书分类的状态信息， 正常状态，已删除状态")
    @PostMapping("/change")
    public Result changeBookSateInListByBid(@RequestBody ChangeCategoryVo vo) {
        return service.changeListByBidAndState(vo.getList(), vo.getState());
    }


    @ApiOperation(value = "根据图书分类id 查询图书分类详情", response = Integer.class)
    @GetMapping("/detail/{cid}")// 图书详情
    public Result detailByBid(@PathVariable("cid") Integer cid) {
        return service.detailByCid(cid);

    }

    @ApiOperation(value = "添加图书", notes = "book 的各种信息, 以及category 的 cid ,不需要填充book 里面的category," +
            "只需要添加cid即可---book.getCid() ")
    @PostMapping("/edit")// 编辑页面跳转
    public Result edit(@RequestBody Category category, HttpServletRequest request) {
        return service.edit(category, request);
    }



}
