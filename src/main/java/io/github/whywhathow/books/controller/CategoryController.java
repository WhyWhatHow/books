package io.github.whywhathow.books.controller;

import io.github.whywhathow.books.pojo.Category;
import io.github.whywhathow.books.service.CategoryService;
import io.github.whywhathow.books.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result insertCategory(@RequestBody Category category) {
        return service.insertCategory(category);
    }

    /**
     * @return
     * @Author whywhathow
     * TODO: 根据category id删除category (修改标记位)
     * 前端:
     * 后端:  返回一个result.data 封装的是数组
     * @Param [category]
     **/
    @ApiOperation(value = "删除 一个分类", notes = "未考虑级联删除的情况,所以删除存在问题")
    @DeleteMapping("/del/{cid}")
    public Result deleteCategory(@PathVariable("cid") Integer cid) {
        return  service.deleteCategory(cid);
    }

    /**
     * TODO: 修改category
     * 后端: 通过category 修改category 对应的属性
     * 前端: 通过判断 result.success 对象进行判断
     *
     * @param category
     * @return result ,
     */
    @ApiOperation( value = "更新一个分类")
    @PostMapping("/update")
    public Result updateCategory(@RequestBody Category category) {

        return service.updateCategory(category);

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
}
