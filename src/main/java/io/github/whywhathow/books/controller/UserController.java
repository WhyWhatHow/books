package io.github.whywhathow.books.controller;

import io.github.whywhathow.books.pojo.Menu;
import io.github.whywhathow.books.pojo.User;
import io.github.whywhathow.books.service.UserService;
import io.github.whywhathow.books.utils.Result;
import io.github.whywhathow.books.vo.BorrowVo;
import io.github.whywhathow.books.vo.HistoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "用户的接口,pass")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    // 登录, 注册, 激活, 修改
    @Autowired
    UserService service;

    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation(value = "用户借书接口", notes = "需要提供用户uid,和图书bid")
    @PostMapping("/borrow")// pass
    public Result borrowBook(@RequestBody BorrowVo vo) {
        return service.borrowBook(vo);
    }

    @ApiOperation(value = "用户还书接口", notes = "需要提供用户uid和图书bid")
    @PostMapping("/return")
    public Result returnBook(@RequestBody BorrowVo vo) {
        return service.returnBook(vo);
    }

    @ApiOperation(value = "用户提交欠款接口", notes = "需要提供用户uid")
    @PostMapping("/pay")
    public Result returnBook(@RequestBody String uid) {
        return service.payOwe(uid);
    }


//    @GetMapping("/hello")
//    public Result hello() {
//        Result result = new Result();
//        result.setMessage("hello");
////        result.setSuccess(false);
//        result.setCode(200);
//        result.setSuccess(true);
//        result.setData("hello, boys");
//        return result;
//
//    }

    @ApiOperation(value = "修改用户信息", notes = "提交数据为表单,封装的user对象", response = User.class)

    @PostMapping("/update")//pass
    public Result updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }


    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO: 用户登录
     * 前端:
     * 后端:
     * @Param [user]
     **/
    @ApiOperation(value = "用户登录", notes = "用户登录成功后,用户会被存在session中,result.data中我也有给你们返回 测试数据;{\"username\":\"nash\",\n" +
            "\"password\":\"aa12321.\"}")
    @PostMapping("/login")//pass
    public Result loginUser(@RequestBody User user, HttpServletRequest request) {
        return service.loginUser(user, request);
    }

//    @ApiOperation("发送激活码给用户")
//    @PostMapping("/send")
//    public Result sendToActiveUser(@RequestBody User user) {
//        return service.sendACtiveCodeToUser(user);
//    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO:
     * 前端: 前端需要实现表单校验,去空字符
     * 后端: 默认数据有效,非空,"",null去空格
     * @Param [user]
     **/
    @ApiOperation(value = "用户注册(pass)")
    @PostMapping("/reg")//pass
    public Result registerUser(@RequestBody User user) {
        return service.registerUser(user);
    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO:
     * 前端:
     * 后端:
     * @Param [user]
     **/
//    @RequestMapping("/active")
//    public Result activeUser(@RequestBody User user) {
//        return null;
//    }

    /**
     * @return com.sdut.onlinestore.utils.Result
     * @Author whywhathow
     * TODO:
     * 前端: 提供当前的登录用户,信息 不准给我少uid (登录成功后,我把uid 存进httpsession里去了,LoginUser方法中我也把登录的用户存进result.data里
     * 后端:  后台系统用户菜单的项的获取, {[{}]} 形式, 递归的性实现
     * @Param [user]
     **/
    @ApiOperation(value = "获取用户的权限- pass", notes = "{\"username\":\"nash\",\n" +
            "\"password\":\"aa12321.\",\n" +
            "\"rid\":1\n" +
            "}", response = Menu.class)
//    @ApiOperation("")
    @PostMapping("/role")//pass
    public Result getRole(@RequestBody User user) {
        return service.getMenu(user);
    }


    @ApiOperation(value = "检查用户名是否重复", notes = "可以用在注册校验上")
    @PostMapping("/check")
    public Result checkUserName(@RequestBody String username) {
        return service.checkUserName(username);
    }

    @ApiOperation(value = "获取用户的历史借阅信息", notes = "返回数据是列表， 列表项是HistoryVo", response = HistoryVo.class)
    @PostMapping("/history")//pass
    public Result getUserBorrowHistory(@RequestBody User user) {
        return service.getUserBorrowHistoryByUid(user.getUid());
    }

    @ApiOperation(value = "获取用户的当前借阅信息", notes = "返回数据是列表， 列表项是HistoryVo", response = HistoryVo.class)
    @PostMapping("/now") //pass
    public Result getUserBorrowNow(@RequestBody User user) {
        return service.getUserBorrowNowByUid(user.getUid());
    }



}
