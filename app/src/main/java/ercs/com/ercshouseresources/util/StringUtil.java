package ercs.com.ercshouseresources.util;
import static ercs.com.ercshouseresources.base.BaseApplication.context;
/**
 * Created by Administrator on 2017/5/9.
 * String的工具类
 */

public class StringUtil {
    /**
     * 获取返回的String 对象
     *
     * @param id
     * @return
     */
    public static String getStr(int id) {
        return context.getResources().getString(id);
    }
}
