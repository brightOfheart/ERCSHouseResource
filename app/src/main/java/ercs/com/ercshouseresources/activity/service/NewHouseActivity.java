package ercs.com.ercshouseresources.activity.service;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
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
import ercs.com.ercshouseresources.adapter.HouseAdapter;
import ercs.com.ercshouseresources.adapter.NewHouseAdapter;
import ercs.com.ercshouseresources.bean.HouseListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * 新房
 */
public class NewHouseActivity extends AppCompatActivity {

    @BindView(R.id.edit_content)
    EditText edit_content;//搜索框
    @BindView(R.id.recyleview)
    LRecyclerView mRecyclerView ;
    @BindView(R.id.view_line)
    View view_line;

    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<String> houseListBeans;
    private NewHouseAdapter houseAdapter;//房源列表
    private LoadingDialog loadingDialog;
    private  String key="";// 关键字 “”
    private int pagenum=1;//页数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_house);
        ButterKnife.bind(this);
        initview();
        getFalseData();
    }

    /**
     * 假数据
     */
    private void getFalseData() {
        for (int i = 0; i < 10; i++) {
            houseListBeans.add("");
            houseAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 初始化
     */
    private void initview() {
        loadingDialog=new LoadingDialog(this,0);
        houseListBeans=new ArrayList<>();
        houseAdapter = new NewHouseAdapter(this, houseListBeans);

        lRecyclerViewAdapter = new LRecyclerViewAdapter(houseAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        // mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);  //设置下拉刷新箭头
        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
//设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.white);

        //分割线
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.d_1)
                .setPadding(R.dimen.d_0)
                .setColorResource(R.color.white2)
                .build();
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh() {
                pagenum=1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {//加载更多
            @Override
            public void onLoadMore() {
                getData(pagenum);
            }
        });



        //搜索框监听
        edit_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH) {

            /*隐藏软键盘*/
                    InputMethodManager inputMethodManager = (InputMethodManager) NewHouseActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(inputMethodManager.isActive()){
                        inputMethodManager.hideSoftInputFromWindow(NewHouseActivity.this.getCurrentFocus().getWindowToken(), 0);
}
key=edit_content.getText().toString();

        houseListBeans.clear();
        houseAdapter.notifyDataSetChanged();
        getData(pagenum);
        return true;
        }
        return false;
        }
        });



        }

@OnClick({R.id.title_left})
public void onClick(View view) {
        switch (view.getId())
        {
        case R.id.title_left:
        //返回键
        finish();
        break;
        }
        }
    /**
     * 获取网络数据
     * @param pageIndex 页数
     */
    private void getData(int pageIndex) {

        if (NetWorkUtil.check(this))
        {
            loadingDialog.show();
            NetHelper.getHouseList("4", pageIndex+"", "10",key,"0","0","0","0","0",0,0,0,0,0+"",0+"","2017-1-1","2018-1-1",0,0, new HttpUtils.HttpCallback() {
                @Override
                public void onSuccess(String data) {
                    loadingDialog.dismiss();
                    final HouseListBean houseListBean = MyGson.getInstance().fromJson(data, HouseListBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showToast(NewHouseActivity.this, houseListBean.getContent());

                            mRecyclerView.refreshComplete(10);
                            //更新数据
//                            houseListBeans.addAll(houseListBean.getData());

                            houseAdapter.notifyDataSetChanged();
                            pagenum++;
                        }
                    });

                }

                @Override
                public void onError(String msg) {
                    super.onError(msg);
                    loadingDialog.dismiss();
                    mRecyclerView.refreshComplete(10);
                }
            });
        }

    }

}
