package cn.thg.community.service.impl;

import cn.thg.community.dao.IUserDao;
import cn.thg.community.dao.LoginTicketMapper;
import cn.thg.community.entity.LoginTicket;
import cn.thg.community.entity.User;
import cn.thg.community.service.IUserService;
import cn.thg.community.util.CommunityConstant;
import cn.thg.community.util.CommunityUtils;
import cn.thg.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService, CommunityConstant {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Value("${community.path.domain}")
    private String domainField;

    @Value("${server.servlet.context-path}")
    private String contextPath;

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

    @Override
    public Map<String, String> register(User user) {
        if(user==null)throw new IllegalArgumentException("参数不能为空");
        Map<String,String> map=new HashMap<>();
        if(StringUtils.isBlank(user.getUsername())){
            map.put("usernamemsg","账号不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            map.put("passwordmsg","密码不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())){
            map.put("emailmsg","邮箱不能为空！");
            return map;
        }
        User u=userDao.selectByName(user.getUsername());
        if(u!=null){
            map.put("usernamemsg","该账号名已被注册！");
            return map;
        }
        u=userDao.selectByEmail(user.getEmail());
        if(u!=null){
            map.put("emailmsg","该邮箱已被注册！");
            return map;
        }

        user.setSalt(CommunityUtils.getUUID().substring(0,5));
        user.setPassword(CommunityUtils.md5(user.getPassword()+user.getSalt()));
        user.setStatus(0);//未激活
        user.setType(0);//普通用户
        user.setActivationCode(CommunityUtils.getUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        userDao.insertUser(user);

        //发送激活邮件
        Context context=new Context();
        context.setVariable("mail",user.getEmail());
        context.setVariable("url",domainField+contextPath+"/activation/"+user.getId()+"/"+user.getActivationCode());
        String content=templateEngine.process("/mail/activation",context);
        mailClient.sendMailMessage(user.getEmail(),"激活邮件",content);

        return map;
    }

    @Override
    public Map<String, String> login(String username, String password, Integer expiredSeconds) {
        Map<String,String> map=new HashMap<>();
        if(StringUtils.isBlank(username)){
            map.put("usernameMsg","账号不能为空！");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("passwordMsg","密码不能为空！");
            return map;
        }
        User user=userDao.selectByName(username);
        if(user==null){
            map.put("usernameMsg","该账号不存在！");
            return map;
        }
        if(user.getStatus()==0){
            map.put("usernameMsg","该账号未激活！");
            return map;
        }
        if(!user.getPassword().equals(CommunityUtils.md5(password+user.getSalt()))){
            map.put("passwordMsg","密码错误！");
            return map;
        }

        //生成登录凭证，插入数据库
        LoginTicket ticket=new LoginTicket();
        ticket.setUserId(user.getId());
        ticket.setTicket(CommunityUtils.getUUID());
        ticket.setStatus(0);
        ticket.setExpired(new Date(System.currentTimeMillis()+expiredSeconds*1000));
        loginTicketMapper.insertLoginTicket(ticket);
        map.put("ticket",ticket.getTicket());

        return map;
    }

    @Override
    public void logout(String ticket) {
        loginTicketMapper.updateStatus(ticket,1);
    }

    @Override
    public int activation(int userId, String code){
        User user=userDao.selectById(userId);
        if(user.getStatus()==1){//已激活过
            return ACTIVATOIN_REPEAT;
        }else if(user.getActivationCode().equals(code)){//未激活
            userDao.updateStatus(userId,1);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_DAILURE;
        }
    }
}
