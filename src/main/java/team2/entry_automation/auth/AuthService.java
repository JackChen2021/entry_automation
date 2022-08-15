package team2.entry_automation.auth;

import com.aliyun.tea.*;
import com.aliyun.teautil.*;
import com.aliyun.dingtalkoauth2_1_0.*;
import com.aliyun.dingtalkoauth2_1_0.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;

/**
 * @author JackChern @create 2022-08-14 11:42
 */
public class AuthService {
    /**
     * 使用 Token 初始化账号Client
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dingtalkoauth2_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
//        com.aliyun.dingtalkoauth2_1_0.Client client = Sample.createClient();
        GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest()
                .setAppKey("dingeqqpkv3xxxxxx")
                .setAppSecret("GT-lsu-taDAxxxsTsxxxx");
        try {
//            client.getAccessToken(getAccessTokenRequest);
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }
    }

}
