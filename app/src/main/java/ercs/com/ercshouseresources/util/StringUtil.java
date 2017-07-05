package ercs.com.ercshouseresources.util;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 * String的工具类
 */

public class StringUtil {
    public static boolean CheckString(String s) {
        return (s == null) || (s.trim().length() == 0);
    }

    public static String ListToString(List<String> list){
        if (null == list){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int n = list.size();
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                sb.append(list.get(i) + ",");
            }else {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }
}
