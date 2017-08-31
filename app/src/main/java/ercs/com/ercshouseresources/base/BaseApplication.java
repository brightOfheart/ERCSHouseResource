package ercs.com.ercshouseresources.base;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.StrictMode;

import com.baidu.mapapi.SDKInitializer;

import java.util.ArrayList;
import java.util.List;

import ercs.com.ercshouseresources.bean.AreaBean;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.receiver.CheckReceiver;
import ercs.com.ercshouseresources.service.LocationService;

/**
 * Created by Administrator on 2017/4/21.
 * 整个项目的通用的Application
 */

public class BaseApplication extends Application {
    public static final String ISSHOWPWD = "IsShowpwd";//是否显示密码
    public static final String ISLOGIN = "IsLogin";//是否自动登录
    public static final String ID = "id";//本地保存的账号
    public static final String PHONE = "phone";//本地保存的手机账号
    public static final String PWD = "pwd";//本地保存的密码
    public static final String NAME = "name";//本地保存登录人的姓名
    public static final String PHOTOPATH = "photopath";//本地保存登录人的头像
    public static final String DEPNAME = "depname";//本地保存登录人的职称
    public static final String VERSIONCODE = "versioncode";//本地保存apk版本号
    public static final String UPDATEURL = "updateurl";//apk更新地址
    public static final String AUTHORITY = "Authority";//本地保存登录人的权限 1 2显示 其它隐藏
    public static final String COMPANY = "Company";//本地保存登录人的公司
    public static final String SUPERIORPHONE = "SuperiorPhone";//管理员电话
    public static final String LOGINJSON = "loginjson";//存储登录后的json
    public static final String CITY = "city";//存储登录后的城市
    public static final String CITYID = "cityid";//存储登录后的城市的id
    public static final String TABS = "tabs";//存储登录后的城市服务
    public static final String TOKEN = "token";//存储登录后的Token
    public static String Token = "";//登录接口获取 除了登录其它接口需要添加的Token
    public static String NewToken = "";//新房登录接口获取 除了登录其它接口需要添加的Token
    public static int NewIsLogin = 0;//新房是否登录成功 默认0未成功
    public LocationService locationService;//百度定位服务类
    public static Context context;//定义一个静态的全局变量
    public static final String FILENAME = "fileName";//保存的名称
    public static LoginBean loginBean;//定义一个静态的新房登录全局变量
    public static List<AreaBean.DataBean> areas;//区域信息

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        locationService = new LocationService(getApplicationContext());
        /**
         * 初始化百度地图
         */
        SDKInitializer.initialize(getApplicationContext());

        areas = new ArrayList<>();
        /**
         * 注册广播
         */
        IntentFilter filter = new IntentFilter();
        filter.addAction("401");
        BaseApplication.context.registerReceiver(new CheckReceiver(), filter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }


}
