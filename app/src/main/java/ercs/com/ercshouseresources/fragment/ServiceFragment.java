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
import ercs.com.ercshouseresources.activity.service.NewHouseActivity;
import ercs.com.ercshouseresources.adapter.ServiceAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.AllProcessBean;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
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
        if (BaseApplication.NewIsLogin == 0)//判断是否登录过
        {
            if (NetWorkUtil.check(getContext()))
                getData();
        } else {
            initview();
        }
        return view;

    }

    /**
     * 初始化
     */
    private void initview() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add("" + i);
        }
        gridview.setAdapter(new ServiceAdapter(getContext(), list));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startActivity(new Intent(getActivity(), NewHouseActivity.class));

            }
        });


    }

    /**
     * 访问登录接口
     */
    private void getData() {
        dialog.show();
        NetHelperNew.login("13888888888", "123456", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, AllProcessBean.class);
                if (baseBean.getType().equals("1")) {
                    btn_login.setVisibility(View.GONE);
                    BaseApplication.NewIsLogin = 1;
                    initview();
                } else {
                    btn_login.setVisibility(View.VISIBLE);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getContext(), baseBean.getContent());
                    }
                });
            }

            @Override
            public void onError(final String msg) {
                super.onError(msg);
                dialog.dismiss();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getContext(), msg);
                    }
                });
            }
        });

    }

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
                    getData();
                break;

        }
    }
}
