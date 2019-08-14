package ru.dsoccer1980.web;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dsoccer1980.domain.Role;
import ru.dsoccer1980.domain.User;
import ru.dsoccer1980.exception.NotFoundException;
import ru.dsoccer1980.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
public class DefaultController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/admin";
    }

    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getRemoteUser());
        return "/user";
    }

    @GetMapping("/admin/user/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/admin/user/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(User user) {
        User userFromDb = userRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("user id not found"));
        userFromDb.setUsername(user.getUsername());
        if (!userFromDb.getPassword().equals(user.getPassword())) {
            setBCryptPassword(userFromDb);
        }

        userRepository.save(userFromDb);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/user/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/admin/user/add")
    public String createPage() {
        return "createUser";
    }

    @PostMapping("/admin/user/add")
    public String createUser(User user) {
        setBCryptPassword(user);
        user.setRoles(Role.USER);
        userRepository.save(user);
        return "redirect:/admin";
    }

    private void setBCryptPassword(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    }


}
