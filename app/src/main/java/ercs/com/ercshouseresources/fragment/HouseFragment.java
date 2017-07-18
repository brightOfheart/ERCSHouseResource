package ercs.com.ercshouseresources.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import ercs.com.ercshouseresources.activity.housing.HouseListDetail;
import ercs.com.ercshouseresources.adapter.HouseAdapter;
import ercs.com.ercshouseresources.bean.HouseListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.lazyviewpager.LazyFragmentPagerAdapter;

/**
 * Created by Administrator on 2017/7/12.
 * 房源
 */

public class HouseFragment extends Fragment implements LazyFragmentPagerAdapter.Laziable {
    @BindView(R.id.edit_content)
    EditText edit_content;//搜索框
    @BindView(R.id.recyleview)
    LRecyclerView mRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house, container, false);
        ButterKnife.bind(this, view);
        initview();
        getData();
        return view;

    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.ly_area, R.id.ly_price, R.id.ly_housetype, R.id.ly_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_area://区域
                startActivity(new Intent(getContext(),HouseListDetail.class));
                break;
            case R.id.ly_price://价格

                break;
            case R.id.ly_housetype://房型

                break;
            case R.id.ly_more://更多

                break;
        }
    }

    /**
     * 获取网络数据
     */
    private void getData() {
        NetHelper.getHouseList("4", "1", "3", "2018-01-01", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final HouseListBean houseListBean = MyGson.getInstance().fromJson(data, HouseListBean.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getContext(), houseListBean.getContent());

                    }
                });

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
            }
        });
    }

    /**
     * 初始化
     */
    private void initview() {
        lRecyclerViewAdapter = new LRecyclerViewAdapter(new HouseAdapter(getContext(), getdata()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        // mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);  //设置下拉刷新箭头
        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
//设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                mRecyclerView.refreshComplete(0);
                            }
                        });
                    }
                }, 2000);
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {//加载更多
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                mRecyclerView.refreshComplete(0);
                            }
                        });
                    }
                }, 2000);
            }
        });
    }

    private List<String> getdata() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        return list;
    }

}
