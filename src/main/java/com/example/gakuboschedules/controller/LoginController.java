package com.example.gakuboschedules.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gakuboschedules.entity.User;
import com.example.gakuboschedules.service.UserAccountDetailsService;
import com.example.gakuboschedules.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller 
public class LoginController {
    private final UserAccountDetailsService userAccountDetailsService;
    private final UserService userService;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public LoginController(UserAccountDetailsService userAccountDetailsService, UserService userService) {
        this.userAccountDetailsService = userAccountDetailsService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
    

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // @PostMapping("/login")
    // public String Login(@RequestParam String userId,
    //                     @RequestParam String userPassword,
    //                     HttpSession session,
    //                     Model model) {

    //     encoder.encode(userPassword);
    //     UserDetails user = userAccountDetailsService.loadUserByUsername(userId);

    //     if(user == null || !encoder.matches(userPassword, user.getPassword())) {
    //         model.addAttribute("error", "メールアドレスまたはパスワードが違います");
    //         return "/login";
    //     }
        
    //     session.setAttribute("loginUser", user);
    //     return "redirect:/index";
    // }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    
    @PostMapping("/signup")
    public String signUp(@RequestParam("userId") String userId,
                         @RequestParam("userPassword") String userPassword,
                         @RequestParam("confirmPassword") String confirmPassword,
                         HttpSession session,
                         Model model
                         ) {
        
        if(!userPassword.equals(confirmPassword)) {
            model.addAttribute("error", "パスワードが一致していません。");
            return "signup";
        }

        User exist = userService.checkUsername(userId);

        if(exist != null) {
            model.addAttribute("error", "このユーザーは既に使用されています。");
            return "signup";
        }
        
        String hashPassword = encoder.encode(userPassword);
        userAccountDetailsService.createUser(userId , hashPassword , "USER");

        return "redirect:/login";
    }
    
}
