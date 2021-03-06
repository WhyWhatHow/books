package io.github.whywhathow.books.mapper;

import io.github.whywhathow.books.pojo.Category;
import io.github.whywhathow.books.pojo.CategoryExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CategoryMapper {
    int countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    @Select("select * from category  where is_deleted = false ")
    List<Category> selectAll();

    @Update("update  category set is_deleted = true where cid =#{cid} or parentId = #{cid}")
    int updateTodelete(Integer cid);

    long selectToListCount(Category category);

    List<Category> selectToList(Category category);

    @Update("update category  set is_deleted = #{state},update_time = #{date} where cid =#{cid} ")
    int updateByPidToChangeState(Integer cid, boolean state, Date date);
}