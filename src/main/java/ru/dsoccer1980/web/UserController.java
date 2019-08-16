package ru.dsoccer1980.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getRemoteUser());
        return "/userPage";
    }

}
