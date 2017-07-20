package ercs.com.ercshouseresources.view.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.AreaSelectListviewAdapter;
import ercs.com.ercshouseresources.adapter.PriceSelectListviewAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.AreaBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;

/**
 * 区域选择
 * Created by Administrator on 2017/7/13.
 */

public class AreaSelectPop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;

    private ListView lv_areaone,lv_areatwo;

    private List<AreaBean.DataBean> areaone_list,areatwo_list;

    private OnSelectAreaListener onSelectAreaListener;


    private AreaSelectListviewAdapter areaTwoSelectListviewAdapter;

    private int areaid=0;//区域id
    private int steetid=0;//片区id
    private AreaSelectListviewAdapter areaOneSelectListviewAdapter;

    public AreaSelectPop(Context context,OnSelectAreaListener onSelectAreaListener) {
        super(context);
        this.context = (Activity) context;

        this.onSelectAreaListener= onSelectAreaListener;

        initPop();

        if (BaseApplication.areas.size()==0)
        {
            //数据为空网络加载
            downloadAreaList();
        }else
        {
            initView(BaseApplication.areas);
        }

    }

    /**
     * 下载区域信息
     */
    private void downloadAreaList() {

        NetHelper.AreaList(new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                AreaBean areaBean = MyGson.getInstance().fromJson(data, AreaBean.class);
                BaseApplication.areas.addAll(areaBean.getData());
                initView(areaBean.getData());
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                Log.i("-->","下载区域信息失败"+msg);

            }
        });
    }

    /**
     * 初始化控价
     */
    private void initView(final List<AreaBean.DataBean> dataBeanList) {

        areaone_list=new ArrayList<>();
        areaone_list.add(new AreaBean.DataBean(0,"不限",0));
        areatwo_list=new ArrayList<>();

        //半透明的view
        View v = view.findViewById(R.id.view_null);
        v.setOnClickListener(this);

        lv_areaone=view.findViewById(R.id.lv_areaone);
        lv_areatwo=view.findViewById(R.id.lv_areatwo);


        for (int i = 0; i < BaseApplication.areas.size(); i++) {
            AreaBean.DataBean dataBean = dataBeanList.get(i);
            if (468==dataBean.getParentId())
            {
                areaone_list.add(dataBean);
            }
        }

        areaOneSelectListviewAdapter = new AreaSelectListviewAdapter(context,areaone_list);
        lv_areaone.setAdapter(areaOneSelectListviewAdapter);


        //二级列表
        areatwo_list.add(new AreaBean.DataBean(0,"不限",0));
        areaTwoSelectListviewAdapter = new AreaSelectListviewAdapter(context,areatwo_list);
        lv_areatwo.setAdapter(areaTwoSelectListviewAdapter);
        /**
         * 区域选择一级
         */
        lv_areaone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("-->","点击了"+i);
                areaOneSelectListviewAdapter.setSelected(i);
                if (i==0)
                {
                    areaid=0;
                    Log.i("-->","到这了");
                    //选择一级不限
                    onSelectAreaListener.getAreaId(0,0);
                    dismiss();
                }
                    areaid=areaone_list.get(i).getId();
                    //区域二级列表赋值
                    areatwo_list.clear();
                    areatwo_list.add(new AreaBean.DataBean(0,"不限",0));
                    for (int j = 0; j <dataBeanList.size(); j++) {
                        AreaBean.DataBean dataBean =dataBeanList.get(j);
                        if (areaone_list.get(i).getId()==dataBean.getParentId())
                        {
                            areatwo_list.add(dataBean);
                        }
                    }

                    areaTwoSelectListviewAdapter.setSelected(-1);


            }
        });


        /**
         * 区域选择二级 点击item
         */
        lv_areatwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                areaTwoSelectListviewAdapter.setSelected(i);
                onSelectAreaListener.getAreaId(areaid,areatwo_list.get(i).getId());
                dismiss();
            }
        });



    }

    /**
     * 初始化pop样式
     */
    private void initPop() {

        view= LayoutInflater.from(context).inflate(R.layout.pop_areaselect,null);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);


        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setContentView(view);


//        setAnimationStyle(R.style.mypopwindow_anim_style);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.gray_bg));
        //设置SelectPicPopupWindow弹出窗体的背景
        setBackgroundDrawable(dw);

        //获取popwindow焦点
        setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        setOutsideTouchable(true);
    }

    /**\
     * 设置透明背景
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.view_null:
                //点击半透明
                dismiss();
                break;
        }
    }


    /**
     * 获取选择地区域ID
     */
    public  interface OnSelectAreaListener
    {
        public void getAreaId(int areaid,int steetid);
    }
}
