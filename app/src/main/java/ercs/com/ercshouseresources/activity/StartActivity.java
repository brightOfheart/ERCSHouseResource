package ercs.com.ercshouseresources.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.RadioGroup;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.AreaBean;
import ercs.com.ercshouseresources.bean.HouseListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.ToastUtil;

/**
 * Created by Administrator on 2017/6/21.
 * 启动页
 */

public class StartActivity extends BaseActivity {
    private int delayMills = 2000;//延迟跳转的时间

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startacvity();
        downloadAreaList();
    }

    /**
     * 下载区域信息
     */
    private void downloadAreaList() {

        NetHelper.AreaList(new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                AreaBean areaBean = MyGson.getInstance().fromJson(data, AreaBean.class);
                BaseApplication.areas.addAll(areaBean.getData());

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                Log.i("-->","下载区域信息失败"+msg);

            }
        });
    }

    /**
     * 跳转页面
     */
    private void startacvity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, delayMills);

    }


}
