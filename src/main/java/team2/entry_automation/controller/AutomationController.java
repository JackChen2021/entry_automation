package team2.entry_automation.controller;

import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team2.entry_automation.auth.AuthHelper;
import team2.entry_automation.service.FieldUserService;
import team2.entry_automation.service.ParentDepartmentService;
import team2.entry_automation.service.UserService;
import team2.entry_automation.staticconst.Const;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author JackChern @create 2022-08-14 19:05
 */
@Controller
public class AutomationController {
    @Autowired
    AuthHelper authHelper;

    @Autowired
    UserService userService;

    @Autowired
    ParentDepartmentService parentDepartmentService;

    @Autowired
    FieldUserService fieldUserService;


    @RequestMapping(path = "auto", method = RequestMethod.POST)
    public String auto( Model model, String username, String number ) throws Exception {
        Map tokenMap = authHelper.getTokenMap("dingt7pr6gsqb7rrlffi",
                "Ju1SFN2Kmk4LLFYiWED8gdUjY262dnkjcb1KXGoLQdzYdd", "GET");
        if (!tokenMap.get("errorcode").equals("0")) {
            model.addAttribute("tokenMsg", "获取token失败！");
            return "/site/result";
        }
        String token = (String) tokenMap.get("access_token");
        //通过手机号查询对应的用户Id
        Map userMapByMobileNum = userService.getUserMapByMobileNum(number, token);
        if (!userMapByMobileNum.get("errorcode").equals("0")) {
            model.addAttribute("userMsg", "输入的员工信息有误！");
            return "/site/result";
        }

        String userId = (String) ((Map) userMapByMobileNum.get("result")).get("userid");

        //通过id查对应的名字
        Map userInfoMapByUserId = userService.getUserInfoMapByUserId(userId, token);
        if (!userInfoMapByUserId.get("errorcode").equals("0")) {
            model.addAttribute("userMsg", "输入的员工信息有误！");
            return "/site/result";
        }
        String name = (String)((Map) userInfoMapByUserId.get("result")).get("name");
        if (!name.equals(username)) {
            model.addAttribute("userMsg", "输入的员工信息有误！");
            return "/site/result";
        }

        //通过id查其所在及父部门id
        Map parentDepartmentMap = parentDepartmentService.getParentDepartmentMap(token, userId);
        if (!parentDepartmentMap.get("errorcode").equals("0")) {
            model.addAttribute("departmentMsg", "查不到该用户对应的部门！");
            return "/site/result";
        }
        Set<String> departmentIdSet = (Set<String>) ((Map) parentDepartmentMap.get("result")).get("parent_list");
        if (!departmentIdSet.contains(Const.developmentDepartmentCode)){
            model.addAttribute("departmentMsg", "该员工不属于开发部门！");
            return "/site/result";
        }

        fieldUserService.register(username);

        return "/index";
    }

}
