package team2.entry_automation.service;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.request.OapiV2UserGetbymobileRequest;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.dingtalk.api.response.OapiV2UserGetbymobileResponse;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author JackChern @create 2022-08-12 17:01
 */
@Service
public class UserService {
    public Map getUserMapByMobileNum( String mobileNum, String accessToken ) throws ApiException {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getbymobile");
        OapiV2UserGetbymobileRequest req = new OapiV2UserGetbymobileRequest();
        req.setMobile(mobileNum);
        OapiV2UserGetbymobileResponse rsp = client.execute(req, accessToken);
        Map map = (Map) JSON.parse(rsp.getBody());
        return map;
    }


    public Map getUserInfoMapByUserId( String userId, String accessToken ) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
        OapiV2UserGetRequest req = new OapiV2UserGetRequest();
        req.setUserid(userId);
        OapiV2UserGetResponse rsp = client.execute(req, accessToken);
        return (Map) JSON.parse(rsp.getBody());

    }
}
