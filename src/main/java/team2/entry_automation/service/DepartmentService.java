package team2.entry_automation.service;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserListidRequest;
import com.dingtalk.api.request.OapiV2DepartmentListsubidRequest;
import com.dingtalk.api.response.OapiUserListidResponse;
import com.dingtalk.api.response.OapiV2DepartmentListsubidResponse;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author JackChern @create 2022-08-15 9:46
 */
@Service
public class DepartmentService {
    List<Long> allSubDeptList = new ArrayList<>();

    public List<Long> getAllSubDeptList( String token, Long deptId ) throws ApiException {
        List<Long> list = new ArrayList<>();
        list.add(deptId);
        backtracking(token, list);
        return allSubDeptList;

    }

    public List<Long> getSubDeptList( String token, Long deptId ) throws ApiException {
        List<Long> deptList = new ArrayList<>();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listsubid");
        OapiV2DepartmentListsubidRequest req = new OapiV2DepartmentListsubidRequest();
        req.setDeptId(deptId);
        OapiV2DepartmentListsubidResponse rsp = client.execute(req, token);
        Map returnMap = (Map) (JSON.parse(rsp.getBody()));
        Map resultMap = (Map) returnMap.get("result");
        List<Integer> deptArr = (List<Integer>) resultMap.get("dept_id_list");
//        List<Object> dataArr = JSONArray.parseArray(resultMap.get("dept_id_list"),Object.class);
//        System.out.println(Arrays.toString(deptArr.toArray()));
        for (Integer deptID : deptArr) {
            deptList.add(deptID.longValue());
        }
        return deptList;
    }

    private void backtracking( String token, List<Long> inputDeptId ) throws ApiException {
        if (inputDeptId.size() == 0) {
            return;
        }
        for (int i = 0; i < inputDeptId.size(); i++) {
            backtracking(token, getSubDeptList(token, inputDeptId.get(i)));
            allSubDeptList.add(inputDeptId.get(i));
        }
    }

    public List<String> getAllUserIdList( String token, List<Long> allSubDeptList ) throws ApiException {
        List<String> allUserIdList = new ArrayList<>();
        for (Long deptID: allSubDeptList){
            for(String userID : getUserIdList(token,deptID)){
                allUserIdList.add(userID);
            }
        }
        return allUserIdList;
    }


    public List<String> getUserIdList( String token, Long deptId ) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/user/listid");
        OapiUserListidRequest req = new OapiUserListidRequest();
        req.setDeptId(deptId);
        OapiUserListidResponse rsp = client.execute(req, token);
        Map returnMap = (Map) JSON.parse(rsp.getBody());
        Map resultMap = (Map) returnMap.get("result");
        List<String> userIdList = (List<String>) resultMap.get("userid_list");
        return userIdList;
    }


}
