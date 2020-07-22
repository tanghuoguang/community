package cn.thg.community.service;/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: cn.thg.community.service.IUserService
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: tanghg
 * @date: 2020/7/16 0016 下午 5:47
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/7/16 0016      tanghg          v1.0.0               修改原因
 */

import cn.thg.community.entity.User;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-16 17:47
 **/
public interface IUserService {
    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);
    int insertUser(User user);
    int updateStatus(int id,int status);
    int updateHeader(int id,String headerUrl);
    int updatePassword(int id,String password);
}
