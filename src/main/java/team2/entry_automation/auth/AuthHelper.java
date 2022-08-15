package team2.entry_automation.auth;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JackChern @create 2022-08-12 16:48
 */
@Component
public class AuthHelper {
    public Map getTokenMap( String appKey, String appSecret, String httpMethod ) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod(httpMethod);
        OapiGettokenResponse response = client.execute(request);
        Map map = (Map) JSON.parse(response.getBody());
//        System.out.println("这个是用JSON类来解析JSON字符串!!!");
//        for (Object map : maps.entrySet()) {
//            System.out.println(((Map.Entry) map).getKey() + "     " + ((Map.Entry) map).getValue());
//        }
        return map;
    }
}
