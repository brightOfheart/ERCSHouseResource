package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.CommissionDeservedAdapter;
import ercs.com.ercshouseresources.adapter.CommissionExplainAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.CommissionExplainBean;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.NoScrollListView;

/**
 * 佣金说明
 */
public class CommissionExplainActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    NoScrollListView listView;//佣金更改记录
    @BindView(R.id.listview_deserved)
    NoScrollListView listview_deserved;//应得佣金列表

    //带看奖励
    @BindView(R.id.tv_bandsawbrokerage)
    TextView tv_bandsawbrokerage;
    @BindView(R.id.tv_salesmaninformation)
    TextView tv_salesmaninformation;//业务员
    private CommissionExplainAdapter commissionExplainAdapter;//更改记录列表
    private List<CommissionExplainBean.DataBean.ModifyBuildingBrokerageLogListBean> recordlList;//更改记录列表数据

    private CommissionDeservedAdapter commissionDeservedAdapter;//应得佣金列表
    private List<CommissionExplainBean.DataBean.BuildingsBrokerageGroupListBean> deservedList;//应得佣金列表数据

    private CommissionExplainBean commissionExplainBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_explain);
        ButterKnife.bind(this);
        initTitle();
        commissionExplainBean = MyGson.getInstance().fromJson(getjsonString(), CommissionExplainBean.class);
        initListview();
        initdeserved();

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        //更改记录

        recordlList.addAll(commissionExplainBean.getData().getModifyBuildingBrokerageLogList());
        commissionExplainAdapter.notifyDataSetChanged();

        //应得佣金列表
        deservedList.addAll(commissionExplainBean.getData().getBuildingsBrokerageGroupList());
        commissionDeservedAdapter.notifyDataSetChanged();


        tv_bandsawbrokerage.setText(commissionExplainBean.getData().getBaseInfo().getBandSawBrokerage());
    }


    /**
     * 页面跳转
     */
    public static void start(Activity mactivity,String jsonString)
    {
        Log.i("-->","佣金说明获取数据："+jsonString);
        Intent intent = new Intent(mactivity, CommissionExplainActivity.class);
        intent.putExtra("jsonString",jsonString);
        mactivity.startActivity(intent);
    }

    /**
     * 获取json数据
     * @return
     */
    private String getjsonString()
    {

        return getIntent().getStringExtra("jsonString");
    }
    /**
     * 应得佣金列表
     */
    private void initdeserved() {
        deservedList=new ArrayList<>();
        commissionDeservedAdapter=new CommissionDeservedAdapter(this,deservedList,commissionExplainBean.getData().getBaseInfo().getCommissionAccount());
        listview_deserved.setAdapter(commissionDeservedAdapter);
    }

    /**
     * 更改记录列表
     */
    private void initListview() {
        recordlList=new ArrayList<>();

        commissionExplainAdapter=new CommissionExplainAdapter(this,recordlList);
        listView.setAdapter(commissionExplainAdapter);
    }

    private void initTitle() {
        TitleControl titleControl = new TitleControl(this);
        titleControl.setTitle(getString(R.string.str_commission));
        tv_salesmaninformation.setText("咨询业务员 "+BaseApplication.loginBean.getData().getStaffList().get(0).getName()+" "+BaseApplication.loginBean.getData().getStaffList().get(0).getPhone());
    }
    @OnClick({R.id.rl_bottom})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_bottom:
                OtherUitl.callPage(CommissionExplainActivity.this, BaseApplication.loginBean.getData().getStaffList().get(0).getPhone());
                break;
        }
    }
}
