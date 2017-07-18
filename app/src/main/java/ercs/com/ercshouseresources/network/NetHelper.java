package ercs.com.ercshouseresources.network;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 * 网络接口访问辅助类
 */
public class NetHelper {
    public static final String URL = "http://192.168.1.55:1111";//IP地址
    private static final String LOGIN = "/API/Account/LOGIN";//用户登录
    private static final String CLERK = "/API/User/UserList";//职员列表
    private static final String PROCESS = "/API/Leave/LeaveApprovaList";//流程审批
    private static final String PROCESSCONTENT = "/API/Leave/LeaveInfo";//流程审批详细接口
    private static final String PROCESSREVIEW = "/API/Leave/ApprovalLeave";//流程审批接口
    private static final String SCHEDULING = "/API/PaiBan/OnDutyList";//排班情况接口
    private static final String ATENDANCE = "/API/InSide/UserInSideList";//考勤统计接口
    private static final String OUTSIDE = "/API/OutSide/OutSideList";//外勤统计接口
    private static final String LEAVEALL = "/API/Leave/LeaveAllList";//所有流程接口
    private static final String INSIDE = "/API/InSide/InSideInfo";//打卡信息接口
    private static final String INSIDESETINFO = "/API/InSide/InSideSetInfo";//打卡设置信息接口
    private static final String UPLOADPIC = "/API/OutSide/UploadImageAsync";//上传打开图片
    private static final String PUNCHCLOCK = "/API/InSide/InsertInSide";//打卡接口
    private static final String OUTSIDEPUNCHCLOCK = "/API/OutSide/InsertOutSide";//外勤打卡接口
    private static final String PROCESSAPPLYOUTSIDE = "/API/Leave/InsertLeave";//流程申请 休息、外出
    private static final String PROCESSRETROACTIVE = "/API/Leave/InsertRetroactive";//流程申请 补签 上班 下班
    private static final String ATENDANCEDETAIL = "/API/InSide/InSideInfo";//外勤统计详情接口
    private static final String RECORDETAIL = "/api/Inside/UserInSideInfo";//考勤记录详情接口
    private static final String AREALIST = "/api/HouseSource/AreaList";//房源区域列表接口
    private static final String USERDICTIONARY = "/api/HouseSource/UserDictionary";//房源类型列表接口
    private static final String HOUSESOURCELIST = "/api/HouseSource/HouseSourceList";//房源列表数据接口
    /**
     * 登录
     *
     * @param id       pwd
     * @param callback
     */
    public static void login(String id, String pwd, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("userName", id);
        map.put("password", pwd);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postLoginJson(URL + LOGIN, json, callback);
    }

    /**
     * 职员列表
     *
     * @param id
     * @param callback
     */
    public static void clerk(String id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", id);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + CLERK, json, callback);
    }

    /**
     * 流程审批
     *
     * @param id
     * @param callback
     */
    public static void process(String id, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("ApprovalUserId", id);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + PROCESS, json, callback);
    }

    /**
     * 流程审批详细接口
     *
     * @param id
     * @param LeaveType
     * @param callback
     */
    public static void processContent(String id, String LeaveType, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", id);
        map.put("LeaveType", LeaveType);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + PROCESSCONTENT, json, callback);
    }

    /**
     * 流程审批接口
     *
     * @param id
     * @param LeaveType
     * @param LeaveState
     * @param ApprovalContent
     * @param ApprovalUserId
     * @param callback
     */
    public static void processReview(String id, String LeaveType, String LeaveState, String ApprovalContent, String ApprovalUserId, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", id);
        map.put("LeaveType", LeaveType);
        map.put("LeaveState", LeaveState);//同意2 不同意3
        map.put("ApprovalContent", ApprovalContent);
        map.put("ApprovalUserId", ApprovalUserId);
        String json = MyGson.getInstance().toJson(map);
        Log.i("Json",json);
        new HttpUtils().postJson(URL + PROCESSREVIEW, json, callback);
    }

    /**
     * 排班情况接口
     *
     * @param ParamsDate
     * @param callback
     */
    public static void scheduling(String ParamsDate, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("ParamsDate", ParamsDate);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + SCHEDULING, json, callback);
    }

    /**
     * 考勤统计接口
     *
     * @param UserId
     * @param date
     * @param callback
     */
    public static void atendance(String UserId, String date, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", UserId);
        map.put("ParamsDate", date);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + ATENDANCE, json, callback);
    }

    /**
     * 外勤统计接口
     *
     * @param CreatorId
     * @param ParamsDate
     * @param callback
     */
    public static void outside(String CreatorId, String ParamsDate, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("CreatorId", CreatorId);
        map.put("ParamsDate", ParamsDate);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + OUTSIDE, json, callback);
    }

    /**
     * 所有流程的接口
     *
     * @param UserId
     * @param callback
     */
    public static void leavealllis(String UserId, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", UserId);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + LEAVEALL, json, callback);
    }

    /**
     * 打卡信息接口
     *
     * @param UserId
     * @param callback
     */
    public static void inside(String ParamsDate, String UserId, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("ParamsDate", ParamsDate);
        map.put("UserId", UserId);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + INSIDE, json, callback);
    }

    /**
     * 打卡设置信息
     *
     * @param UserId
     * @param callback
     */
    public static void insideSetInfo(String UserId, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", UserId);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + INSIDESETINFO, json, callback);
    }

    /**
     * 上传打卡图片
     *
     * @param Imagedata
     * @param callback
     */
    public static void uploadPic(String Imagedata, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Imagedata", Imagedata);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + UPLOADPIC, json, callback);
    }

    /**
     * 上班打卡接口
     *
     * @param
     * @param Id
     * @param callback
     */
    public static void punchClock(String StartImagePage, String Id, String CreatorId, String StartLocation, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        map.put("StartImagePage", StartImagePage);
        map.put("CreatorId", CreatorId);
        map.put("StartContent", "1");
        map.put("StartLocation", StartLocation);
        String json = MyGson.getInstance().toJson(map);
        Log.d("Json", json);
        new HttpUtils().postJson(URL + PUNCHCLOCK, json, callback);
    }

    /**
     * 下班打卡接口
     *
     * @param
     * @param Id
     * @param callback
     */
    public static void punchClock2(String EndImagePage, String Id, String CreatorId, String EndLocation, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", Id);
        map.put("EndImagePage", EndImagePage);
        map.put("CreatorId", CreatorId);
        map.put("EndContent", "1");
        map.put("EndLocation", EndLocation);
        String json = MyGson.getInstance().toJson(map);
        Log.d("xia班打卡",json);
        new HttpUtils().postJson(URL + PUNCHCLOCK, json, callback);
    }

    /**
     * 外勤打卡接口
     *
     * @param Location
     * @param ImagePage
     * @param CreatorId
     * @param OutSideContent
     * @param callback
     */
    public static void outsidePunchClock(String Location, String ImagePage, String CreatorId, String OutSideContent, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Location", Location);
        map.put("ImagePage", ImagePage);
        map.put("CreatorId", CreatorId);
        map.put("OutSideContent", OutSideContent);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + OUTSIDEPUNCHCLOCK, json, callback);
    }

    /**
     * 流程审批 休息 外出
     * @param UserId
     * @param StartTime
     * @param EndTime
     * @param TimeCount
     * @param LeaveType
     * @param LeaveState
     * @param LeaveContent
     * @param callback
     */
    public static void processApplyOutside(String UserId, String StartTime, String EndTime, String TimeCount, String LeaveType, String LeaveState,String LeaveContent, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", UserId);
        map.put("StartTime", StartTime);
        map.put("EndTime", EndTime);
        map.put("TimeCount", TimeCount);
        map.put("LeaveType", LeaveType);
        map.put("LeaveState", LeaveState);
        map.put("LeaveContent", LeaveContent );
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + PROCESSAPPLYOUTSIDE, json, callback);
    }

    /**
     * 流程审批 补签：上班、下班
     *
     * @param UserId
     * @param RetroactiveClass
     * @param RetroactiveTime
     * @param RetroactiveState
     * @param ApplicationContent
     * @param ApplicationTime
     * @param callback
     */
    public static void processRetroactive(String UserId, String RetroactiveClass, String RetroactiveTime, String RetroactiveState, String ApplicationContent, String ApplicationTime, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", UserId);
        map.put("RetroactiveClass", RetroactiveClass);
        map.put("RetroactiveTime", RetroactiveTime);
        map.put("RetroactiveState", RetroactiveState);
        map.put("ApplicationContent", ApplicationContent);
        map.put("ApplicationTime", ApplicationTime);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + PROCESSRETROACTIVE, json, callback);
    }

    /**
     * 考勤统计详情
     * @param UserId
     * @param ParamsDate
     * @param callback
     */
    public static void atendanceDetail(String UserId, String ParamsDate, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", UserId);
        map.put("ParamsDate", ParamsDate);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + ATENDANCEDETAIL, json, callback);
    }

    /**
     * 考勤统计详情修改后
     * @param UserId
     * @param AttState
     * @param callback
     */
    public static void RecordDetail(String UserId, String AttState, HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("Id", UserId);
        map.put("AttState", AttState);
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + RECORDETAIL, json, callback);
    }

    /**
     * 房源获取区域列表
     * @param callback
     */
    public static void AreaList(HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + AREALIST, json, callback);
    }

    /**
     * 获取房源类型列表
     * @param callback
     */
    public static void UserDictionary(HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        String json = MyGson.getInstance().toJson(map);
        new HttpUtils().postJson(URL + USERDICTIONARY, json, callback);
    }

    /**
     * 获取房源列表
     * @param UserId
     * @param pageIndex
     * @param pageSize
     * @param endDate
     * @param callback
     */
    public static void getHouseList(String UserId,String pageIndex,String pageSize,String endDate,HttpUtils.HttpCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", UserId);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        map.put("beginDate", "2017-01-01");
        map.put("endDate", endDate);
        String json = MyGson.getInstance().toJson(map);
        Log.d("json",json);
        new HttpUtils().postJson(URL + HOUSESOURCELIST, json, callback);
    }
}
