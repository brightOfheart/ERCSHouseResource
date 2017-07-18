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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.PriceSelectListviewAdapter;

/**
 * 价格选择
 * Created by Administrator on 2017/7/13.
 */

public class PriceSelectPop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;
    private ListView lv_houseprice,lv_rentalprice;//售房价格，租房价格


    private List<String> houseprices,rentalprices;//价格数据

    private EditText edit_minprice,edit_maxprice;
    private OnSelectPriceListener onSelectPriceListener;
    public PriceSelectPop(Context context,List<String> houseprices,List<String> rentalprices,OnSelectPriceListener onSelectPriceListener) {
        super(context);
        this.context = (Activity) context;
        this.houseprices=houseprices;
        this.rentalprices=rentalprices;
        this.onSelectPriceListener=onSelectPriceListener;
        initPop();
        initView();
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

        //半透明的view
        View v = view.findViewById(R.id.view_null);
        v.setOnClickListener(this);

        edit_minprice=view.findViewById(R.id.edit_minprice);
        edit_maxprice=view.findViewById(R.id.edit_maxprice);
        lv_houseprice.setAdapter(new PriceSelectListviewAdapter(context,houseprices));
        lv_rentalprice.setAdapter(new PriceSelectListviewAdapter(context,rentalprices));


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
                onSelectPriceListener.getPrice(edit_minprice.getText().toString(),edit_maxprice.getText().toString());
                dismiss();
                break;
            case R.id.view_null:
                //点击半透明
                dismiss();
                break;
        }
    }

    public  interface OnSelectPriceListener
    {
        public void getPrice(String min,String max);
    }
}
