package cn.thg.community.service;

import cn.thg.community.entity.DiscussPost;

import java.util.List;

public interface IDiscussPostService {

    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    int selectDiscussPostRows(int userId);

}
