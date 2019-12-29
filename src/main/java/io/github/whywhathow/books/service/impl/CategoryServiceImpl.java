package io.github.whywhathow.books.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.github.whywhathow.books.mapper.CategoryMapper;
import io.github.whywhathow.books.pojo.Category;
import io.github.whywhathow.books.service.CategoryService;
import io.github.whywhathow.books.utils.Result;
import io.github.whywhathow.books.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper mapper;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
//    @Cacheable(value = "category")// 启用缓存
    public Result getAll() {
        // 如果缓存服务器中存在category对象
        Result result = (Result) redisTemplate.opsForValue().get("bookcategory");
        if (result != null && result.isSuccess() && result.getData() != null && !StringUtils.isEmpty(result)) {
            System.err.println("这并不是第一次查询.....,数据存在redis 中");
            result.setMessage(result.getMessage() + " redis.........................");
            return result;
        }
        // 缓存服务器并不存在category对象
        result = new Result();
        result.setSuccess(false);
        List<Category> list = null;

        try {
            list = mapper.selectAll();
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem , selectAllCategory");
            return result;
        }
        // 递归生成分类
        List<Category> categoryList = null;
        try {
            categoryList = getChildrens(0, list);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        // TODO redis 缓存数据库的设置
        result.setData(categoryList);
        result.setSuccess(true);
        result.setMessage("Success in selectAll category ");
        result.setCode(202);
        redisTemplate.opsForValue().set("bookcategory", result);
        return result;
    }

    @Override
//    @CacheEvict  // 删除缓存
    public Result insertCategory(Category category, HttpServletRequest request) {
        Result result = new Result();
        result.setSuccess(false);
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        int res = 0;
        try {
            res = mapper.insertSelective(category);

        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- insert category");
            return result;
        }
        result.setCode(202);
        result.setMessage("Success insert category ");
        result.setSuccess(true);
        // try catch 处理下 ...
        redisTemplate.opsForValue().set("bookcategory", null);

        return result;

    }

    @Override
//    @CacheEvict // 删除缓存
    public Result updateCategory(Category category, HttpServletRequest request) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0 ;
        try {
            category.setUpdateTime(new Date());
            res = mapper.updateByPrimaryKeySelective(category);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  --");
            return result;
        }
        redisTemplate.opsForValue().set("bookcategory", null);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in update category! ");
        result.setData(res);
        return result;


    }

    @Override
    public Result deleteCategory(Integer cid) {
        Result result = new Result();
        result.setSuccess(false);
        int res = 0 ;
        try {
            res = mapper.updateTodelete(cid);
//            res = mapper.deleteByPrimaryKey(cid);
        } catch (Exception e) {

            result.setCode(500);
            result.setMessage("Server's problem,  -- delete category ");
            return result;
        }
        redisTemplate.opsForValue().set("bookcategory", null);//  重置数据
        result.setSuccess(true);
        result.setCode(202);
        result.setData(res);
        result.setMessage("Success in delete category ");
        return result;

//        return null;
    }

    @Override
    public Result selectTolist(CategoryVo vo) {
        Result result = new Result();
        result.setSuccess(false);
        PageInfo<Category> info = null;
        try {
            PageHelper.startPage(vo.getStart(), vo.getRows());
            info = PageInfo.of(mapper.selectToList(vo.getCategory()));
            info.setTotal(mapper.selectToListCount(vo.getCategory()));
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
    @Transactional(rollbackFor = Exception.class)
    public Result changeListByBidAndState(List<Integer> list, boolean state) {

        Result result = new Result();
        result.setSuccess(false);
        int res = 0;
        try {
            Date date = new Date();
            for (Integer cid : list) {
                res += mapper.updateByPidToChangeState(cid, state, date);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("Server's problem,  -- set category change list");
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
    public Result detailByCid(Integer cid) {
        Result result = new Result();
        result.setSuccess(false);
        Category category = null;
//        int res = 0;
        try {
            category = mapper.selectByPrimaryKey(cid);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("Server's problem,  -- detail by cid ");
            return result;
        }
        result.setData(category);
        result.setSuccess(true);
        result.setCode(202);
        result.setMessage("Success in delete category");
//        result.setData(res);
        return result;
    }

    @Override
    public Result edit(Category category, HttpServletRequest request) {
        System.err.println(category);
        if (StringUtils.isEmpty(category.getCid())) {
            return insertCategory(category, request);
        } else {
            return updateCategory(category, request);
        }
    }


    /**
     * @return java.util.List<com.sdut.onlinestore.pojo.Category>
     * @Author whywhathow
     * TODO: 利用递归实现无限分类
     * 后端:
     * @Param [parentId, list]
     **/
    public ArrayList<Category> getChildrens(Integer parentId, List<Category> list) {
        ArrayList<Category> arrayList = new ArrayList<>(50);
        for (Category cat : list) {
//            Integer id = cat.getParentid();
            if (parentId == cat.getParentid()) {
                cat.setChildernList(getChildrens(cat.getCid(), list));
                arrayList.add(cat);
            }

        }
        return arrayList;
    }


}
