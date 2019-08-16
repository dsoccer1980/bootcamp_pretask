package ru.dsoccer1980.web;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dsoccer1980.domain.Role;
import ru.dsoccer1980.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal != null) {
            Set<Role> roles = ((User) ((UsernamePasswordAuthenticationToken) userPrincipal).getPrincipal()).getRoles();
            if (roles.contains(Role.USER)) {
                return "redirect:/user";
            } else if (roles.contains(Role.ADMIN)) {
                return "redirect:/admin";
            }
        }
        return "/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

}
