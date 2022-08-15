package team2.entry_automation.util;


import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;

import org.springframework.util.DigestUtils;
import team2.entry_automation.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author JackChern @create 2021-09-02 21:01
 */
public class CommunityUtils {

    //    生成随机字符串
    public static String getUUID() {
//        return UUID.randomUUID().toString().replaceAll("-", " ");错的 不是" "是""，没有空格！cookie中不能包含有空格
        return UUID.randomUUID().toString().replaceAll("-", "");


    }

    //    MD5加密
    // hello -> abc123def456
    // hello + 3e4a8 -> abc123def456abc
    public static String md5( String key ) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
//        不太理解什么意思
//        spring自带的方法：将对象装换成16位的字符串
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    //    fastjson工具
    public static String getJSONString( int code, String msg, Map<String, Object> map ) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
//        return json.tostring();
        return json.toJSONString();
    }

    //    重载1
    public static String getJSONString( int code, String msg ) {
        return getJSONString(code, msg, null);
    }
    //    重载2

    public static String getJSONString( int code ) {
        return getJSONString(code, null, null);
    }

//    测试
    public static void main( String[] args ) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","jack");
        map.put("age",21);
        String jsonString = getJSONString(0, "ok", map);
        System.out.println(jsonString);
    }



}
