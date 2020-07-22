package cn.thg.community.dao;

import cn.thg.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-17 15:24
 **/
@Mapper
@Repository
public interface IDiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    int selectDiscussPostRows(int userId);

}
