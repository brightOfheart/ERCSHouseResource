package ercs.com.ercshouseresources.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.clerk.ClerkActivity;
import ercs.com.ercshouseresources.adapter.CityAdapter;
import ercs.com.ercshouseresources.adapter.ClerkAdapter;
import ercs.com.ercshouseresources.bean.CityBean;
import ercs.com.ercshouseresources.bean.ClerkBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/31.
 * 选择城市
 */

public class CityAcvitity extends BaseActivity {
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private LoadingDialog dialog;
    private SPUtil spUtil;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acvitity_city);
        ButterKnife.bind(this);
        initTitle();
        createDialog();
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("城市");
        if (spUtil == null)
            spUtil = new SPUtil(this, "fileName");
    }

    /**
     * 创建加载对话框
     */
    private void createDialog() {
        dialog = new LoadingDialog(CityAcvitity.this, 0);
    }

    /**
     * 加载网络数据
     */
    private void loadData() {
        dialog.show();
        NetHelperNew.getCityList(new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final CityBean cityBean = MyGson.getInstance().fromJson(data, CityBean.class);
                if (cityBean.getType().equals("1")) {
                    initView(cityBean.getData());
                } else {
                    ToastUtil.showToast(getApplicationContext(), cityBean.getContent());
                }

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });
    }

    /**
     * 初始化
     */
    private void initView(List<CityBean.DataBean> datas) {
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(new CityAdapter(CityAcvitity.this, this, datas));
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setPullRefreshEnabled(false);
    }
}
