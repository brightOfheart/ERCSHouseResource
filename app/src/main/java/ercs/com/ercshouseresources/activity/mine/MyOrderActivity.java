package ercs.com.ercshouseresources.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.MyOrderAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.ServiceBean;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;

/**
 * Created by Administrator on 2017/8/22.
 */

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private SPUtil spUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        ButterKnife.bind(this);
        initTitle();
        initView();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("我的订单");

    }

    /**
     * 初始化
     */
    private void initView() {
        if (spUtil == null)
            spUtil = new SPUtil(MyOrderActivity.this, "fileName");
        String tabs = spUtil.getString(BaseApplication.TABS, "");
        String str[] = tabs.split(",");
        List<ServiceBean> list = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            ServiceBean serviceBean = new ServiceBean();
            if (!str[i].equals(""))
                switch (Integer.valueOf(str[i])) {
                    case 1:
                        serviceBean.setTitle("新房订单");
                        serviceBean.setPic(R.mipmap.xf);
                        list.add(serviceBean);
                        break;
                    case 2:
                        serviceBean.setTitle("二手房订单");
                        serviceBean.setPic(R.mipmap.djf);
                        list.add(serviceBean);

                        break;
                    case 3:
                        serviceBean.setTitle("租房订单");
                        serviceBean.setPic(R.mipmap.esf);
                        list.add(serviceBean);
                        break;
                    case 4:
                        serviceBean.setTitle("低价房订单");
                        serviceBean.setPic(R.mipmap.zf);
                        list.add(serviceBean);
                        break;
                    case 5:
                        serviceBean.setTitle("装修订单");
                        serviceBean.setPic(R.mipmap.jr);
                        list.add(serviceBean);
                        break;
                    case 6:
                        serviceBean.setTitle("活动订单");
                        serviceBean.setPic(R.mipmap.zx);
                        list.add(serviceBean);
                        break;
                    case 7:
                        serviceBean.setTitle("金融订单");
                        serviceBean.setPic(R.mipmap.jz);
                        list.add(serviceBean);
                        break;
                    case 8:
                        serviceBean.setTitle("家政订单");
                        serviceBean.setPic(R.mipmap.hd);
                        list.add(serviceBean);
                        break;
                    case 9:
                        serviceBean.setTitle("旅游订单");
                        serviceBean.setPic(R.mipmap.ly);
                        list.add(serviceBean);
                        break;

                    default:
                        break;

                }
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(new MyOrderAdapter(MyOrderActivity.this, this, list, str));
            recyleview.setLayoutManager(new LinearLayoutManager(this));
            recyleview.setAdapter(mLRecyclerViewAdapter);
            recyleview.setPullRefreshEnabled(false);

        }
    }

}
