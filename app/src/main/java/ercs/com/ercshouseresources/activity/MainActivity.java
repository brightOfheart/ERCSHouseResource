package ercs.com.ercshouseresources.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
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
import ercs.com.ercshouseresources.fragment.NewMineFragment;
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
    @BindView(R.id.iv_mine)
    ImageView iv_mine;
    @BindView(R.id.iv_service)
    ImageView iv_service;
    @BindView(R.id.tv_mine)
    TextView tv_mine;
    @BindView(R.id.tv_service)
    TextView tv_service;
    private static final int NUM = 2;//设置Viewpager 里面的 fragment的数量

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 初始化
     */
    private void initview() {
        if (!CloseActivityClass.activityList.contains(this)) {
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
    @OnClick({R.id.ly_service, R.id.ly_mine})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.ly_house://房源
//                setBottomLabState(0);
//                viewpager.setCurrentItem(0, false);
//                break;
            case R.id.ly_service://服务
                setBottomLabState(0);
                viewpager.setCurrentItem(0, false);
                break;
            case R.id.ly_mine://我的
                setBottomLabState(1);
                viewpager.setCurrentItem(1, false);
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
                setTextAndImg(tv_service, iv_service, R.mipmap.service_select, true);
                setTextAndImg(tv_mine, iv_mine, R.mipmap.mine_pre, false);
                break;
            case 1:
                setTextAndImg(tv_service, iv_service, R.mipmap.service, false);
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
            tv.setTextColor(ContextCompat.getColor(this, R.color.system_color));
        } else {
            tv.setTextColor(ContextCompat.getColor(this, R.color.clor_maingray));
        }
    }

    private static class CustomLazyFragmentPagerAdapter extends LazyFragmentPagerAdapter {

        private CustomLazyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(ViewGroup container, int position) {
//            if (position == 0) {
//                return new HouseFragment();
//            } else if (position == 1) {
//                return new ServiceFragment();
//            } else if (position == 2) {
//                return new NewMineFragment();
//            }
            if (position == 0) {
                return new ServiceFragment();
            } else if (position == 1) {
                return new NewMineFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM;
        }

    }


    interface getCityListener {
        public String getCity();
    }

    /**
     * 返回键隐藏到后台
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*退出对话框*/
    public void dialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("提示")
                .setMessage("确定要退出吗？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog dialog_dialog = dialog.create();
        dialog_dialog.show();

    }
}
