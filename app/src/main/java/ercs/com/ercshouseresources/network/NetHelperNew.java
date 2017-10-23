package ercs.com.ercshouseresources.network;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 * 新房网络接口辅助类
 */

public class NetHelperNew {
   // public static final String URL = "http://soilcity.vicp.net";//IP地址
    //public static final String URL = "http://192.168.1.55:8899";//IP地址
    public static final String URL = "http://300.fang101.com";//IP地址
    private static final String CLERK = "/API/Organization/ttGetUserList";//职员列表
    private static final String LOGIN = "/API/Account/Login";//用户登录
    private static final String BuildingsList = "/API/NewHouse/ttGetBuildingsList";//新房列表
    private static final String CHEAPROOMLIST = "/API/LowPriceHouses/ttGetBuildingsList";//低价房列表
    private static final String AreaList = "/API/Common/ttGetAreaList";//新房区域列表
    private static final String HOUSEDETAIL = "/API/NewHouse/ttGetBuildingModel";//房源详情
    private static final String CHEAPROOMHOUSEDETAIL = "/API/LowPriceHouses/ttGetBuildingModel";//低价房房源详情
    private static final String CustomersList = "/API/Organization/ttGetCustomersList";//报备客户列表
    private static final String CheapCustomersList = "/API/LowPriceHouses/ttGetRunningsList";//低价房报备客户列表
    private static final String InsertNewCustomer = "/API/Organization/ttInsertNewCustomer";//添加客户
    private static final String UpdateCustomer = "/API/Organization/ttUpdateCustomer";//编辑客户
    private static final String InsertRunningsModel = "/API/NewHouse/ttInsertRunningsModel";//确认报备信息
    private static final String CheapInsertRunningsModel = "/API/LowPriceHouses/ttInsertRunningsModel";//低价房确认报备信息
    private static final String REPORTINGORDERDETSIL = "/API/NewHouse/ttGetRunningModel";//新房报备订单详情
    private static final String CHEAPREPORTINGORDERDETSIL = "/API/LowPriceHouses/ttGetRunningModel";//低价房报备订单详情
    private static final String UploadImage = "/API/Common/ttUploadImageModel";//上传图片
    private static final String DelImage = "/API/Common/ttDelImageModel";//上传图片
    private static final String RunningsList = "/API/NewHouse/ttGetRunningsList";//获取报备列表
    private static final String DYNAMIC = "/API/DynamicInterface/ttGetDynamicInterfaceListNewHouse";//动态
    private static final String CHEAPDYNAMIC = "/API/DynamicInterface/ttGetDynamicInterfaceListLowPriceHouse";//低价房动态
    private static final String RENOVATIONLIST = "/API/Decoration/ttGetDecorationCompanyList";//装修列表
    private static final String RENOVATIONLISTDETAIL = "/API/Decoration/ttGetDecorationCompanyInfo";//装修列表详情
    private static final String DecorationPreparation = "/API/Decoration/ttInsertDecorationRunning";//装修报备信息
    private static final String DecorationPreparationOrder = "/API/Decoration/ttGetDecorationRunningList";//装修报备订单信息
    private static final String REN_SELECTLIST = "/API/Decoration/ttGetCaseCondition";//装修案例列表条件查询
    private static final String REN_LIST = "/API/Decoration/ttGetDecorationCaseList";//装修列表查询
    private static final String REN_LISTDETAIL = "/API/Decoration/ttGetDecorationCaseInfo";//装修列表详情查询
    private static final String FINANCIAL = "/API/Finance/ItemList";//金融查询
    private static final String FINANCIALREPORTINGORDERDETSIL = "/API/Finance/AddRunning";//金融报备订单详情
    private static final String FINANCIALRunningsList = "/API/Finance/RunningList";//获取金融订单列表
    private static final String RENORDERDETAIL = "/API/Decoration/ttGetRunningModel";//装修订单详情
    private static final String FINANCIALORDERDETAIL = "/API/Finance/RunningModel";//金融订单详情
    private static final String CITYLIST = "/api/Account/CityList";//城市列表
    private static final String CHOSECITYLIST = "/api/Account/ModifyCity";//选择城市列表
    private static final String UPDATECODE = "/API/Common/RePassSMSCode";//获取修改密码的验证码
    private static final String UPDATEPWD = "/API/Common/RePass";//修改密码
    private static final String FINDPWDCODE = "/API/Common/NewPassSMSCode";//忘记密码验证码
    private static final String FINDPWD = "/API/Common/NewPass";//密码重置
    private static final String UPLOADPHOTOPIC = "/API/Organization/ttUpdateUser";//修改经济人的信息
    private static final String CHECKUPDATE = "/API/Common/ttGetVersionInfo";//检查当前版本是否最新
    private static final String GETBANNER = "/API/AdvertisementInterface/ttGetAdvertisementInterfaceList";//获取广告
    private static final String BANNERDETAIL = "/API/DynamicInterface/ttGetDynamicModel";//获取广告详情
    private static final String GETCHEAPROOM = "/API/LowPriceHouses/ttGetLowPriceHousesCondition";//获取低价房条件
    /**
     * 登录接口
     *
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
     *
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
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + BuildingsList, json, callback);
    }

    /**
     * 低价房列表
     * @param PageIndex
     * @param AreaID
     * @param HouseTypeVal
     * @param BuildingsTypeVal
     * @param DecorationConditionVal
     * @param AreaMax
     * @param AreaMin
     * @param StoreyMin
     * @param StoreyMax
     * @param CreateYearMin
     * @param CreateYearMax
     * @param PriceMin
     * @param PriceMax
     * @param KeyWord
     * @param callback
     */
    public static void CheapRoomList(String PageIndex, String AreaID, String HouseTypeVal,String BuildingsTypeVal,String DecorationConditionVal, String AreaMax,String AreaMin,String StoreyMin,String StoreyMax,String CreateYearMin,String CreateYearMax,String PriceMin,String PriceMax,String KeyWord, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("AreaID", AreaID);
        map.put("HouseTypeVal", HouseTypeVal);
        map.put("BuildingsTypeVal", BuildingsTypeVal);
        map.put("DecorationConditionVal", DecorationConditionVal);
        map.put("AreaMin", AreaMin);
        map.put("AreaMax", AreaMax);
        map.put("StoreyMin", StoreyMin);
        map.put("StoreyMax", StoreyMax);
        map.put("CreateYearMin", CreateYearMin);
        map.put("CreateYearMax", CreateYearMax);
        map.put("PriceMin", PriceMin);
        map.put("PriceMax", PriceMax);
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        map.put("KeyWord", KeyWord);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + CHEAPROOMLIST, json, callback);
    }

    /**
     * 新房区域列表
     *
     * @param cityid
     * @param callback
     */
    public static void AreaList(String cityid, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("cityid", cityid);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + AreaList, json, callback);
    }

    /**
     * 房源详情
     *
     * @param BuildingID
     * @param UserID
     * @param callback
     */
    public static void getHouseDetail(String BuildingID, String UserID, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("BuildingID", BuildingID);
        map.put("UserID", UserID);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postNewJson(URL + HOUSEDETAIL, json, callback);
    }

    /**
     * 获取低价房详情的ID
     *
     * @param Id
     * @param callback
     */
    public static void getCheapRoomHouseDetail(String Id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postNewJson(URL + CHEAPROOMHOUSEDETAIL, json, callback);
    }

    /**
     * 报备客户列表
     *
     * @param UserID
     * @param KeyWord
     * @param KeyWord
     * @param PageIndex
     * @param callback
     */
    public static void getCustomersList(String UserID, String KeyWord, String PageIndex, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserID", UserID);
        map.put("KeyWord", KeyWord);
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + CustomersList, json, callback);
    }

    /**
     * 低价房报备列表
     *
     * @param
     * @param PageIndex
     * @param callback
     */
    public static void getCheapCustomersList(String PageIndex, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("KeyWord", "");
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + CheapCustomersList, json, callback);
    }

    /**
     * 添加客户
     *
     * @param UserID
     * @param Name
     * @param CustomerPhoneList 电话集合，用 | 分隔
     * @param Sex               性别 0 男 1 女
     * @param callback
     */
    public static void getInsertNewCustomer(String UserID, String Name, String CustomerPhoneList, String Sex, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserID", UserID);
        map.put("Name", Name);
        map.put("CustomerPhoneList", CustomerPhoneList);
        map.put("Sex", Sex);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + InsertNewCustomer, json, callback);
    }

    /**
     * 修改客户
     * @param CustomerID
     * @param CustomerPhoneList
     * @param callback
     */
    public static void UpdateCustomer(String CustomerID ,String CustomerPhoneList,HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("CustomerID", CustomerID );
        map.put("CustomerPhoneList", CustomerPhoneList);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + UpdateCustomer, json, callback);
    }

    /**
     * 确认报备信息
     *
     * @param BuildingID
     * @param CustomerID
     * @param CustomerPhoneID
     * @param callback
     */
    public static void getInsertRunningsModel(String BuildingID, String CustomerID, String CustomerPhoneID, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("BuildingID", BuildingID);
        map.put("CustomerID", CustomerID);
        map.put("CustomerPhoneID", CustomerPhoneID);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + InsertRunningsModel, json, callback);
    }

    /**
     * 低价房报备信息
     *
     * @param BuildingID
     * @param CustomerID
     * @param CustomerPhoneID
     * @param callback
     */
    public static void getInsertCheapRunningsModel(String BuildingID, String CustomerID, String CustomerPhoneID, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("BuildingID", BuildingID);
        map.put("CustomerID", CustomerID);
        map.put("CustomerPhoneID", CustomerPhoneID);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + CheapInsertRunningsModel, json, callback);
    }

    /**
     * 查看报备订单详情
     *
     * @param Id
     * @param callback
     */
    public static void getReportingOrderDetail(String Id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + REPORTINGORDERDETSIL, json, callback);
    }


    /**
     * 查看低价房订单详情
     *
     * @param Id
     * @param callback
     */
    public static void getCheapReportingOrderDetail(String Id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + CHEAPREPORTINGORDERDETSIL, json, callback);
    }

    /**
     * 查看装修订单详情
     *
     * @param Id
     * @param callback
     */
    public static void getRenOrderDetail(String Id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + RENORDERDETAIL, json, callback);
    }

    /**
     * 查看金融订单详情
     *
     * @param Id
     * @param callback
     */
    public static void getFinancialOrderDetail(String Id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + FINANCIALORDERDETAIL, json, callback);
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
     * 获取低价房的动态
     *
     * @param PageIndex
     * @param PageSize
     * @param LowPriceHousesID
     * @param callback
     */
    public static void getCheapDynamic(String PageIndex, String PageSize, String LowPriceHousesID, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", PageIndex);
        map.put("PageSize", PageSize);
        map.put("LowPriceHousesID", LowPriceHousesID);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + CHEAPDYNAMIC, json, callback);
    }

    /**
     * 上传图片
     *
     * @param GroupID    图片组ID 3
     * @param InterfixID 相关ID 主键
     * @param ImageType  图片类型 参照ImageGroup表 1带看 2确认单 3 备案
     * @param Imagedata  图片字符串 用Base64加密
     * @param callback
     */
    public static void uploadImage(String GroupID, String InterfixID, String ImageType, String Imagedata, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("GroupID", GroupID);
        map.put("InterfixID", InterfixID);
        map.put("ImageType", ImageType);
        map.put("Imagedata", Imagedata);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", GroupID + "/" + InterfixID + "/" + ImageType);
        new HttpUtils().postNewJson(URL + UploadImage, json, callback);
    }


    /**
     * 删除图片
     *
     * @param Id       图片id
     * @param callback
     */
    public static void DelImage(String Id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + DelImage, json, callback);
    }

    /**
     * 报备订单列表
     *
     * @param PageIndex
     * @param callback
     */
    public static void RunningsList(String PageIndex, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        map.put("KeyWord", "");
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + RunningsList, json, callback);
    }

    /**
     * 低价房报备订单列表
     *
     * @param PageIndex
     * @param callback
     */
    public static void Cheap_RunningsList(String PageIndex, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        map.put("KeyWord", "");
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + RunningsList, json, callback);
    }

    /**
     * 获取装修列表
     *
     * @param PageIndex
     * @param callback
     */
    public static void getRenovationList(String PageIndex, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "20");
        map.put("KeyWord", "");
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + RENOVATIONLIST, json, callback);
    }

    /**
     * 装修列表详情
     *
     * @param Id
     * @param callback
     */
    public static void getRenovationListDetail(String Id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + RENOVATIONLISTDETAIL, json, callback);
    }

    /**
     * 报备信息接口
     *
     * @param DecorationCompanyID
     * @param CustomerID
     * @param CustomerPhoneID
     * @param CustomerAddress
     * @param Area
     * @param Remark
     * @param callback
     */
    public static void getDecorationPreparation(String DecorationCompanyID, String CustomerID, String CustomerPhoneID, String CustomerAddress, String Area, String Remark, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("DecorationCompanyID", DecorationCompanyID);
        map.put("CustomerID", CustomerID);
        map.put("CustomerPhoneID", CustomerPhoneID);
        map.put("CustomerAddress", CustomerAddress);
        map.put("Area", Area);
        map.put("Remark", Remark);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + DecorationPreparation, json, callback);
    }

    /**
     * 获取装修报备列表
     *
     * @param PageIndex
     * @param KeyWord
     * @param callback
     */
    public static void getDecorationPreparationOrder(String PageIndex, String KeyWord, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        map.put("KeyWord", KeyWord);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + DecorationPreparationOrder, json, callback);
    }

    /**
     * 获取装修条件筛选列表
     *
     * @param callback
     */
    public static void getRenSelectList(HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + REN_SELECTLIST, json, callback);
    }

    /**
     * 获取装修列表
     *
     * @param DecorationCompanyId
     * @param PageIndex
     * @param Style
     * @param HouseType
     * @param AreaTag
     * @param callback
     */
    public static void getRenList(String DecorationCompanyId, String PageIndex, String Style, String HouseType, String AreaTag, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("DecorationCompanyId", DecorationCompanyId);
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        map.put("Style", Style);
        map.put("HouseType", HouseType);
        map.put("AreaTag", AreaTag);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + REN_LIST, json, callback);
    }

    /**
     * 装修列表详情
     *
     * @param Id
     * @param callback
     */
    public static void getRenListDetail(String Id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + REN_LISTDETAIL, json, callback);
    }

    /**
     * 获取金融
     *
     * @param
     * @param ParentId
     * @param callback
     */
    public static void getFinancial(String ParentId, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("ParentId", ParentId);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + FINANCIAL, json, callback);
    }

    /**
     * 金融报备
     *
     * @param RunningId
     * @param PhoneId
     * @param callback
     */
    public static void getFinacialReportingOrderDetail(String RunningId, String PhoneId, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("RunningId", RunningId);
        map.put("PhoneId", PhoneId);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + FINANCIALREPORTINGORDERDETSIL, json, callback);
    }

    /**
     * 金融订单列表
     *
     * @param PageIndex
     * @param
     * @param callback
     */
    public static void getFinacialRunningList(String PageIndex, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", PageIndex);
        map.put("PageSize", "10");
        map.put("KeyWord", "");
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + FINANCIALRunningsList, json, callback);
    }

    /**
     * 获取城市列表
     *
     * @param callback
     */
    public static void getCityList(HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + CITYLIST, json, callback);
    }

    /**
     * 选择城市列表
     *
     * @param callback
     */
    public static void getChoseCityList(String CityID, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("CityID", CityID);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + CHOSECITYLIST, json, callback);
    }

    /**
     * 获取验证码
     *
     * @param callback
     */
    public static void getCode(HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + UPDATECODE, json, callback);
    }

    /**
     * 修改密码
     *
     * @param SMSCode
     * @param PassWord
     * @param callback
     */
    public static void updatePwd(String SMSCode, String PassWord, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("SMSCode", SMSCode);
        map.put("PassWord", PassWord);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + UPDATEPWD, json, callback);
    }

    /**
     * 职员列表
     *
     * @param id
     * @param callback
     */
    public static void clerk(String id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postNewJson(URL + CLERK, json, callback);
    }

    /**
     * 密码找回
     *
     * @param
     * @param
     * @param callback
     */
    public static void findpwdcode(String LoginName, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("LoginName", LoginName);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + FINDPWDCODE, json, callback);
    }

    /**
     * 密码重置
     *
     * @param LoginName
     * @param SMSCode
     * @param PassWord
     * @param callback
     */
    public static void findpwd(String LoginName, String SMSCode, String PassWord, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("LoginName", LoginName);
        map.put("SMSCode", SMSCode);
        map.put("PassWord", PassWord);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + FINDPWD, json, callback);
    }

    /**
     * 修改经济人的信息
     *
     * @param Name
     * @param Portrait
     * @param Sex
     * @param callback
     */
    public static void updatePerMes(String Name, String Portrait, String Sex, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Declaration", "");
        map.put("Name", Name);
        map.put("OperationPeriod", "");
        map.put("Portrait", Portrait);
        map.put("Sex", Sex);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + UPLOADPHOTOPIC, json, callback);
    }

    /**
     * 检查版本更新
     *
     * @param callback
     */
    public static void updateVersion(HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postNewJson(URL + CHECKUPDATE, json, callback);
    }

    /**
     * 获取广告Banner
     *
     * @param ModuleID
     * @param callback
     */
    public static void getBanner(String ModuleID, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("ModuleID", ModuleID);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + GETBANNER, json, callback);
    }

    /**
     * 获取广告详情
     * @param Id
     * @param callback
     */
    public static void getBannerDetail(String Id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json", json);
        new HttpUtils().postNewJson(URL + BANNERDETAIL, json, callback);
    }

    /**
     * 获取低价房的筛选条件
     * @param
     * @param callback
     */
    public static void getCheapRoomContent(HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postNewJson(URL + GETCHEAPROOM, json, callback);
    }

}
