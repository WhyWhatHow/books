package io.github.whywhathow.books.service.impl;

import io.github.whywhathow.books.controller.BorrowVo;
import io.github.whywhathow.books.mapper.BookMapper;
import io.github.whywhathow.books.mapper.RelationMapper;
import io.github.whywhathow.books.pojo.*;
import io.github.whywhathow.books.service.MailService;
import io.github.whywhathow.books.service.UserService;
import io.github.whywhathow.books.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import io.github.whywhathow.books.mapper.MenuMapper;
import io.github.whywhathow.books.mapper.UserMapper;
import io.github.whywhathow.books.utils.MD5Util;
import io.github.whywhathow.books.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RelationMapper relationMapper;
    @Autowired
    BookMapper bookmapper;
    @Autowired
    UserMapper mapper;

    @Autowired
    MailService mailService;

    @Override
    @Transactional
    public Result registerUser(User user) {
        Result result = new Result();
        result.setSuccess(false);
        user.setUid(MD5Util.setUUID());
        int res = 0;
        // 添加用户
        user.setCreateTime(new Date());
        user.setState(0);
        user.setRid(2);//  默认角色

        // TODO  密码加密
        String password = null;
        try {
            password = MD5Util.getEncryption(user.getPassword());

        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            result.setCode(500);
            result.setMessage("Server Problem!! -- register user encryption ");
            return result;
        }


        user.setPassword(password);

        try {
            res = mapper.insertSelective(user);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server Problem!! -- register user ");
            return result;
        }
        // TODO 账户激活 ,或者放到另外一个请求中

        result.setSuccess(true);
        result.setMessage("用户注册成功....");
        result.setCode(202);
        result.setData(1);
        return result;
    }


    @Override//TODO  2019年11月5日21:27:39 check  if user have overdate book ???
    public Result loginUser(User user, HttpServletRequest request) {
//        return null;
        Result result = new Result();
        result.setSuccess(false);
        //  加密登录密码, 加密 md5
        String encryption = "";
        try {
            encryption = MD5Util.getEncryption(user.getPassword());
        } catch (Exception e) {
//            e.printStackTrace();
            result.setCode(500);
            result.setMessage("Server Problem!! -- Login user ,encryption");
            return result;
        }
        // 根据用户名获取用户
        User user1 = null;
        try {
            user1 = mapper.selectByUsername(user.getUsername());
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server Problem!! -- Login user ");
            return result;
        }
        result.setCode(202);
        if (user1 != null) {
            if (encryption.equals(user1.getPassword())) {
                dealWithOverdueBook(user1);
                result.setSuccess(true);
                result.setData(user1);
                request.getSession().setAttribute("user", user1);
                result.setMessage("Login In Success");
            } else {
                result.setMessage("password or username is wrong!! ");
            }
        } else {
            result.setMessage("userName is wrong");
        }
        return result;
    }

    @Override
    public Result updateUser(User user) {
        Result result = new Result();
        result.setSuccess(false);
        // 根据用户uid 修改信息, if 动态sql 所以好一点
        try {
            user.setUpdateTime(new Date());
//            user.setUpdateTime(LocalDateTime.now());
            mapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("Server Problem,--update user");
            return result;
        }
//        result.setData(user);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("update user success");
        return result;

    }

    @Autowired
    MenuMapper menuMapper;

    // TODO 将menu 菜单加载到redis中
    @Transactional
    @Override
    public Result getMenu(User user) {
        Result result = new Result();
        result.setSuccess(false);
        Integer rid = null;
        // !.获取用户对应的角色
        try {
            rid = user.getRid();
//            rid = mapper.selectByUserToRid(user);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get rid false in getMenuMethod");
            return result;
        }
        Role role = new Role(rid);
        // 获取 该角色对应的权限(所谓的菜单)
        List<Menu> list = null;
        try {
            list = menuMapper.selectByRole(role);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- get menu list false  in getMenu menthod");
            return result;
        }

        // 处理菜单 , 注意 角色必须对应一个 根菜单
        ArrayList<Menu> childrens = new ArrayList<>(50);
        try {
            childrens = getChildrens(0, list);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        System.err.println("=---------------------------------------=");
        for (Menu menu : list) {
            System.err.println(menu);

        }
        System.err.println("=---------------------------------------=");
        result.setData(childrens);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("成功获取菜单数据");
        return result;

    }

    private static String subject = "账号激活码";

    @Override
    public Result sendACtiveCodeToUser(User user) {

        Result result = new Result();
        String code = "";
        user.setUid(MD5Util.setUUID());
        code = MD5Util.setUUID() + MD5Util.setUUID();

        String content = "<h3> 激活码: <a href ='#'>" + code + "</a> </h3>";
        result.setSuccess(false);
        int res = 0;
        try {
            res = mapper.insert(user);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        int mail = 0;
        try {
            mail = mailService.sendHtmlMail(user.getEmail(), subject, content);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- send code to user ");
            return result;
        }
        result.setCode(202);
        if (mail == 1 && res == 1) {
            result.setSuccess(true);
            result.setData(user);
            result.setMessage("Success in sending user active code, please enter your email to check");
        } else {
            result.setMessage("bad in send email or generator active code !");
        }
        return result;

    }

    @Override
    public Result checkUserName(String username) {
        Result result = new Result();
        result.setSuccess(false);
        User user = null;
        try {
            user = mapper.selectByUsername(username);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        if (StringUtils.isEmpty(user)) {
            result.setMessage("改用户名不存在");
            return result;
        } else {
            result.setMessage("用户名存在");
            result.setData(user);
            result.setCode(202);
            result.setSuccess(true);
        }
        return result;
    }

    @Override
    public Result borrowBook(BorrowVo vo) {
        Result result = new Result();
        result.setSuccess(false);
        User user = null;
        Book book = null;
        // 1. 获取用户信息(用户未登录是可以查看图书详情), 保证用户存在,检查用户状态,是否有超期现象若有，拒绝借阅，并提示超期 TODO : 优化 用户信息, 用户信息可以保存在redis里,减少数据库的访问次数
        try {
            user = mapper.selectByPrimaryKey(vo.getUid());
            // 用户存在超期行为,不可以借书
            if (user.getOwe() > 0) {
                result.setMessage("存在超期图书，请缴费后在进行借阅行为");
                return result;
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- 获取用户信息失败");
            return result;
        }
        // 2. 获取图书信息, 保证图书可以借阅
        try {
            book = bookmapper.selectByPrimaryKey(vo.getBid());
            if (book.getCurrent() == 0) {
                result.setMessage("由于图书过于热门,已经被读者全都借走了,请您隔段时间再来借阅图书!!!");
                return result;
            }
            book.setBorrowStatus(); // 设置图书借阅后的状态
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- 获取图书信息失败");
            return result;
        }
        // 3. 用户借书
        Relation relation = new Relation(vo);
        return borrow(relation, book);
    }

    @Transactional
// 用户借书核心代码
    Result borrow(Relation relation, Book book) {
        Result result = new Result();
        result.setSuccess(false);
        try {
            bookmapper.updateByPrimaryKeySelective(book);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("Server's problem, -- error: update Book information in Borrow a book");
            return result;
        }
        try {
            relationMapper.updateByPrimaryKeySelective(relation);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server's problem,  -- error: update book relation in borrow a book");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("用户借书成功");
        return result;
    }


    @Override
    @Transactional
    public Result returnBook(BorrowVo vo) {
        Result result = new Result();
        result.setSuccess(false);
        // 1.  修改图书数量
        try {
            bookmapper.updateCurrentNumByBid(vo.getBid());
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- return book in update book current number ");
            return result;
        }
        // 2. 获取当前系统时间, 用户与本书的节约关系,判断是否超期
        int overTime = 0;
        Relation relation = null;
        try {
            Date now = new Date();
            RelationKey key = new RelationKey(vo.getUid(), vo.getBid());
            relation = relationMapper.selectByPrimaryKey(key);
            relation.setRealReturn(now);
            overTime = TimeUtils.getOverTime(now, relation.getBorrowTime());
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- user return book in get user-book relation  ");
            return result;
        }
        // 3. 超期处理,为用户设置罚款,并修改用户的状态为不可借阅状态
        if (overTime > 0) {
            try {
                mapper.updateUserOweAndState(vo.getUid(), overTime);
            } catch (Exception e) {
                result.setCode(500);
                result.setMessage("Server's problem,  -- user return book in set user status to owe  ");
                return result;
            }
        }
        // 4. 用户还书
        try {
            relationMapper.updateByPrimaryKeySelective(relation);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- user return book in update relation");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("success in return books ");
        return result;
    }

    @Override
    public Result payOwe(String uid) {
        Result result = new Result();
        result.setSuccess(false);
        try {
            mapper.updateUserOweToNormal(uid);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- user pay money for owe ");
            return result;
        }
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success user pay owe money , now , you can borrow books.");
        return result;

    }


    public ArrayList<Menu> getChildrens(Integer parentId, List<Menu> list) {
        ArrayList<Menu> arrayList = new ArrayList<>(100);
        for (Menu menu : list) {
            if (parentId.equals(menu.getParentid())) {
                menu.setChildrenList(getChildrens(menu.getId(), list));
                arrayList.add(menu);
            }
        }
        return arrayList;
    }

    public User dealWithOverdueBook(User user) {// 检查用户是否存在超期图书,并确定是否设置罚款金额
        try {
            List<Relation> list = relationMapper.selectByUid(user.getUid());
            int sum = 0;
            Date now = new Date();
            for (Relation relation : list) {
                sum += TimeUtils.getOverTime(now, relation.getNeedReturn());
            }
            if (sum > 0) {
                user.setState(2);//
                user.setOwe(sum);
                mapper.updateByPrimaryKeySelective(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
