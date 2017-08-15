package ercs.com.ercshouseresources.activity.renovation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/11.
 * 公司简介
 */

public class Ren_companyIntroActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String str, String path) {
        Intent intent = new Intent(mactivity, Ren_companyIntroActivity.class);
        intent.putExtra("str", str);
        intent.putExtra("path", path);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyintro);
        ButterKnife.bind(this);
        initTitle();
        createView();
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
    }
    private void createView()
    {
        tv_content.setText(getStr());
        GlideUtil.loadImage(Ren_companyIntroActivity.this, NetHelperNew.URL + getPath(), iv_photo, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("公司简介");

    }

    private String getStr() {

        return getIntent().getStringExtra("str");
    }

    private String getPath() {

        return getIntent().getStringExtra("path");
    }
}
