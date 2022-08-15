package team2.entry_automation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team2.entry_automation.service.FieldUserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author JackChern @create 2022-08-14 17:15
 */
@Controller
public class LoginController {
    @Autowired
    private FieldUserService fieldUserService;

    //   登录功能
    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    html的 name="code"相匹配，自动注入属性！
    public String login( Model model, String username, String password
//                         HttpSession session
                        ) {
        Map<String, Object> loginMap = fieldUserService.login(username, password);

        if (loginMap.containsKey("succeed")) {
            return "redirect:/index";
        }

        model.addAttribute("usernameMsg",loginMap.get("usernameMsg"));
        model.addAttribute("passwordMsg",loginMap.get("passwordMsg"));
        return "/site/login";
//        return "redirect:/index";
    }
}
