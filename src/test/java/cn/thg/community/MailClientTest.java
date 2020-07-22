package cn.thg.community;/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: cn.thg.community.MailClientTest
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: tanghg
 * @date: 2020/7/22 0022 下午 11:49
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/7/22 0022      tanghg          v1.0.0               修改原因
 */

import cn.thg.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-22 23:49
 **/
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailClientTest {

    @Autowired
    private MailClient mailClient;

    @Test
    public void testSendMail(){
        mailClient.sendMailMessage("2521830193@qq.com","test","hello~thg");
    }


}
