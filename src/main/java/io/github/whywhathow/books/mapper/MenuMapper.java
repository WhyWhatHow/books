package io.github.whywhathow.books.mapper;

import io.github.whywhathow.books.pojo.Menu;
import io.github.whywhathow.books.pojo.MenuExample;
import java.util.List;

import io.github.whywhathow.books.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MenuMapper {
    int countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);


    @Select("select * from menu ,role_menu rm  where rid = #{rid} and rm.mid = menu.id")
    List<Menu> selectByRole(int rid);
}