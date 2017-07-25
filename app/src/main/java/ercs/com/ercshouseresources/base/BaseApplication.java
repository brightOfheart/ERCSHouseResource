package ercs.com.ercshouseresources.base;

import android.app.Application;
import android.content.Context;
import com.baidu.mapapi.SDKInitializer;

import java.util.ArrayList;
import java.util.List;

import ercs.com.ercshouseresources.bean.AreaBean;
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
    public static final String AUTHORITY = "Authority";//本地保存登录人的权限 1 2显示 其它隐藏
    public static final String SUPERIORPHONE = "SuperiorPhone";//管理员电话
    public static String Token = "";//登录接口获取 除了登录其它接口需要添加的Token
    public static String NewToken = "";//新房登录接口获取 除了登录其它接口需要添加的Token
    public static int NewIsLogin = 0;//新房是否登录成功 默认0未成功
    public LocationService locationService;//百度定位服务类
    public static Context context;//定义一个静态的全局变量
    public static final String FILENAME = "fileName";//保存的名称

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

        areas=new ArrayList<>();
    }


}
