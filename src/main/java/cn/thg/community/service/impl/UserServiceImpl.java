package cn.thg.community.service.impl;/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: cn.thg.community.service.impl.UserServiceImpl
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

import cn.thg.community.dao.IUserDao;
import cn.thg.community.entity.User;
import cn.thg.community.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User selectById(int id) {
        return userDao.selectById(id);
    }

    @Override
    public User selectByName(String username) {
        return userDao.selectByName(username);
    }

    @Override
    public User selectByEmail(String email) {
        return userDao.selectByEmail(email);
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public int updateStatus(int id, int status) {
        return 0;
    }

    @Override
    public int updateHeader(int id, String headerUrl) {
        return 0;
    }

    @Override
    public int updatePassword(int id, String password) {
        return 0;
    }
}
