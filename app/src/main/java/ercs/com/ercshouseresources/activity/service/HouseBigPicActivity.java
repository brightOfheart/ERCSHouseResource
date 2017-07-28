package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.HouseBigPicAdapter;
import ercs.com.ercshouseresources.bean.NewHouseDetailBean;
import ercs.com.ercshouseresources.network.MyGson;

/**
 * Created by Administrator on 2017/7/28.
 * 显示房源详情页的大图片
 */

public class HouseBigPicActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_oration)
    TextView tv_oration;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.gridview)
    GridView gridview;
    private NewHouseDetailBean newHouseDetailBean;
    private String count;
    private List<String> list;
    private HouseBigPicAdapter adapter;
    /**
     * 页面跳转
     *
     * @param mActiivty
     */
    public static void start(Activity mActiivty, String json, String counts) {
        Intent intent = new Intent(mActiivty, HouseBigPicActivity.class);
        intent.putExtra("json", json);
        intent.putExtra("counts", counts);
        mActiivty.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housebigpic);
        ButterKnife.bind(this);
        getData();
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.iv_goback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_goback://返回点击事件
                finish();
                break;
        }
    }

    private String getJson() {
        return getIntent().getStringExtra("json");
    }

    private String getCount() {
        return getIntent().getStringExtra("counts");
    }
    private void setData(String str1,String str2,String str3,String str4)
    {
        tv_title.setText(str1);
        tv_price.setText(str2);
        tv_oration.setText(str3);
        tv_type.setText(str4);

    }
    private void getData() {
        newHouseDetailBean = MyGson.getInstance().fromJson(getJson(), NewHouseDetailBean.class);
        viewpager.setAdapter(new MyAdapter());
        if(Integer.valueOf(getCount())>0)
        viewpager.setCurrentItem(Integer.valueOf(getCount())-1);
        tv_num.setText(getCount() + "/" + newHouseDetailBean.getData().getHouseTypeList().size());
        if (newHouseDetailBean.getData().getHouseTypeList().size() > 0) {
            setData(newHouseDetailBean.getData().getHouseTypeList().get(Integer.valueOf(getCount())).getName(),
                    newHouseDetailBean.getData().getHouseTypeList().get(Integer.valueOf(getCount())).getPrice(),
                    newHouseDetailBean.getData().getHouseTypeList().get(Integer.valueOf(getCount())).getOrientations(),
                    newHouseDetailBean.getData().getHouseTypeList().get(Integer.valueOf(getCount())).getDecorationCondition()
                    );
            list=newHouseDetailBean.getData().getHouseTypeList().get(Integer.valueOf(getCount())).getHouseTypeTagList();
            adapter= new HouseBigPicAdapter(this, list);
            gridview.setAdapter(adapter);
        }
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_num.setText(position+1 + "/" + newHouseDetailBean.getData().getHouseTypeList().size());
                list = newHouseDetailBean.getData().getHouseTypeList().get(position).getHouseTypeTagList();
                gridview.setAdapter(new HouseBigPicAdapter(HouseBigPicActivity.this, list));
                setData(newHouseDetailBean.getData().getHouseTypeList().get(position).getName(),
                        newHouseDetailBean.getData().getHouseTypeList().get(position).getPrice(),
                        newHouseDetailBean.getData().getHouseTypeList().get(position).getOrientations(),
                        newHouseDetailBean.getData().getHouseTypeList().get(position).getDecorationCondition());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return newHouseDetailBean.getData().getHouseTypeList().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 准备显示的数据，一个简单的
            ImageView tv = new ImageView(HouseBigPicActivity.this);
            tv.setImageResource(R.mipmap.ic_launcher);
            // 添加到ViewPager容器
            container.addView(tv);
            // 返回填充的View对象
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
