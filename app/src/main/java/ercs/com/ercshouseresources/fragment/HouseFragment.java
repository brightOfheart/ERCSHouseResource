package ercs.com.ercshouseresources.fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.HouseAdapter;
import ercs.com.ercshouseresources.bean.HouseListBean;
import ercs.com.ercshouseresources.bean.UserDictionaryBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.lazyviewpager.LazyFragmentPagerAdapter;
import ercs.com.ercshouseresources.view.popupwindow.AreaSelectPop;
import ercs.com.ercshouseresources.view.popupwindow.HouseLayoutSelectPop;
import ercs.com.ercshouseresources.view.popupwindow.MoreSelectPop;
import ercs.com.ercshouseresources.view.popupwindow.PriceSelectPop;

/**
 * Created by Administrator on 2017/7/12.
 * 房源
 */

public class HouseFragment extends Fragment implements LazyFragmentPagerAdapter.Laziable {
    @BindView(R.id.edit_content)
    EditText edit_content;//搜索框
    @BindView(R.id.recyleview)
    LRecyclerView mRecyclerView ;
    @BindView(R.id.view_line)
    View view_line;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<HouseListBean.DataBean>  houseListBeans;
    private HouseAdapter houseAdapter;//房源列表

    private UserDictionaryBean userDictionaryBean;// 房源类型列表 更多


    private  String key="";// 关键字 “”
    private int  areaId =0;//城区 0
    private int streetId=0;// 片区 0
    private String beginPrice="0";//  最低价格 0
    private String endPrice="0";//  最高价格 0
    private int scale=0;//  房型 0

//    更多

    private int AtradeType;//交易
    private int Aorientation;//朝向
    private String AminArea="0";//最小面积
    private String AmaxArea="0";//最大面积
    private int AbuildingType;//建筑类型
    private int Apurpose;//用途
    private int Arenovation;//装修
    private String  Astartdate="2017-01-01";//开始时间
    private String  Aenddate="2018-01-01";//结束时间
    private int  Adatetype;//时间类型


    private int pagenum=1;//页数


    private PriceSelectPop priceSelectPop;
    private  AreaSelectPop areaSelectPop;
    private HouseLayoutSelectPop houseLayoutSelectPop;
    private MoreSelectPop moreSelectPop;


    private LoadingDialog loadingDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house, container, false);
        ButterKnife.bind(this, view);

        initNowDate();
        initview();
        getData(pagenum,true);

        getUserDictionary();
        initPriceSelectPop();
        initAreaSelectPop();
        initHouseLayoutSelectPop();

        return view;

    }

    /**
     * 获取当前时间
     */
    private void initNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        long time = date.getTime();

        long starttime=time-3*30*24*60*60*1000;

        Log.i("-->","DateLong:"+starttime);
        Aenddate=sdf.format(time);
//        Astartdate=sdf.format(new Date(starttime));
//        Log.i("-->", "时间"+Astartdate);

        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(calendar.MONTH, -3);  //设置为前3月
        Date dBefore = calendar.getTime();   //得到前3月的时间
        Astartdate=sdf.format(dBefore);
    }

    /**
     * 更多选择pop
     */
    private void initMoreSelectPop() {
        if (userDictionaryBean!=null)
        {
             moreSelectPop = new MoreSelectPop(getActivity(), userDictionaryBean,Astartdate,Aenddate, new MoreSelectPop.OnSelectMoreListener() {
                @Override
                public void getMore(int tradeType, int orientation, String minArea, String maxArea, int buildingType, int purpose, int renovation, String startdate, String enddate, int datetype) {

                    AtradeType=tradeType;
                    Aorientation=orientation;
                    AminArea=minArea;
                    AmaxArea=maxArea;
                    AbuildingType=buildingType;
                    Apurpose=purpose;
                    Arenovation=renovation;
                    Astartdate=startdate;
                    Aenddate=enddate;
                    Adatetype=datetype;


                    pagenum=1;
                    houseListBeans.clear();
                    houseAdapter.notifyDataSetChanged();
                    getData(pagenum,true);
                }
            });

        }
    }

    /**
     * 房型选择pop
     */
    private void initHouseLayoutSelectPop() {
         houseLayoutSelectPop = new HouseLayoutSelectPop(getActivity(), new HouseLayoutSelectPop.OnSelectHouseLayoutListener() {
            @Override
            public void selectHouseLayout(int i) {

                Log.i("-->","选择房型："+i);
                scale=i;

                pagenum=1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum,true);
            }
        });
    }

    /**
     * 地区选择pop
     */
    private void initAreaSelectPop() {
         areaSelectPop = new AreaSelectPop(getActivity(), new AreaSelectPop.OnSelectAreaListener() {
            @Override
            public void getAreaId(int areid, int steetid) {
                Log.i("-->","区域："+areid+" "+steetid);
                //获取区域ID
                areaId=areid;
                streetId=steetid;

                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                pagenum=1;
                getData(pagenum,true);

            }
        });
    }

    /**
     * 初始化价格选择pop
     */
    private void initPriceSelectPop() {
         priceSelectPop = new PriceSelectPop(getActivity(), new PriceSelectPop.OnSelectPriceListener() {
            @Override
            public void getPrice(String min, String max) {
                Log.i("-->","最高价："+min+" 最低价："+max);
                if ("".equals(min))
                {
                    beginPrice="0";
                }else
                {
                    beginPrice=min;
                }

                if ("".equals(max))
                {
                    endPrice="0";
                }else
                {
                    endPrice=max;
                }


                pagenum=1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum,true);
            }
        });
    }

    /**
     * 获取房源类型列表
     */
    private void getUserDictionary() {
        NetHelper.UserDictionary(new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {

                 userDictionaryBean = MyGson.getInstance().fromJson(data, UserDictionaryBean.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initMoreSelectPop();
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
    @OnClick({R.id.ly_area, R.id.ly_price, R.id.ly_housetype, R.id.ly_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_area://区域


                if (areaSelectPop!=null)
                areaSelectPop.showAsDropDown(view_line,0,0);
                break;
            case R.id.ly_price://价格

                priceSelectPop.showAsDropDown(view_line,0,0);
                break;
            case R.id.ly_housetype://房型

               if (houseLayoutSelectPop!=null)
                houseLayoutSelectPop.showAsDropDown(view_line,0,0);
                break;
            case R.id.ly_more://更多
                if ( moreSelectPop!=null)
                moreSelectPop.showAsDropDown(view_line,0,0);
                break;
        }
    }


    /**
     * 获取网络数据
     */
    private void getData(int pageIndex, final boolean isLoading) {

        if (NetWorkUtil.check(getActivity()))
        {
            if (isLoading)
            loadingDialog.show();
            NetHelper.getHouseList("4", pageIndex+"", "10",key,areaId+"",streetId+"",beginPrice+"",endPrice+"",scale+"",AtradeType,Aorientation,AbuildingType,Apurpose,AminArea,AmaxArea,Astartdate,Aenddate,Adatetype,Arenovation, new HttpUtils.HttpCallback() {
                @Override
                public void onSuccess(String data) {
                    if (isLoading)
                    loadingDialog.dismiss();
                    final HouseListBean houseListBean = MyGson.getInstance().fromJson(data, HouseListBean.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showToast(getContext(), houseListBean.getContent());

                            mRecyclerView.refreshComplete(10);
                            //更新数据
                            houseListBeans.addAll(houseListBean.getData());

                            houseAdapter.notifyDataSetChanged();
                            pagenum++;
                        }
                    });

                }

                @Override
                public void onError(String msg) {
                    super.onError(msg);
                    if (isLoading)
                    loadingDialog.dismiss();
                    mRecyclerView.refreshComplete(10);
                }
            });
        }

    }


    /**
     * 初始化
     */
    private void initview() {
        loadingDialog=new LoadingDialog(getActivity(),0);
        houseListBeans=new ArrayList<>();
        houseAdapter = new HouseAdapter(getActivity(), houseListBeans);

        lRecyclerViewAdapter = new LRecyclerViewAdapter(houseAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(lRecyclerViewAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        // mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);  //设置下拉刷新箭头
        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.system_color, R.color.system_color, android.R.color.white);
//设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.system_color, R.color.system_color, android.R.color.white);

        //分割线
        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                .setHeight(R.dimen.d_1)
                .setPadding(R.dimen.d_0)
                .setColorResource(R.color.white2)
                .build();
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh() {
                pagenum=1;
                houseListBeans.clear();
                houseAdapter.notifyDataSetChanged();
                getData(pagenum,false);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {//加载更多
            @Override
            public void onLoadMore() {
                getData(pagenum,false);
            }
        });



        //搜索框监听
        edit_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH) {

            /*隐藏软键盘*/
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(inputMethodManager.isActive()){
                        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    }
                    key=edit_content.getText().toString();

                    houseListBeans.clear();
                    houseAdapter.notifyDataSetChanged();
                    getData(pagenum,true);
                    return true;
                }
                return false;
            }
        });
    }



}
