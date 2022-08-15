package team2.entry_automation;

import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import team2.entry_automation.Test.AutoTest;
import team2.entry_automation.auth.AuthHelper;
import team2.entry_automation.controller.LoginController;
import team2.entry_automation.service.DepartmentService;
import team2.entry_automation.service.FieldUserService;
import team2.entry_automation.service.ParentDepartmentService;
import team2.entry_automation.service.UserService;
import team2.entry_automation.staticconst.Const;
import team2.entry_automation.util.PinyinUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest
class EntryAutomationApplicationTests {

    @Autowired
    LoginController loginController;

    @Autowired
    UserService userService;


    @Autowired
    FieldUserService fieldUserService;

    @Autowired
    AutoTest autoTest;

    @Autowired
    DepartmentService departmentService;


    @Test
    void contextLoads() throws Exception {
        AuthHelper authHelper = new AuthHelper();
        Map tokenMap = authHelper.getTokenMap("dingt7pr6gsqb7rrlffi", "Ju1SFN2Kmk4LLFYiWED8gdUjY262dnkjcb1KXGoLQdzYdd-v80TGNJiuF2WaSpwM", "GET");
//        Map tokenMap = (Map) JSON.parse(tokenJsonString);
        String token = (String) tokenMap.get("access_token");
        System.out.println(token);
//        UserService userService = new UserService();
//        String userIdJsonString = userService.getUserByMobileNum("18025736629", token);
//        System.out.println(token);
//        System.out.println(userIdJsonString);
//        Map userIdMap = (Map) JSON.parse(userIdJsonString);
//        Map resultMap = (Map) userIdMap.get("result");
//        String userId = (String) resultMap.get("userid");
//        System.out.println("userId:" + userId);
//        String userInfoJsonString = userService.getUserInfoByUserId(userId, token);
//        System.out.println(userInfoJsonString);
//        Map userInfoMap = (Map) JSON.parse(userInfoJsonString);
//        Map userInfoResultMap = (Map) userInfoMap.get("result");
//        String userName = (String) userInfoResultMap.get("name");
//        System.out.println("userName：" + userName);
//        ParentDepartmentService parentDepartmentService = new ParentDepartmentService();
//        String manager857 = parentDepartmentService.getParentDepartment(token, "manager857");
//        System.out.println(manager857);

//        System.out.println("******");
//        System.out.println(PinyinUtils.getPinyin("陈旭湘"));
//
//
//        fieldUserService.register("李旭想");
//        fieldUserService.register("陈许像");

//        autoTest.auto("陈旭湘","18025736629");
        List<Long> allSubDeptList = departmentService.getAllSubDeptList(token, Const.developmentDepartmentCode);
        for (Long l : allSubDeptList) {
            System.out.println(l);
        }

        System.out.println("******");


        List<String> allUserIdList = departmentService.getAllUserIdList(token, allSubDeptList);
        for (String i : allUserIdList) {
            System.out.println(i);
        }

//        System.out.println(allSubDeptList);
    }

}
