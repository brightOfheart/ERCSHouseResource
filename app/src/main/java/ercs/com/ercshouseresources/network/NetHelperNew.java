package ercs.com.ercshouseresources.network;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 * 新房网络接口辅助类
 */

public class NetHelperNew {
    public static final String URL = "http://192.168.1.55:8899";//IP地址
    private static final String LOGIN = "/API/Account/Login";//用户登录
    private static final String BuildingsList = "/API/NewHouse/ttGetBuildingsList";//新房列表
    private static final String AreaList = "/API/Common/ttGetAreaList";//新房区域列表
    private static final String HOUSEDETAIL = "/API/NewHouse/ttGetBuildingModel";//房源详情

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


    /**
     * 新房列表
     * @param AreaID
     * @param BuildingTypeID 房源类型
     * @param callback
     */
    public static void NewBuildingsList(String PageIndex, String AreaID, String BuildingTypeID, String KeyWord, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("AreaID", AreaID);
        map.put("BuildingTypeID", BuildingTypeID);
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        map.put("KeyWord", KeyWord);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        new HttpUtils().postNewJson(URL + BuildingsList, json, callback);
    }

    /**
     * 新房区域列表
     * @param cityid
     * @param callback
     */
    public static void AreaList(String cityid, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("cityid", cityid);

        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        new HttpUtils().postNewJson(URL + AreaList, json, callback);
    }

    /**
     * 房源详情
     * @param BuildingID
     * @param UserID
     * @param callback
     */
    public static void getHouseDetail(String BuildingID,String UserID, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("BuildingID", BuildingID);
        map.put("UserID", UserID);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postNewJson(URL + HOUSEDETAIL, json, callback);
    }
}
