package cn.thg.community;/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: cn.thg.community.IUserDaoTest
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: tanghg
 * @date: 2020/7/17 0017 上午 11:13
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/7/17 0017      tanghg          v1.0.0               修改原因
 */

import cn.thg.community.dao.IUserDao;
import cn.thg.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-17 11:13
 **/
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class IUserDaoTest {

    @Autowired
    private IUserDao userDao;

    @Test
    public void testSelect(){

        User user=userDao.selectById(149);
        System.out.println("selectById -->"+user);

        user=userDao.selectByEmail("nowcoder149@sina.com");
        System.out.println("selectByEmail -->"+user);

        user=userDao.selectByName("niuke");
        System.out.println("selectByName -->"+user);

    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("thg");
        user.setPassword("104816");
        user.setSalt("thg");
        user.setEmail("tanghuoguang@163.com");
        user.setType(1);
        user.setStatus(1);
        user.setHeaderUrl("http://images.nowcoder.com/head/150t.png");
        user.setCreateTime(new Date());

        int row=userDao.insertUser(user);
        System.out.println("insertUser "+row+" -->"+user);
    }

    @Test
    public void testUpdate(){
        int row=userDao.updatePassword(152, "thg104816");
        System.out.println(row);
        row=userDao.updateStatus(152, 0);
        System.out.println(row);
        row=userDao.updateHeader(152, "http://images.nowcoder.com/head/152t.png");
        System.out.println(row);
    }

}
