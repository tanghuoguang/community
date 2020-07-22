package cn.thg.community;/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: cn.thg.community.IDiscussPostMapperTest
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: tanghg
 * @date: 2020/7/17 0017 下午 3:47
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/7/17 0017      tanghg          v1.0.0               修改原因
 */

import cn.thg.community.dao.IDiscussPostMapper;
import cn.thg.community.entity.DiscussPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-17 15:47
 **/
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class IDiscussPostMapperTest {

    @Autowired
    private IDiscussPostMapper discussPostMapper;

    @Test
    public void testSelect(){
        List<DiscussPost> lists = discussPostMapper.selectDiscussPosts(0,0,10);
        for(DiscussPost post : lists){
            System.out.println(post);
        }
        System.out.println(discussPostMapper.selectDiscussPostRows(0));
    }
}
