package ercs.com.ercshouseresources.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maning.updatelibrary.InstallUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.renovation.Ren_PrepareInformationActivity;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;

import static ercs.com.ercshouseresources.base.BaseApplication.context;

/**
 * Created by Administrator on 2017/9/1.
 */

public class UpdateActivity extends BaseActivity {
    @BindView(R.id.btn_upload)
    Button btn_upload;
    @BindView(R.id.tv_progress)
    TextView tv_progress;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    //最新APK的下载地址
//    public static final String APK_URL = "http://200.fang101.com/Downloads/app-newhouse_1_9_965.apk";
    //下载后的APK的命名
    public static final String APK_NAME = "update";
    private static final String TAG = "InstallUtils";

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String APK_URL) {
        Intent intent = new Intent(mactivity, UpdateActivity.class);
        intent.putExtra("APK_URL", APK_URL);
        mactivity.startActivity(intent);
    }

    private String getAPK_URL() {

        return getIntent().getStringExtra("APK_URL");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);
        initTitle();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetWorkUtil.check(getApplicationContext()))
                    createview();

            }
        });

    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("下载新版云房源");

    }

    private void createview() {

        new InstallUtils(UpdateActivity.this, getAPK_URL(), APK_NAME, new InstallUtils.DownloadCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "InstallUtils---onStart");
                tv_progress.setText("0%");
            }

            @Override
            public void onComplete(String path) {
                Log.i(TAG, "InstallUtils---onComplete:" + path);

                /**
                 * 安装APK工具类
                 * @param context       上下文
                 * @param filePath      文件路径
                 * @param authorities   ---------Manifest中配置provider的authorities字段---------
                 * @param callBack      安装界面成功调起的回调
                 */
                InstallUtils.installAPK(UpdateActivity.this, path, getPackageName() + ".fileProvider", new InstallUtils.InstallCallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(UpdateActivity.this, "正在安装程序", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(UpdateActivity.this, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                tv_progress.setText("100%");
            }

            @Override
            public void onLoading(long total, long current) {
                Log.i(TAG, "InstallUtils----onLoading:-----total:" + total + ",current:" + current);
                tv_progress.setText((int) (current * 100 / total) + "%");
                progressbar.setProgress((int) (current * 100 / total));
            }

            @Override
            public void onFail(Exception e) {
                Log.i(TAG, "InstallUtils---onFail:" + e.getMessage());
            }

        }).downloadAPK();
    }
}
