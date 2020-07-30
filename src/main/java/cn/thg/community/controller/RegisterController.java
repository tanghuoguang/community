package cn.thg.community.controller;

import cn.thg.community.entity.User;
import cn.thg.community.service.IUserService;
import cn.thg.community.util.CommunityConstant;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class RegisterController implements CommunityConstant {

    private static final Logger logger= LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private Producer producer;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @RequestMapping(path="/register",method = RequestMethod.GET)
    public String register(){
        return "/site/register";
    }

    @RequestMapping(path="/register",method = RequestMethod.POST)
    public String register(Model model,User user){
        Map<String,String> map=userService.register(user);
        if(map==null||map.isEmpty()){ //效验成功并成功为该用户在数据库中创建记录，前往激活
            model.addAttribute("msg","注册成功,我们已经向您的邮箱发送了一封激活邮件,请尽快激活!");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }else{ //效验信息失败，保存错误信息，打回注册页面
            model.addAttribute("usernamemsg",map.get("usernamemsg"));
            model.addAttribute("passwordmsg",map.get("passwordmsg"));
            model.addAttribute("emailmsg",map.get("emailmsg"));
            return "/site/register";
        }
    }

    @RequestMapping(path = "/activation/{userId}/{code}")
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code){
        int result=userService.activation(userId,code);
        if(result==ACTIVATION_SUCCESS){
            model.addAttribute("msg","激活成功，您可以正常使用了！");
        }else if(result==ACTIVATOIN_REPEAT){
            model.addAttribute("msg","无效操作，请勿重复激活！");
        }else {
            model.addAttribute("msg","激活失败，您提供的激活码不正确！");
        }
        model.addAttribute("target","/index");
        return "/site/operate-result";
    }

    @RequestMapping("/login")
    public String login(){
        return "site/login";
    }

    @RequestMapping(path="/login",method = RequestMethod.POST)
    public String login(Model model, String username, String password, String code, boolean rememberme,
                        HttpSession session, HttpServletResponse response){
        //检查验证码
        String verifyCodeText= (String) session.getAttribute("verifyCodeText");
        if(StringUtils.isBlank(code)||StringUtils.isBlank(verifyCodeText)||!code.equalsIgnoreCase(verifyCodeText)){
            model.addAttribute("verifyCodeMsg","验证码错误！");
            return "/site/login";
        }
        Integer expired=rememberme?REMEMBER_EXPIRED_SECONDS:DEFAULT_EXPIRED_SECONDS;
        Map<String,String> map=userService.login(username,password,expired);
        if(map.containsKey("ticket")){
            Cookie cookie=new Cookie("ticket",map.get("ticket"));
            cookie.setPath(contextPath);
            cookie.setMaxAge(expired);
            response.addCookie(cookie);
            return "redirect:/index";
        }else{
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "/site/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/login";
    }

    @RequestMapping("/verifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpSession session){
        String text=producer.createText();
        BufferedImage image=producer.createImage(text);
        session.setAttribute("verifyCodeText",text);

        response.setContentType("image/png");
        try {
            OutputStream out=response.getOutputStream();
            ImageIO.write(image,"png",out);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

}
