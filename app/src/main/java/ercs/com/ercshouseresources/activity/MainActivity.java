package ercs.com.ercshouseresources.activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.fragment.HouseFragment;
import ercs.com.ercshouseresources.fragment.MineFragment;
import ercs.com.ercshouseresources.fragment.ServiceFragment;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.view.lazyviewpager.LazyFragmentPagerAdapter;

/**
 * Created by Administrator on 2017/6/21.
 * 主页
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.iv_msg)
    ImageView iv_msg;
    @BindView(R.id.iv_mine)
    ImageView iv_mine;
    @BindView(R.id.iv_service)
    ImageView iv_service;
    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @BindView(R.id.tv_mine)
    TextView tv_mine;
    @BindView(R.id.tv_service)
    TextView tv_service;
    private static final int NUM = 3;//设置Viewpager 里面的 fragment的数量

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
    }

    /**
     * 初始化
     */
    private void initview() {
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
        setBottomLabState(0);
        viewpager.setAdapter(new CustomLazyFragmentPagerAdapter(getSupportFragmentManager()));
    }

    /**
     * 底部类别点击事件处理
     *
     * @param view
     */
    @OnClick({R.id.ly_house, R.id.ly_service, R.id.ly_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_house://房源
                setBottomLabState(0);
                viewpager.setCurrentItem(0, false);
                break;
            case R.id.ly_service://服务
                setBottomLabState(1);
                viewpager.setCurrentItem(1, false);
                break;
            case R.id.ly_mine://我的
                setBottomLabState(2);
                viewpager.setCurrentItem(2, false);
                break;

        }
    }

    /**
     * 点击的底部分类图标状态
     *
     * @param position
     */
    private void setBottomLabState(int position) {
        switch (position) {
            case 0:
                setTextAndImg(tv_msg, iv_msg, R.mipmap.message_over, true);
                setTextAndImg(tv_mine, iv_mine, R.mipmap.mine_pre, false);
                break;
            case 1:
                setTextAndImg(tv_msg, iv_msg, R.mipmap.message_pre, false);
                setTextAndImg(tv_mine, iv_mine, R.mipmap.mine_over, true);
                break;
            case 2:
                setTextAndImg(tv_msg, iv_msg, R.mipmap.message_pre, false);
                setTextAndImg(tv_mine, iv_mine, R.mipmap.mine_over, true);
                break;
        }
    }

    /**
     * 设置选择分类的样式
     *
     * @param tv
     * @param iv
     * @param res
     * @param isBlue
     */
    private void setTextAndImg(TextView tv, ImageView iv, int res, boolean isBlue) {
        iv.setImageResource(res);
        if (isBlue) {
            tv.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        } else {
            tv.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    private static class CustomLazyFragmentPagerAdapter extends LazyFragmentPagerAdapter {

        private CustomLazyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(ViewGroup container, int position) {
            if (position == 0) {
                return new HouseFragment();
            } else if (position == 1) {
                return new ServiceFragment();
            } else if (position == 2) {
                return new MineFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM;
        }

    }
}
