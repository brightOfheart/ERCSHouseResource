package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.attendance.AtendanceActivity;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.view.CustomBanner;
import ercs.com.ercshouseresources.view.ObservableScrollView;
import ercs.com.ercshouseresources.view.item.NewHouseItem;

/**
 * Created by Administrator on 2017/7/24.
 * 新房详情页
 */

public class NewHouseDetailActivity extends BaseActivity implements ObservableScrollView.ScrollViewListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_subTitle)
    TextView tv_subTitle;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    @BindView(R.id.banner)
    XBanner xBanner;
    @BindView(R.id.ly_newhouse)
    LinearLayout ly_newhouse;


    public static void start(Activity mactivity, String BuildingID, String UserID) {
        Intent intent = new Intent(mactivity, NewHouseDetailActivity.class);
        intent.putExtra("BuildingID", BuildingID);
        intent.putExtra("UserID", UserID);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newhousedetailactivity);
        ButterKnife.bind(this);
        setbanner();
        initview();
        getData();
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.iv_left, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left://返回点击事件

                break;
            case R.id.iv_right://标题栏顶部右侧点击事件

                break;

        }
    }

    /**
     * 设置首页广告页
     */
    private void setbanner() {
        final List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        new CustomBanner(this, xBanner, imgesUrl);

    }

    /**
     * 初始化
     */
    private void initview() {

        for (int i = 0; i < 5; i++) {
            ly_newhouse.addView(new NewHouseItem(this));
        }

        scrollview.setScrollViewListener(NewHouseDetailActivity.this);
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {
            tv_title.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
        } else if (y > 0 && y <= 200) {
            float scale = (float) y / 200;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            tv_title.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
        } else {
            tv_title.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
        }
    }

    /**
     * 获取网络数据
     */
    private void getData() {
        NetHelperNew.getHouseDetail(getBuildingID(), getUserID(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
            }
        });
    }

    private String getBuildingID() {
        return getIntent().getStringExtra("BuildingID");
    }

    private String getUserID() {
        return getIntent().getStringExtra("UserID");
    }
}
