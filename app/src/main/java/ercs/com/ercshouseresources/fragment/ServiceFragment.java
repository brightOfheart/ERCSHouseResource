package ercs.com.ercshouseresources.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.CityAcvitity;
import ercs.com.ercshouseresources.activity.cheaproom.CheapRoomListActivity;
import ercs.com.ercshouseresources.activity.financial.FinancialActivity;
import ercs.com.ercshouseresources.activity.renovation.RenovationListActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseActivity;
import ercs.com.ercshouseresources.adapter.ServiceAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.ServiceBean;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.lazyviewpager.LazyFragmentPagerAdapter;

/**
 * Created by Administrator on 2017/7/24.
 * 服务
 */

public class ServiceFragment extends Fragment implements LazyFragmentPagerAdapter.Laziable {
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.ly_city)
    LinearLayout ly_city;
    private LoadingDialog dialog;
    private final String[] mIndexItems = {"定位", "热门"};//头部额外的索引
    public static String citys = "";
    private SPUtil spUtil;
    private List<ServiceBean> list;
    private ServiceAdapter serviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        ButterKnife.bind(this, view);
        dialog = new LoadingDialog(getContext(), 0);
//        if (BaseApplication.NewIsLogin == 0)//判断是否登录过
//        {
//            if (NetWorkUtil.check(getContext()))
//                getData();
//        } else {
//            initview();
//        }
        initview();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        String city = spUtil.getString(BaseApplication.CITY, "");
        tv_city.setText(city);
        String tabs = spUtil.getString(BaseApplication.TABS, "");
        String str[] = tabs.split(",");
        list.clear();
        addDate(str);
        serviceAdapter.setListData(list);
        serviceAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化
     */
    private void initview() {
        if (spUtil == null)
            spUtil = new SPUtil(getContext(), "fileName");
        list = new ArrayList<>();
        String city = spUtil.getString(BaseApplication.CITY, "");
        tv_city.setText(city);
        String tabs = spUtil.getString(BaseApplication.TABS, "");
        String str[] = tabs.split(",");
        addDate(str);
        ly_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().startActivity(new Intent(getActivity(), CityAcvitity.class));
            }
        });
        serviceAdapter = new ServiceAdapter(getContext(), list);
        gridview.setAdapter(serviceAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {//新房
                    startActivity(new Intent(getActivity(), NewHouseActivity.class));
                } else if (i == 1) {
                }
//                    startActivity(new Intent(getActivity(), NewHouseActivity.class));
                else if (i == 3)//低价房
                    startActivity(new Intent(getActivity(), CheapRoomListActivity.class));
                else if (i == 4)//装修
                    startActivity(new Intent(getActivity(), RenovationListActivity.class));
                else if (i == 6)//金融
                    startActivity(new Intent(getActivity(), FinancialActivity.class));
            }
        });


    }

    private void addDate(String str[]) {
        for (int i = 1; i < str.length; i++) {
            ServiceBean serviceBean = new ServiceBean();
            switch (Integer.valueOf(str[i])) {
                case 1:
                    serviceBean.setTitle("新房");
                    serviceBean.setPic(R.mipmap.xf);
                    list.add(serviceBean);
                    break;
                case 2:
                    serviceBean.setTitle("二手房");
                    serviceBean.setPic(R.mipmap.esf);
                    list.add(serviceBean);
                    break;
                case 3:
                    serviceBean.setTitle("租房");
                    serviceBean.setPic(R.mipmap.zf);
                    list.add(serviceBean);
                    break;
                case 4:
                    serviceBean.setTitle("低价房");
                    serviceBean.setPic(R.mipmap.djf);
                    list.add(serviceBean);
                    break;
                case 5:
                    serviceBean.setTitle("装修");
                    serviceBean.setPic(R.mipmap.zx);
                    list.add(serviceBean);
                    break;
                case 6:
                    serviceBean.setTitle("活动");
                    serviceBean.setPic(R.mipmap.hd);
                    list.add(serviceBean);
                    break;
                case 7:
                    serviceBean.setTitle("金融");
                    serviceBean.setPic(R.mipmap.jr);
                    list.add(serviceBean);
                    break;
                case 8:
                    serviceBean.setTitle("家政");
                    serviceBean.setPic(R.mipmap.jz);
                    list.add(serviceBean);
                    break;
                case 9:
                    serviceBean.setTitle("旅游");
                    serviceBean.setPic(R.mipmap.ly);
                    list.add(serviceBean);
                    break;
                default:
                    break;

            }
        }
    }

    /**
     * 访问登录接口
     */
//    private void getData() {
//        dialog.show();
//        NetHelperNew.login("13888888883", "123456", new HttpUtils.HttpCallback() {
//            @Override
//            public void onSuccess(String data) {
//                dialog.dismiss();
//                final LoginBean loginBean = MyGson.getInstance().fromJson(data, LoginBean.class);
//                if (loginBean.getType().equals("1")) {
//                    btn_login.setVisibility(View.GONE);
//                    BaseApplication.NewIsLogin = 1;
//                    BaseApplication.loginBean = loginBean;
//                    initview();
//                } else {
//                    btn_login.setVisibility(View.VISIBLE);
//                }
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ToastUtil.showToast(getContext(), loginBean.getContent());
//                    }
//                });
//            }
//
//            @Override
//            public void onError(final String msg) {
//                super.onError(msg);
//                dialog.dismiss();
//                btn_login.setVisibility(View.VISIBLE);
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ToastUtil.showToast(getContext(), msg);
//                    }
//                });
//            }
//        });
//
//    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login://登录失败的点击事件
                if (NetWorkUtil.check(getContext()))
                    //  getData();
                    break;

        }
    }
}
