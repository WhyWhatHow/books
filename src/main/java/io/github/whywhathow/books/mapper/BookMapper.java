package io.github.whywhathow.books.mapper;

import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.pojo.BookExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookMapper {
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer bid);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExampleWithBLOBs(BookExample example);

    List<Book> selectByExample(BookExample example);

    Book selectByPrimaryKey(String bid);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExampleWithBLOBs(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKeyWithBLOBs(Book record);

    int updateByPrimaryKey(Book record);


    //TODO 一下代码为之前 book 项目的代码， 可能有错，需要小心使用

    @Select("select * from book")
    List<Book> selectAll();

    @Select("select count(bid) from book")
    long selectCount();

    @Select("select count(bid) from book where cid =#{cid}")
    long selectCountByCategory(Integer cid);

    @Select("select * from book where cid = #{cid}")
    List<Book> selectByCategory(Integer cid);

    @Delete("update   book  set state=2  where bid =#{bid}")
    int updateToDeleted(String bid);

    @Select("select * from book where bname like concat('%',#{panme},'%') or  author like concat('%',#{panme},'%')  or publish like concat('%',#{panme},'%')")
    List<Book> selectByLike(String name);

    // TODO 批量修改资产状态出错!!!!
    @Update("update  book set state = #{state} ,update_time =  #{date} where bid = #{bid}")
    int updateByPidToChangeState(String bid, Integer state, Date date);


    //    @Select("select * from book where name like concat('%',#{name},'%')  ")
    List<Book> selectToList(Book book);

    //    @Select("select * from book ")
    long selectToListCount(Book book);

    //    @Select(" ")
    List<Book> selectToSideShow();


    @Select("select * from book where uid = #{uid} and state = #{state}")
    List<Book> selectByStateAndUid(String uid, Integer state);

    @Select("select book.* from book , category where uid = #{uid} and cname = #{cname} and category.cid = book.cid  ")
    List<Book> selectByCnameAndUid(String uid, String cname);

    List<Book> selectToReportLess(Book book);

    List<Book> selectToReportMore(Book book);

    List<Book> selectToReport(Book book);
}