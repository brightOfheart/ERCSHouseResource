package ercs.com.ercshouseresources.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.clerk.ClerkActivity;
import ercs.com.ercshouseresources.activity.set.SetActivity;
import ercs.com.ercshouseresources.adapter.MineAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.MineBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.NoScrollListView;
import ercs.com.ercshouseresources.view.lazyviewpager.LazyFragmentPagerAdapter;

/**
 * Created by Administrator on 2017/6/22.
 * 我的页面
 */

public class MineFragment extends Fragment implements LazyFragmentPagerAdapter.Laziable {
    @BindView(R.id.listview)
    NoScrollListView listview;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_dep)
    TextView tv_dep;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    private SPUtil spUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        initview();
        return view;

    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.ly_clerk, R.id.ly_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_clerk://职员列表
                startActivity(new Intent(getContext(), ClerkActivity.class));
                break;
            case R.id.ly_set://设置
                startActivity(new Intent(getContext(), SetActivity.class));
                break;
        }
    }

    /**
     * 初始化
     */
    private void initview() {
        if (spUtil == null)
            spUtil = new SPUtil(getContext(), "fileName");
        listview.setAdapter(new MineAdapter(getContext(), setData()));
        tv_name.setText("欢迎您," + spUtil.getString(BaseApplication.NAME, ""));
        tv_dep.setText("[" + spUtil.getString(BaseApplication.DEPNAME, "") + "]");
        GlideUtil.loadCircleImage(getContext(), NetHelper.URL + spUtil.getString(BaseApplication.PHOTOPATH, ""), iv_photo);
    }

    /**
     * 设置初始化的数据
     */
    private List<MineBean> setData() {
        List<MineBean> listData = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            MineBean mineBean = new MineBean();
            switch (i) {
                case 0:
                    mineBean.setTitle(getResources().getString(R.string.str_clockin));
                    mineBean.setIconid(R.mipmap.icon_clockin);
                    break;
                case 1:
                    mineBean.setTitle(getResources().getString(R.string.str_attendance));
                    mineBean.setIconid(R.mipmap.icon_attendance);
                    break;
                case 2:
                    mineBean.setTitle(getResources().getString(R.string.str_fieldclockin));
                    mineBean.setIconid(R.mipmap.icon_field);
                    break;
                case 3:
                    mineBean.setTitle(getResources().getString(R.string.str_field));
                    mineBean.setIconid(R.mipmap.icon_field);
                    break;
                case 4:
                    mineBean.setTitle(getResources().getString(R.string.str_scheduling));
                    mineBean.setIconid(R.mipmap.icon_scheduling);
                    break;
                case 5:
                    mineBean.setTitle(getResources().getString(R.string.str_process));
                    mineBean.setIconid(R.mipmap.icon_process);
                    break;
                case 6:
                    mineBean.setTitle(getResources().getString(R.string.str_allprocess));
                    mineBean.setIconid(R.mipmap.icon_allprocess);
                    break;
            }
            listData.add(mineBean);
        }
        return listData;
    }
}
