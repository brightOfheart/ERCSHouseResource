package ercs.com.ercshouseresources.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NetWorkUtil {

    /**
     * 判断当前是否有网络
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {
        boolean flag = false;
        ConnectivityManager connectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectManager.getActiveNetworkInfo() != null)
            flag = connectManager.getActiveNetworkInfo().isAvailable();
        return flag;

    }

    /**
     * 登录之前的检测
     *
     * @return
     */
    public static boolean check(Context context) {

        if (!checkNet(context)) {
            ToastUtil.showToast(context, context.getString(R.string.error_net));
            return false;
        }
        return true;
    }

    /**
     * 获取当前的wifi mac地址
     *
     * @param context
     * @return
     */
    public static String getWifiMac(Context context) {
        String netMac = "";
        WifiManager mWifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (mWifi.isWifiEnabled()) {
            WifiInfo wifiInfo = mWifi.getConnectionInfo();
            String netName = wifiInfo.getSSID(); //获取被连接网络的名称
            netMac = wifiInfo.getBSSID(); //获取被连接网络的mac地址
            String localMac = wifiInfo.getMacAddress();// 获得本机的MAC地址
            Log.d("MainActivity", "---netName:" + netName);   //---netName:HUAWEI MediaPad
            Log.d("MainActivity", "---netMac:" + netMac);     //---netMac:78:f5:fd:ae:b9:97
            Log.d("MainActivity", "---localMac:" + localMac); //---localMac:BC:76:70:9F:56:BD
            return netMac;
        }
        return netMac;
    }

}
