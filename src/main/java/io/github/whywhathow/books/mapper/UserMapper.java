package io.github.whywhathow.books.mapper;

import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.pojo.User;
import io.github.whywhathow.books.pojo.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Update("update user set state = 2 ,owe = owe+#{overTime}  where uid = #{uid}")
    int updateUserOweAndState(String uid, int overTime);// 更新用户的欠款状态

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    @Select("SELECT b.*, FROM book b , relation r   WHERE r.`uid` = #{uid} AND r.`bid`= b.`bid` AND real_return IS NULL")
    List<Book> selectUserBorrorwHistory(String uid);

    @Update("update user set owe =0, state = 1 where uid = #{uid}")
    int updateUserOweToNormal(String uid);

}