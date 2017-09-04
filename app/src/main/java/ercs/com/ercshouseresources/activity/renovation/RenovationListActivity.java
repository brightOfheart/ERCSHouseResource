package ercs.com.ercshouseresources.activity.renovation;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.dalong.marqueeview.MarqueeView;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.RenovationListAdapter;
import ercs.com.ercshouseresources.bean.RenovationListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/8/9.
 * 装修列表
 */

public class RenovationListActivity extends BaseActivity {
    @BindView(R.id.tv_marquee)
    MarqueeView marqueeView;
    @BindView(R.id.recyleview)
    LRecyclerView recyleview;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private LoadingDialog dialog;
    private int PageIndex = 1;
    private List<RenovationListBean.DataBean> list;
    private RenovationListAdapter renovationListAdapter;
    private boolean b=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renovationlist);
        ButterKnife.bind(this);
        initTitle();
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("装修");
        dialog = new LoadingDialog(RenovationListActivity.this, 0);
    }

    /**
     * 开启跑马灯效果
     */
    private void startMarquee(List<RenovationListBean.DataBean.AdListBean> AdList) {
        String str = "";
        for (int i = 0; i < AdList.size(); i++) {
            str += AdList.get(i).getText() + "   ";
        }
        marqueeView.setFocusable(true);
        marqueeView.requestFocus();
        marqueeView.setText(str);//设置文本
        marqueeView.startScroll(); //开始
        b=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(b)
        marqueeView.startScroll(); //开始

    }

    /**
     * 初始化
     */
    private void initView() {
        renovationListAdapter=new RenovationListAdapter(RenovationListActivity.this, this, list);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(renovationListAdapter);
        recyleview.setLayoutManager(new LinearLayoutManager(this));
        //设置头部加载颜色
        recyleview.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
//设置底部加载颜色
        recyleview.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
        recyleview.setAdapter(mLRecyclerViewAdapter);
        recyleview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                PageIndex++;
                NetHelperNew.getRenovationList(PageIndex + "", new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        final RenovationListBean renovationListBean = MyGson.getInstance().fromJson(data, RenovationListBean.class);
                        if (renovationListBean.getType().equals("1")) {
                            list.addAll(renovationListBean.getData());
                            renovationListAdapter.setListData(list);
                            mLRecyclerViewAdapter.notifyDataSetChanged();

                        }
                        recyleview.refreshComplete(10);
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        recyleview.refreshComplete(10);
                        ToastUtil.showToast(getApplicationContext(), msg);
                    }
                });
            }
        });
        recyleview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                PageIndex = 1;
                NetHelperNew.getRenovationList(PageIndex + "", new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        final RenovationListBean renovationListBean = MyGson.getInstance().fromJson(data, RenovationListBean.class);
                        recyleview.refreshComplete(10);
                        if (renovationListBean.getType().equals("1")) {
                            list.clear();
                            list = renovationListBean.getData();
                            renovationListAdapter.setListData(list);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        recyleview.refreshComplete(10);
                        ToastUtil.showToast(getApplicationContext(), msg);
                    }
                });

            }
        });

    }

    /**
     * 加载网络数据
     */
    private void loadData() {
        dialog.show();
        NetHelperNew.getRenovationList(PageIndex + "", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final RenovationListBean renovationListBean = MyGson.getInstance().fromJson(data, RenovationListBean.class);
                if (renovationListBean.getType().equals("1")) {
                    list = renovationListBean.getData();
                    initView();
                    if (renovationListBean.getData().size() > 0)
                        startMarquee(renovationListBean.getData().get(0).getAdList());
                }
                dialog.dismiss();
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });
    }


}
