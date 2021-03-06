package cn.thg.community.controller;

import cn.thg.community.entity.DiscussPost;
import cn.thg.community.entity.Page;
import cn.thg.community.entity.User;
import cn.thg.community.service.IDiscussPostService;
import cn.thg.community.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author: CandyTom
 *
 *
 *@create: 2020-07-17 16:03
 **/
@Controller
public class HomeController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDiscussPostService discussPostService;

    @RequestMapping(value = "/index")
    public String index(Model model, Page page){
        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/index");
        List<DiscussPost> list = discussPostService.selectDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        for(DiscussPost post : list){
            Map<String, Object> map = new HashMap<>();
            map.put("post", post);
            User user = userService.selectById(post.getUserId());
            map.put("user", user);
            discussPosts.add(map);
        }
        model.addAttribute("discussPosts", discussPosts);
        return "/index";
    }

}
