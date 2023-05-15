package com.iset.authentification.security;


import com.iset.authentification.services.AppAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class SecurityController {
    @Autowired
    AppAuthProvider userService;
    @GetMapping("/accessDenied")
    public String geterror() {
        return "accessDenied";
    }

    @PostMapping("/accessDenied")
    public String posterror() {
        return "accessDenied";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String show() {
        return "index";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               ModelMap modelMap) throws ParseException {
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authenticated = userService.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            return "redirect:/";
        } catch (BadCredentialsException e) {
            modelMap.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login";
        }
    }






    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException
    {
        request.logout();
        return "redirect:/login";
    }
}
