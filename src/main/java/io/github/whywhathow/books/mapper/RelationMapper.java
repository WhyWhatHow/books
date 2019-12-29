package io.github.whywhathow.books.mapper;

import io.github.whywhathow.books.pojo.Relation;
import io.github.whywhathow.books.pojo.RelationExample;
import io.github.whywhathow.books.pojo.RelationKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RelationMapper {
    int countByExample(RelationExample example);

    int deleteByExample(RelationExample example);

    int deleteByPrimaryKey(RelationKey key);

    int insert(Relation record);

    int insertSelective(Relation record);

    List<Relation> selectByExample(RelationExample example);

    Relation selectByPrimaryKey(RelationKey key);

    int updateByExampleSelective(@Param("record") Relation record, @Param("example") RelationExample example);

    int updateByExample(@Param("record") Relation record, @Param("example") RelationExample example);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);

    @Select("select * from relation where uid=#{uid} and real_return is NULL")
    /***
     * @Author whywhathow
     * 获取用户的当前借阅的图书
     * @Param [uid]
     * @return java.util.List<io.github.whywhathow.books.pojo.Relation>
     **/
    List<Relation> selectByUid(String uid);

    @Select("select * from relation where uid = #{uid} and bid =#{bid} and real_return is NULL")
    int selectByUidAndBid(String uid, String bid);// 当前用户在借阅期内有借阅本书，不用归还
}