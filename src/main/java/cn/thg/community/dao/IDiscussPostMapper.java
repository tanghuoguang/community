package cn.thg.community.dao;/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: cn.thg.community.dao.IDiscussPostMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: tanghg
 * @date: 2020/7/17 0017 下午 3:24
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/7/17 0017      tanghg          v1.0.0               修改原因
 */

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
