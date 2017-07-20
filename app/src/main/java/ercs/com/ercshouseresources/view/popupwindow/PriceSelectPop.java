package ercs.com.ercshouseresources.view.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.PriceSelectListviewAdapter;
import ercs.com.ercshouseresources.bean.PriceBean;

/**
 * 价格选择
 * Created by Administrator on 2017/7/13.
 */

public class PriceSelectPop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;
    private ListView lv_houseprice,lv_rentalprice;//售房价格，租房价格

    private TextView tv_unlimited;//不限

    private List<PriceBean> houseprices,rentalprices;//价格数据

    private EditText edit_minprice,edit_maxprice;
    private OnSelectPriceListener onSelectPriceListener;
    private PriceSelectListviewAdapter housepriceSelectListviewAdapter;
    private PriceSelectListviewAdapter rentalpriceSelectListviewAdapter;

    public PriceSelectPop(Context context,OnSelectPriceListener onSelectPriceListener) {
        super(context);
        this.context = (Activity) context;
        this.onSelectPriceListener=onSelectPriceListener;



        initData( );
        initPop();
        initView();
    }

    private void initData() {
        houseprices=new ArrayList<>();

        rentalprices=new ArrayList<>();


        houseprices.add(new PriceBean("40万以下","0","400000"));
        houseprices.add(new PriceBean("40-50万","400000","500000"));
        houseprices.add(new PriceBean("50-60万","500000","600000"));
        houseprices.add(new PriceBean("60-70万","600000","700000"));


        rentalprices.add(new PriceBean("1000元以下","0","1000"));
        rentalprices.add(new PriceBean("1000-1500万","1000","1500"));
        rentalprices.add(new PriceBean("1500-2000万","1500","2000"));
        rentalprices.add(new PriceBean("2000-2500万","2000","2500"));




    }

    /**
     * 初始化控价
     */
    private void initView() {

        lv_houseprice=view.findViewById(R.id.lv_houseprice);
        lv_rentalprice=view.findViewById(R.id.lv_rentalprice);

        //确定按钮
        TextView tv_sure=view.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(this);
        //不限
         tv_unlimited=view.findViewById(R.id.tv_unlimited);
        tv_unlimited.setOnClickListener(this);

        //半透明的view
        View v = view.findViewById(R.id.view_null);
        v.setOnClickListener(this);

        edit_minprice=view.findViewById(R.id.edit_minprice);
        edit_maxprice=view.findViewById(R.id.edit_maxprice);
        housepriceSelectListviewAdapter = new PriceSelectListviewAdapter(context,houseprices);
        lv_houseprice.setAdapter(housepriceSelectListviewAdapter);
        rentalpriceSelectListviewAdapter = new PriceSelectListviewAdapter(context,rentalprices);
        lv_rentalprice.setAdapter(rentalpriceSelectListviewAdapter);


        //售房列表点击
        lv_houseprice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onSelectPriceListener.getPrice( houseprices.get(i).getMinprice(),houseprices.get(i).getMaxprice());
                housepriceSelectListviewAdapter.setSelected(i);
                rentalpriceSelectListviewAdapter.setSelected(-1);
                dismiss();

            }
        });


        //租房列表点击
        lv_rentalprice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onSelectPriceListener.getPrice( rentalprices.get(i).getMinprice(),rentalprices.get(i).getMaxprice());
                housepriceSelectListviewAdapter.setSelected(-1);
                rentalpriceSelectListviewAdapter.setSelected(i);
                dismiss();
            }
        });
    }

    /**
     * 初始化pop样式
     */
    private void initPop() {

        view= LayoutInflater.from(context).inflate(R.layout.pop_priceselect,null);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);


        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setContentView(view);


//        setAnimationStyle(R.style.mypopwindow_anim_style);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.gray_bg));
        //设置SelectPicPopupWindow弹出窗体的背景
        setBackgroundDrawable(dw);
//
//        backgroundAlpha(0.7f);
//        setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                backgroundAlpha(1f);
//            }
//        });
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
            case R.id.tv_sure:
                //点击确定
                housepriceSelectListviewAdapter.setSelected(-1);
                rentalpriceSelectListviewAdapter.setSelected(-1);
                onSelectPriceListener.getPrice(edit_minprice.getText().toString(),edit_maxprice.getText().toString());
                dismiss();
                break;
            case R.id.view_null:
                //点击半透明
                dismiss();
                break;
            case R.id.tv_unlimited:
                //点击不限
                onSelectPriceListener.getPrice("0","0");
                dismiss();
                housepriceSelectListviewAdapter.setSelected(-1);
                rentalpriceSelectListviewAdapter.setSelected(-1);
                break;
        }
    }

    public  interface OnSelectPriceListener
    {
        public void getPrice(String min,String max);//其他点击

    }
}
