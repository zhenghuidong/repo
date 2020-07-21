package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/toLogin.action";
    }

    @PostMapping("/login")
    public String login(String username,String password,HttpSession session){
        if ("zs".equals(username) && "123".equals(password)){
            session.setAttribute("user",username);
            return "redirect:/itemList.action";
        }else {
            return "redirect:/toLogin.action";
        }
    }
}