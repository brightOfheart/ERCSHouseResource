package ercs.com.ercshouseresources.activity.service;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.NewHouseAdapter;
import ercs.com.ercshouseresources.adapter.ReportingClientsAdapter;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * 报备客户
 */
public class ReportingClientsActivity extends AppCompatActivity {

    @BindView(R.id.edit_content)
    EditText edit_content;//搜索框
    @BindView(R.id.recyleview)
    LRecyclerView mRecyclerView ;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<String> clientListBeans;
    private ReportingClientsAdapter reportingClientsAdapter;//客户列表
    private LoadingDialog loadingDialog;
    private  String key="";// 关键字 “”
    private int pagenum=1;//页数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_clients);
        ButterKnife.bind(this);
        initview();
        getData();
    }

    /**
     * 获取网络数据
     */
    private void getData() {
        for (int i = 0; i < 10; i++) {
            clientListBeans.add("");
            reportingClientsAdapter.notifyDataSetChanged();
        }

    }

    @OnClick({R.id.iv_addperson})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_addperson:
                //添加客户
                startActivity(new Intent(ReportingClientsActivity.this,AddClientActivity.class));
                break;
        }
    }
    private void initview() {
        loadingDialog=new LoadingDialog(this,0);
        clientListBeans=new ArrayList<>();
        reportingClientsAdapter = new ReportingClientsAdapter(this, clientListBeans);

        lRecyclerViewAdapter = new LRecyclerViewAdapter(reportingClientsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        // mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);  //设置下拉刷新箭头
        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
//设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.white);




        mRecyclerView.setLoadMoreEnabled(false);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh() {
                pagenum=1;
                clientListBeans.clear();
                reportingClientsAdapter.notifyDataSetChanged();
//                getData(pagenum);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {//加载更多
            @Override
            public void onLoadMore() {

            }
        });



        //搜索框监听
        edit_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH) {

            /*隐藏软键盘*/
                    InputMethodManager inputMethodManager = (InputMethodManager) ReportingClientsActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(inputMethodManager.isActive()){
                        inputMethodManager.hideSoftInputFromWindow(ReportingClientsActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }
                    key=edit_content.getText().toString();

                    clientListBeans.clear();
                    reportingClientsAdapter.notifyDataSetChanged();
//                    getData(pagenum);
                    return true;
                }
                return false;
            }
        });



    }

}
