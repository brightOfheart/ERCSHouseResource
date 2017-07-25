package ercs.com.ercshouseresources.network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 * 新房网络接口辅助类
 */

public class NetHelperNew {
    public static final String URL = "http://192.168.1.55:8899";//IP地址
    private static final String LOGIN = "/API/Account/Login";//用户登录


    /**
     *  登录接口
     * @param LoginName
     * @param LoginPass
     * @param callback
     */
    public static void login(String LoginName, String LoginPass, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("LoginName", LoginName);
        map.put("LoginPass", LoginPass);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postNewLoginJson(URL + LOGIN, json, callback);
    }
}
