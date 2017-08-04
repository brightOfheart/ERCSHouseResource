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
    private static final String CustomersList = "/API/Organization/ttGetCustomersList";//报备客户列表
    private static final String InsertNewCustomer = "/API/Organization/ttInsertNewCustomer";//添加客户
    private static final String InsertRunningsModel = "/API/NewHouse/ttInsertRunningsModel";//确认报备信息
    private static final String REPORTINGORDERDETSIL = "/API/NewHouse/ttGetRunningModel";//报备订单详情
    private static final String UploadImage = "/API/Common/ttUploadImageModel";//上传图片
    private static final String DelImage = "/API/Common/ttDelImageModel";//上传图片
    private static final String RunningsList = "/API/NewHouse/ttGetRunningsList";//获取报备列表
    private static final String DYNAMIC = "/API/DynamicInterface/ttGetDynamicInterfaceListNewHouse";//动态

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

    /**
     * 报备客户列表
     * @param UserID
     * @param KeyWord
     * @param KeyWord
     * @param PageIndex
     * @param callback
     */
    public static void getCustomersList(String UserID,String KeyWord , String PageIndex ,HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserID", "1");
        map.put("KeyWord", KeyWord);
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        new HttpUtils().postNewJson(URL + CustomersList, json, callback);
    }

    /**
     * 添加客户
     * @param UserID
     * @param Name
     * @param CustomerPhoneList  电话集合，用 | 分隔
     * @param Sex 性别 0 男 1 女
     * @param callback
     */
    public static void getInsertNewCustomer(String UserID,String Name , String CustomerPhoneList, String Sex   ,HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserID", "1");
        map.put("Name", Name);
        map.put("CustomerPhoneList", CustomerPhoneList);
        map.put("Sex", Sex );
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        new HttpUtils().postNewJson(URL + InsertNewCustomer, json, callback);
    }

    /**
     * 确认报备信息
     * @param BuildingID
     * @param CustomerID
     * @param CustomerPhoneID
     * @param callback
     */
    public static void getInsertRunningsModel(String BuildingID,String CustomerID , String CustomerPhoneID    ,HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("BuildingID", BuildingID);
        map.put("CustomerID", CustomerID);
        map.put("CustomerPhoneID", CustomerPhoneID );
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        new HttpUtils().postNewJson(URL + InsertRunningsModel, json, callback);
    }

    /**
     * 查看报备订单详情
     * @param Id
     * @param callback
     */
    public static void getReportingOrderDetail(String Id ,HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id","2" );
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        new HttpUtils().postNewJson(URL + REPORTINGORDERDETSIL, json, callback);
    }

    /**
     * 动态管理接口
     *
     * @param PageIndex PageSize BuildingID
     * @param callback
     */
    public static void getDynamic(String PageIndex, String PageSize, String BuildingID, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", PageIndex);
        map.put("PageSize", PageSize);
        map.put("BuildingID", BuildingID);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + DYNAMIC, json, callback);
    }

    /**
     * 上传图片
     * @param GroupID 图片组ID 3
     * @param InterfixID 相关ID 主键
     * @param ImageType 图片类型 参照ImageGroup表 1带看 2确认单 3 备案
     * @param Imagedata 图片字符串 用Base64加密
     * @param callback
     */
    public static void uploadImage(String GroupID ,String InterfixID,String ImageType,String Imagedata ,HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("GroupID",GroupID );
        map.put("InterfixID",InterfixID);
        map.put("ImageType",ImageType );
        map.put("Imagedata",Imagedata );
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        Log.i("-->","ImageType:"+ImageType);
        new HttpUtils().postNewJson(URL + UploadImage, json, callback);
    }


    /**
     * 删除图片
     * @param Id 图片id
     * @param callback
     */
    public static void DelImage(String Id ,HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id",Id );
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        new HttpUtils().postNewJson(URL + DelImage, json, callback);
    }

    /**
     * 报备订单列表
     * @param PageIndex
     * @param callback
     */
    public static void RunningsList(String PageIndex ,HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex",PageIndex);
        map.put("PageSize","10");
        map.put("KeyWord","" );
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        new HttpUtils().postNewJson(URL + RunningsList, json, callback);
    }
}
