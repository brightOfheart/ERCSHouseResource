package ercs.com.ercshouseresources.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/6/30.
 * 其它的工具类
 */

public class OtherUitl {

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    public static void callPage(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * @Descriptionmap 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @author temdy
     * @Date 2015-01-26
     * @param path 图片路径
     * @return
     */
    public static String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        Base64Encoder encoder = new Base64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
}
