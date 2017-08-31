package ercs.com.ercshouseresources.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.clerk.ClerkActivity;
import ercs.com.ercshouseresources.activity.mine.MyOrderActivity;
import ercs.com.ercshouseresources.activity.service.DynamicActivity;
import ercs.com.ercshouseresources.activity.set.SetActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.lazyviewpager.LazyFragmentPagerAdapter;
import static ercs.com.ercshouseresources.util.StringUtil.getStr;

/**
 * Created by Administrator on 2017/8/22.
 */

public class NewMineFragment extends Fragment implements LazyFragmentPagerAdapter.Laziable {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_dep)
    TextView tv_dep;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_version)
    TextView tv_version;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.ly_manager)
    LinearLayout ly_manager;
    private SPUtil spUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newmine, container, false);
        ButterKnife.bind(this, view);
        initview();
        return view;

    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.ly_clerk, R.id.ly_set, R.id.ly_process, R.id.ly_memberAssess, R.id.ly_memberOutAssess})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_clerk://职员列表
                startActivity(new Intent(getContext(), ClerkActivity.class));
                break;
            case R.id.ly_process://我的订单
                startActivity(new Intent(getContext(), MyOrderActivity.class));
                break;
            case R.id.ly_memberAssess://最新动态
                DynamicActivity.start(getActivity(), "0");
                break;
            case R.id.ly_memberOutAssess://版本更新

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
        setShowData();
        isHideManager();
    }


    /**
     * 是否显示管理员的功能
     */
    private void isHideManager() {
        if (spUtil.getString(BaseApplication.AUTHORITY, "").equals("1") || spUtil.getString(BaseApplication.AUTHORITY, "").equals("2"))
            ly_manager.setVisibility(View.VISIBLE);
        else
            ly_manager.setVisibility(View.VISIBLE);
    }

    /**
     * 设置显示的View数据
     */
    private void setShowData() {
        tv_name.setText(getStr(R.string.str_welcome) + spUtil.getString(BaseApplication.NAME, ""));
        tv_company.setText(spUtil.getString(BaseApplication.COMPANY, ""));
        tv_dep.setText(getStr(R.string.str_leftbracket) + spUtil.getString(BaseApplication.DEPNAME, "") + getStr(R.string.str_rightbracket));
        GlideUtil.loadCircleImage(NetHelper.URL + spUtil.getString(BaseApplication.PHOTOPATH, ""), iv_photo);
        tv_version.setText(OtherUitl.getVersion(getContext()));
    }
}
