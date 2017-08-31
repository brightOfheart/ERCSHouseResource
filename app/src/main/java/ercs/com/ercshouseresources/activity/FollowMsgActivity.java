package ercs.com.ercshouseresources.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.clerk.ClerkActivity;
import ercs.com.ercshouseresources.activity.service.DynamicDetailActivity;
import ercs.com.ercshouseresources.adapter.CheapFollowMsgAdapter;
import ercs.com.ercshouseresources.adapter.ClerkAdapter;
import ercs.com.ercshouseresources.adapter.FinancialFollowMsgAdapter;
import ercs.com.ercshouseresources.adapter.Financial_ReportingClientsAdapter;
import ercs.com.ercshouseresources.adapter.FollowMsgAdapter;
import ercs.com.ercshouseresources.adapter.RenFollowMsgAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.CheapReportOrderDetailBean;
import ercs.com.ercshouseresources.bean.ClerkBean;
import ercs.com.ercshouseresources.bean.FinancialOrderDetailBean;
import ercs.com.ercshouseresources.bean.RenReportOrderDetailBean;
import ercs.com.ercshouseresources.bean.ReportOrderDetailBean;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/30.
 * 跟进信息
 */

public class FollowMsgActivity extends BaseActivity {
    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String json, String kind) {
        Intent intent = new Intent(mactivity, FollowMsgActivity.class);
        intent.putExtra("json", json);
        intent.putExtra("kind", kind);
        mactivity.startActivity(intent);
    }

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private ReportOrderDetailBean reportOrderDetailBean;
    private CheapReportOrderDetailBean cheap_reportOrderDetailBean;
    private FinancialOrderDetailBean financial_reportOrderDetailBean;
    private RenReportOrderDetailBean ren_reportOrderDetailBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followmsg);
        ButterKnife.bind(this);
        initTitle();
        initView();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("跟进信息");
    }

    /**
     * 初始化
     */
    private void initView() {
        if (getKind().equals("1"))//新房
        {
            reportOrderDetailBean = MyGson.getInstance().fromJson(getJson(), ReportOrderDetailBean.class);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(new FollowMsgAdapter(FollowMsgActivity.this, this, reportOrderDetailBean.getData().getMessageBoardsShowList()));
        } else if (getKind().equals("2"))//低价房
        {
            cheap_reportOrderDetailBean = MyGson.getInstance().fromJson(getJson(), CheapReportOrderDetailBean.class);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(new CheapFollowMsgAdapter(FollowMsgActivity.this, this, cheap_reportOrderDetailBean.getData().getMessageBoardsShowList()));

        } else if (getKind().equals("3"))//金融
        {
            financial_reportOrderDetailBean = MyGson.getInstance().fromJson(getJson(), FinancialOrderDetailBean.class);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(new FinancialFollowMsgAdapter(FollowMsgActivity.this, this, financial_reportOrderDetailBean.getData().getMessageBoardsShowList()));
        } else if (getKind().equals("4"))//装修
        {
            ren_reportOrderDetailBean = MyGson.getInstance().fromJson(getJson(), RenReportOrderDetailBean.class);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(new RenFollowMsgAdapter(FollowMsgActivity.this, this, ren_reportOrderDetailBean.getData().getMessageBoardsShowList()));
        }

        recyleview.setLayoutManager(new LinearLayoutManager(this));
        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setPullRefreshEnabled(false);
    }

    private String getJson() {
        return getIntent().getStringExtra("json");
    }

    private String getKind() {
        return getIntent().getStringExtra("kind");
    }
}
