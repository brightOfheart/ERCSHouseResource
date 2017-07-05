package ercs.com.ercshouseresources.network;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/3/23.
 */

public class MyGson {
    private static Gson instance = null;

    public static synchronized Gson getInstance() {
        if (instance == null) {
            instance = new Gson();
        }
        return instance;
    }
}
