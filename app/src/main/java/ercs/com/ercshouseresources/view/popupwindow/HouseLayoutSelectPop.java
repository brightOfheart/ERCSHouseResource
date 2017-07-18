package ercs.com.ercshouseresources.view.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
 * 房型选择
 * Created by Administrator on 2017/7/13.
 */

public class HouseLayoutSelectPop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;



    private OnSelectHouseLayoutListener onSelectHouseLayoutListener;
    public HouseLayoutSelectPop(Context context, OnSelectHouseLayoutListener onSelectHouseLayoutListener) {
        super(context);
        this.context = (Activity) context;

        this.onSelectHouseLayoutListener=onSelectHouseLayoutListener;
        initPop();

        initView();

    }

    private void initView() {
        View v=view.findViewById(R.id.view_null);
        v.setOnClickListener(this);

        TextView tv_unlimited=view.findViewById(R.id.tv_unlimited);
        tv_unlimited.setOnClickListener(this);
        TextView tv_onehouse=view.findViewById(R.id.tv_onehouse);
        tv_onehouse.setOnClickListener(this);
        TextView tv_twohouse=view.findViewById(R.id.tv_twohouse);
        tv_twohouse.setOnClickListener(this);
        TextView tv_threehouse=view.findViewById(R.id.tv_threehouse);
        tv_threehouse.setOnClickListener(this);
        TextView tv_fourhouse=view.findViewById(R.id.tv_fourhouse);
        tv_fourhouse.setOnClickListener(this);
        TextView tv_fivehouse=view.findViewById(R.id.tv_fivehouse);
        tv_fivehouse.setOnClickListener(this);
        TextView tv_sixhouse=view.findViewById(R.id.tv_sixhouse);
        tv_sixhouse.setOnClickListener(this);
    }


    /**
     * 初始化pop样式
     */
    private void initPop() {

        view= LayoutInflater.from(context).inflate(R.layout.pop_houseayoutselect,null);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);


        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setContentView(view);


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
            case R.id.tv_unlimited:
                //不限
                onSelectHouseLayoutListener.selectHouseLayout(0);
                dismiss();
                break;
            case R.id.tv_onehouse:
                //一室
                onSelectHouseLayoutListener.selectHouseLayout(1);
                dismiss();
                break;
            case R.id.tv_twohouse:
                //二室
                onSelectHouseLayoutListener.selectHouseLayout(2);
                dismiss();
                break;
            case R.id.tv_threehouse:
                //三室
                onSelectHouseLayoutListener.selectHouseLayout(3);
                dismiss();
                break;
            case R.id.tv_fourhouse:
                //四室
                onSelectHouseLayoutListener.selectHouseLayout(4);
                dismiss();
                break;
            case R.id.tv_fivehouse:
                //五室
                onSelectHouseLayoutListener.selectHouseLayout(5);
                dismiss();
                break;
            case R.id.tv_sixhouse:
                //六室
                onSelectHouseLayoutListener.selectHouseLayout(6);
                dismiss();
                break;
        }
    }

    /**
     * 房型选择回调
     */
    public  interface OnSelectHouseLayoutListener
    {
        public void selectHouseLayout(int i);//不限

    }
}
