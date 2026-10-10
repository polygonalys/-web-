package com.example.gakuboschedules.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gakuboschedules.entity.User;
import com.example.gakuboschedules.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller 
public class LoginController {
    private final UserService userService;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String Login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.checkEmail(email);

        if(user == null || !encoder.matches(password, user.getUserPassword())) {

            model.addAttribute("error", "メールアドレスまたはパスワードが違います");
            model.addAttribute("email", email);
            return "login";
        }

        session.setAttribute("loginUser", user.getUserEmail());
        session.setAttribute("loginUserName", user.getUserName());

        return "login";
    }
    
    @PostMapping("/signup")
    public String signUp(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String confirmpassword,
                         HttpSession session,
                         Model model
                         ) {
        
        if(!password.equals(confirmpassword)) {
            
            model.addAttribute("error", "パスワードが一致していません。");
            return "signup";
        }

        User existing = userService.checkEmail(email);

        if(existing != null) {
            model.addAttribute("error", "このメールアドレスは既に使用されています。");
            return "signup";
        }

        userService.createUser(new User(null, email, encoder.encode(password), name));

        session.setAttribute("loginUser", email);
        session.setAttribute("loginUserName", name);
        return "signup";
    }
    
}
