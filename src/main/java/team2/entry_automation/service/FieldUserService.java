package team2.entry_automation.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team2.entry_automation.dao.UserMapper;
import team2.entry_automation.entity.User;
import team2.entry_automation.util.CommunityUtils;
import team2.entry_automation.util.PinyinUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JackChern @create 2022-08-14 17:24
 */
@Service
public class FieldUserService {
    @Autowired
    private UserMapper userMapper;


    //登录业务
    public Map<String, Object> login( String username, String password ) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (StringUtils.isBlank(username)) {
            map.put("usernameMsg", "账号名不能为空");
            return map;
        }

        if (StringUtils.isBlank(username)) {
            map.put("passwordMsg", "密码不能为空");
            return map;
        }

        // 验证账号
        User user = userMapper.selectByName(username);
        if (user == null) {
            map.put("usernameMsg", "该账号不存在！");
            return map;
        }

        // 验证密码
//      不是用！= 而是！ equal
        if (!user.getPassword().equals(CommunityUtils.md5(password + user.getSalt()))) {
            map.put("passwordMsg", "密码不正确！");
            return map;
        }

        map.put("succeed", "成功登录");
        return map;
    }

    //注册业务
    public String register( String username ) throws Exception {
        String name = PinyinUtils.getPinyin(username);
//        int count = userMapper.countByUserName(name);
//        if (count != 0) {
//            for (int i = 0; i < 100; i++) {
//                if (userMapper.countByUserName(name + i) == 0){
//                    name += i;
//                    break;
//                }
//            }
//        }
        User user = new User();
        user.setUsername(name);
        user.setPassword("6cloud.com");
        userMapper.insertUser(user);

        return name;
    }


}
