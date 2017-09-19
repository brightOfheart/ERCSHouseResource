package ercs.com.ercshouseresources.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.DynamicDetailAdapter;
import ercs.com.ercshouseresources.bean.GDynamicBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.NoScrollListView;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/9/13.
 */

public class GDynamicDetailActivity extends BaseActivity {
    @BindView(R.id.listview)
    NoScrollListView listview;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_subtitle)
    TextView tv_subtitle;
    private LoadingDialog loadingDialog;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String Id) {
        Intent intent = new Intent(mactivity, GDynamicDetailActivity.class);
        intent.putExtra("Id", Id);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamicdetail);
        ButterKnife.bind(this);
        initTitle();
        createData();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
        if (NetWorkUtil.check(getApplicationContext())) {
            loadData();
        }
    }

    private void loadData() {
        loadingDialog.show();
        NetHelperNew.getBannerDetail(getId(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                loadingDialog.dismiss();
                final GDynamicBean gDynamicBean = MyGson.getInstance().fromJson(data, GDynamicBean.class);
                if (gDynamicBean.getType().equals("1")) {
                    tv_title.setText(gDynamicBean.getData().getTitle());
                    tv_subtitle.setText(gDynamicBean.getData().getSubject());
                    listview.setAdapter(new DynamicDetailAdapter(GDynamicDetailActivity.this, gDynamicBean.getData().getImagePathList()));
                } else {
                    ToastUtil.showToast(GDynamicDetailActivity.this, gDynamicBean.getContent());
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                loadingDialog.dismiss();
                ToastUtil.showToast(GDynamicDetailActivity.this, msg);
            }
        });
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("广告动态详情");
        loadingDialog = new LoadingDialog(this, 0);
    }

    private void createData() {

    }

    private String getId() {
        return getIntent().getStringExtra("Id");
    }
    private String getSubTitles() {
        return getIntent().getStringExtra("subtitle");
    }

    private ArrayList<String> getPathList() {
        return getIntent().getStringArrayListExtra("path");
    }
}
