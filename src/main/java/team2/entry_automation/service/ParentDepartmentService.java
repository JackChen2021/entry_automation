package team2.entry_automation.service;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiV2DepartmentListparentbyuserRequest;
import com.dingtalk.api.response.OapiV2DepartmentListparentbyuserResponse;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @author JackChern @create 2022-08-14 16:03
 */
@Service
public class ParentDepartmentService {
    public Map getParentDepartmentMap( String token, String userId) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listparentbyuser");
        OapiV2DepartmentListparentbyuserRequest req = new OapiV2DepartmentListparentbyuserRequest();
        req.setUserid(userId);
        OapiV2DepartmentListparentbyuserResponse rsp = client.execute(req, token);
        return (Map) JSON.parse(rsp.getBody());
    }
}
