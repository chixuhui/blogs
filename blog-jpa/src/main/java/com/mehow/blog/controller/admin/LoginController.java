package com.mehow.blog.controller.admin;

import com.mehow.blog.entity.User;
import com.mehow.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author: xuhui
 * @Date: 2020/8/4 14:21
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user=userService.checkUser(username,password);
        if(user !=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            System.out.println("- 登录失败-->>"+user);
            /*重定向不能使用 model，重定向拿不到值*/
            attributes.addFlashAttribute("message","用户名与密码错误");
            return  "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String login(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
