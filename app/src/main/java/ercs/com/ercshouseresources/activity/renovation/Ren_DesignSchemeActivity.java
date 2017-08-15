package ercs.com.ercshouseresources.activity.renovation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.DesignSchemeAdapter;
import ercs.com.ercshouseresources.bean.RenListBean;
import ercs.com.ercshouseresources.bean.RenSelectListBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.renpop.RenDesignAreaoPop;
import ercs.com.ercshouseresources.view.renpop.RenDesignPop;
import ercs.com.ercshouseresources.view.renpop.RenDesignStylePop;

/**
 * Created by Administrator on 2017/8/11.
 * 设计方案
 */

public class Ren_DesignSchemeActivity extends BaseActivity {
    @BindView(R.id.recyleview)
    LRecyclerView mRecyclerView;
    @BindView(R.id.v_line)
    View v_line;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RenDesignPop renDesignPop = null;
    private RenDesignStylePop renDesignStylePop = null;
    private RenDesignAreaoPop areaoPop = null;
    private LoadingDialog dialog;
    private RenSelectListBean renSelectListBean;
    private String Style = "";
    private String HouseType = "";
    private String AreaTag = "";
    private int PageIndex = 1;
    private RenListBean renListBean;
    private DesignSchemeAdapter designSchemeAdapter;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String DecorationCompanyId) {
        Intent intent = new Intent(mactivity, Ren_DesignSchemeActivity.class);
        intent.putExtra("DecorationCompanyId", DecorationCompanyId);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designscheme);
        ButterKnife.bind(this);
        initTitle();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
        if (NetWorkUtil.check(getApplicationContext()))
            loadData();
        getData();
    }

    /**
     * 加载网络数据
     */
    private void loadData() {

        NetHelperNew.getRenSelectList(new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {

                renSelectListBean = MyGson.getInstance().fromJson(data, RenSelectListBean.class);
                if (renSelectListBean.getType().equals("1")) {
                    setData();
                } else {
                    ToastUtil.showToast(Ren_DesignSchemeActivity.this, renSelectListBean.getContent());
                }

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);

                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });

    }

    private void getData() {
        dialog.show();
        NetHelperNew.getRenList(getStr(), PageIndex + "", Style, HouseType, AreaTag, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                renListBean = MyGson.getInstance().fromJson(data, RenListBean.class);
                if (renListBean.getType().equals("1")) {
                    createview();
                } else {
                    ToastUtil.showToast(Ren_DesignSchemeActivity.this, renSelectListBean.getContent());
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
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("设计方案");
        dialog = new LoadingDialog(Ren_DesignSchemeActivity.this, 0);
    }

    private void setData() {
        renDesignPop = new RenDesignPop(this, new RenDesignPop.OnSelectContentListener() {
            @Override
            public void selectContent(String s) {
                Style = s;
                reshData();
            }
        }, renSelectListBean.getData().getHouseType());

        renDesignStylePop = new RenDesignStylePop(this, new RenDesignStylePop.OnSelectContentListener() {
            @Override
            public void selectContent(String s) {
                HouseType = s;
                reshData();
            }
        }, renSelectListBean.getData().getStyle());
        areaoPop = new RenDesignAreaoPop(this, new RenDesignAreaoPop.OnSelectContentListener() {
            @Override
            public void selectContent(String s) {
                AreaTag = s;
                reshData();
            }
        }, renSelectListBean.getData().getAreaTag());
    }

    @OnClick({R.id.ly_style, R.id.ly_room, R.id.ly_areo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_style://风格

                if (renDesignPop != null)
                    renDesignStylePop.showAsDropDown(v_line, 0, 0);

                break;
            case R.id.ly_room:
                if (renDesignPop != null)
                    renDesignPop.showAsDropDown(v_line, 0, 0);


                break;
            case R.id.ly_areo:

                if (renDesignPop != null)
                    areaoPop.showAsDropDown(v_line, 0, 0);

                break;
        }
    }

    /**
     * 初始化
     */
    private void createview() {
        designSchemeAdapter = new DesignSchemeAdapter(this, renListBean.getData());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(designSchemeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                PageIndex = 1;
                NetHelperNew.getRenList(getStr(), PageIndex + "", Style, HouseType, AreaTag, new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        renListBean = MyGson.getInstance().fromJson(data, RenListBean.class);
                        mRecyclerView.refreshComplete(10);
                        if (renListBean.getType().equals("1")) {
                            designSchemeAdapter.setListData(null);
                            designSchemeAdapter.setListData(renListBean.getData());
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mRecyclerView.refreshComplete(10);
                        ToastUtil.showToast(getApplicationContext(), msg);
                    }
                });
            }
        });
    }

    private String getStr() {

        return getIntent().getStringExtra("DecorationCompanyId");
    }

    private void reshData() {
        dialog.show();
        NetHelperNew.getRenList(getStr(), PageIndex + "", Style, HouseType, AreaTag, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                renListBean = MyGson.getInstance().fromJson(data, RenListBean.class);
                dialog.dismiss();
                if (renListBean.getType().equals("1")) {
                    designSchemeAdapter.setListData(null);
                    designSchemeAdapter.setListData(renListBean.getData());
                    mLRecyclerViewAdapter.notifyDataSetChanged();
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
}
