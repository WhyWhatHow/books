package io.github.whywhathow.books.mapper;

import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.pojo.BookExample;

import java.util.Date;
import java.util.List;

import io.github.whywhathow.books.vo.BookHotVo;
import io.github.whywhathow.books.vo.CategoryCountVo;
import io.github.whywhathow.books.vo.UserCategoryVo;
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

//    // TODO 批量修改资产状态出错!!!!
//    @Update("update  book set state = #{state} ,update_time =  #{date} where bid = #{bid}")
//    int updateByPidToChangeState(String bid, Integer state, Date date);


    //    @Select("select * from book where name like concat('%',#{name},'%')  ")
    List<Book> selectToList(Book book);

    //    @Select("select * from book ")
    long selectToListCount(Book book);

    @Select(" select * from book order by borrow desc  limit 5")
    List<Book> selectToSideShow();


    @Select("select * from book where uid = #{uid} and state = #{state}")
    List<Book> selectByStateAndUid(String uid, Integer state);

    @Select("select book.* from book , category where uid = #{uid} and cname = #{cname} and category.cid = book.cid  ")
    List<Book> selectByCnameAndUid(String uid, String cname);

    List<Book> selectToReportLess(Book book);

    List<Book> selectToReportMore(Book book);

    List<Book> selectToReport(Book book);

    @Update("update book set current = current+1  where bid = #{bid}")
    int updateCurrentNumByBid(String vid);

    @Select("\n" +
            "SELECT c.cid,cname, COUNT(1) num FROM category c, book b WHERE c.`cid` = b.`cid`  GROUP BY c.`cid`;\n")
    List<CategoryCountVo> selectCategoryCount();

    @Select("SELECT c.cname, c.cid ,COUNT(1) num FROM category c, book b ,relation r \n" +
            "WHERE r.uid = #{uid} AND r.bid = b.bid AND b.`cid` =c.`cid`  \n" +
            "GROUP BY c.`cid`;  \n")
    List<CategoryCountVo> selectUserPieByUid(String uid);

    @Select(
            "\n" +
                    "SELECT bname,r.bid,COUNT(1) num  \n" +
                    "FROM relation r, book b \n" +
                    "WHERE r.`bid` = b.`bid`" +
                    " GROUP BY r.bid\n" +
                    "ORDER BY num  DESC LIMIT 10;\n"
    )
    List<BookHotVo> selectHotBook();

    @Select("select b.* from book b , relation r where r.uid =#{uid} and r.bid = b.bid and b.cid = #{cid}")
    List<Book> selectCategoryInUser(String uid, Integer cid);

    @Update("update book  set state = #{state},update_time = #{date} where bid =#{bid}  ")
    int updateByPidToChangeState(String bid, Integer state, Date date);
}