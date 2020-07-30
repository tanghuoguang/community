package cn.thg.community;/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: cn.thg.community.LoginTicketMapperTest
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: tanghg
 * @date: 2020/7/29 0029 下午 4:33
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/7/29 0029      tanghg          v1.0.0               修改原因
 */

import cn.thg.community.dao.LoginTicketMapper;
import cn.thg.community.entity.LoginTicket;
import cn.thg.community.util.CommunityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-29 16:33
 **/
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class LoginTicketMapperTest {

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testInsert(){
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(157);
        loginTicket.setTicket(CommunityUtils.getUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+12*3600*1000));
        int rows=loginTicketMapper.insertLoginTicket(loginTicket);
        System.out.println(rows+":"+loginTicket);
    }

    @Test
    public void testSelect(){
        LoginTicket loginTicket=loginTicketMapper.selectByTicket("193aeb85fa034d9e9c7ce4c9308cc759");
        System.out.println(loginTicket);
    }

    @Test
    public void testUpdate(){
        int rows=loginTicketMapper.updateStatus("193aeb85fa034d9e9c7ce4c9308cc759",1);
    }

}
