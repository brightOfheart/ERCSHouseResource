package ercs.com.ercshouseresources.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.PhotoViewActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseDetailActivity;
import ercs.com.ercshouseresources.activity.service.NewHouseActivity;
import ercs.com.ercshouseresources.adapter.ServiceAdapter;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.lazyviewpager.LazyFragmentPagerAdapter;

/**
 * Created by Administrator on 2017/7/24.
 * 服务
 */

public class ServiceFragment extends Fragment implements LazyFragmentPagerAdapter.Laziable {
    @BindView(R.id.gridview)
    GridView gridview;
    private LoadingDialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        ButterKnife.bind(this, view);
        initview();
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
        getData();
    }

    /**
     * 访问登录接口
     */
    private void getData()
    {
        NetHelperNew.login("13888888888", "123456", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
            }
        });

    }
}
