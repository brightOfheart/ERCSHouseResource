package ercs.com.ercshouseresources.util;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 * 统一管理Activity的类
 */

public class CloseActivityClass  {
    public static List<Activity> activityList = new ArrayList<Activity>();
    public static void exitClient()
    {
        // 关闭所有Activity
        for (int i = 1; i < activityList.size(); i++)
        {
            if (null != activityList.get(i))
            {
                activityList.get(i).finish();
            }
        }

    }
}
