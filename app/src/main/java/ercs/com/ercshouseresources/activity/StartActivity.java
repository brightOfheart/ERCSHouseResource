package ercs.com.ercshouseresources.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ercs.com.ercshouseresources.R;

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
