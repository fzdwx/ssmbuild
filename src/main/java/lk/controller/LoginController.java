package lk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author likeLove
 * @time 2020-08-02  16:28
 */
@Controller
@RequestMapping ("/user")
public class LoginController {
    @RequestMapping ("/goLogin")
    public String goLogin() {
        return "login";
    }

    @RequestMapping ("/login")
    public String login(String username, String pwd, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (username != null && pwd != null) {
            HttpSession session = req.getSession();
            session.setAttribute("login", "true");
            session.setAttribute("username", username);
            return "redirect:/book/getAllBooks";
        } else {
            return "redirect:/user/goLogin";
        }
    }
}
