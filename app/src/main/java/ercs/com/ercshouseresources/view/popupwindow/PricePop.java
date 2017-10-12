package ercs.com.ercshouseresources.view.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.HouseLayoutSelectListviewAdapter;

/**
 * Created by Administrator on 2017/10/11.
 * 价格pop
 */

public class PricePop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;
    private GetPriceListener getPriceListener;

    public PricePop(Context context, GetPriceListener getPriceListener) {
        super(context);
        this.context = (Activity) context;
        this.getPriceListener = getPriceListener;
        initPop();
        initView();

    }

    private void initView() {
        View v = view.findViewById(R.id.view_null);
        v.setOnClickListener(this);
        final EditText edit_1 = view.findViewById(R.id.edit_1);
        final EditText edit_2 = view.findViewById(R.id.edit_2);
        Button btn_sure = view.findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPriceListener.getPrice(edit_1.getText().toString(), edit_2.getText().toString());
                dismiss();
            }
        });
    }


    /**
     * 初始化pop样式
     */
    private void initPop() {

        view = LayoutInflater.from(context).inflate(R.layout.pop_price, null);
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

    /**
     * \
     * 设置透明背景
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.view_null:
                //点击半透明
                dismiss();
                break;

        }
    }

    /**
     * 价格回调
     */
    public interface GetPriceListener {
        public void getPrice(String str1, String str2);//不限

    }
}
