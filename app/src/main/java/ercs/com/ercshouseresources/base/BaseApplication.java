package ercs.com.ercshouseresources.base;
import android.app.Application;
import com.baidu.mapapi.SDKInitializer;
import ercs.com.ercshouseresources.service.LocationService;

/**
 * Created by Administrator on 2017/4/21.
 * 整个项目的通用的Application
 */

public class BaseApplication extends Application {
    public static final String ISSHOWPWD="IsShowpwd";//是否显示密码
    public static final String ISLOGIN="IsLogin";//是否自动登录
    public static final String ID="id";//本地保存的账号
    public static final String PWD="pwd";//本地保存的密码
    public static final String NAME="name";//本地保存登录人的姓名
    public static final String PHOTOPATH="photopath";//本地保存登录人的头像
    public static final String DEPNAME="depname";//本地保存登录人的职称
    public static final String AUTHORITY="Authority";//本地保存登录人的权限
    public static String Token="";
    public LocationService locationService;
    private String string="000";
    @Override
    public void onCreate() {
        super.onCreate();
        locationService = new LocationService(getApplicationContext());
        /**
         * 初始化百度地图
         */
        SDKInitializer.initialize(getApplicationContext());
    }


}
