package ercs.com.ercshouseresources.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.cheaproom.CheapRoomListActivity;
import ercs.com.ercshouseresources.activity.renovation.RenovationListActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseActivity;
import ercs.com.ercshouseresources.adapter.ServiceAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.newbean.LoginBean;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.ToastUtil;
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
    private LoadingDialog dialog;

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

    /**
     * 初始化
     */
    private void initview() {
        List<String> list = new ArrayList<>();
        list.add("中介");
        list.add("新房");
        list.add("低价房");
        list.add("装修");
        list.add("金融");
        list.add("个人中心");
        gridview.setAdapter(new ServiceAdapter(getContext(), list));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                } else if (i == 1)//新房
                    startActivity(new Intent(getActivity(), NewHouseActivity.class));
                else if (i == 2)//低价房
                    startActivity(new Intent(getActivity(), CheapRoomListActivity.class));
                else if (i == 3)//装修
                    startActivity(new Intent(getActivity(), RenovationListActivity.class));
            }
        });


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
