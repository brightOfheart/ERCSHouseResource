package ercs.com.ercshouseresources.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.SPUtil;

/**
 * Created by Administrator on 2017/6/21.
 * 所有Activity的父类
 */

public class BaseActivity extends AppCompatActivity {
    private SPUtil spUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }
}