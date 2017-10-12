package ercs.com.ercshouseresources.view.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.HouseSelectAdapter;
import ercs.com.ercshouseresources.bean.HouseBean;
import ercs.com.ercshouseresources.view.MyGridView;

/**
 * Created by Administrator on 2017/10/11.
 * 更多
 */

public class MorePop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;
    private HouseBean.DataBean Data;

    private OngetContentListener ongetContentListener;
    private HouseSelectAdapter houseSelectAdapter1;
    private HouseSelectAdapter houseSelectAdapter2;
    private EditText edit_1, edit_2, edit_3, edit_4, edit_5, edit_6;
    public String Dcv, btv;

    public MorePop(Context context, HouseBean.DataBean Data, OngetContentListener ongetContentListener) {
        super(context);
        this.context = (Activity) context;
        this.Data = Data;
        this.ongetContentListener = ongetContentListener;
        initPop();
        initView();

    }

    private void initView() {
        View v = view.findViewById(R.id.view_null);
        MyGridView gridView1 = view.findViewById(R.id.gridview1);
        edit_1 = view.findViewById(R.id.edit_1);
        edit_2 = view.findViewById(R.id.edit_2);
        MyGridView gridView2 = view.findViewById(R.id.gridview2);
        edit_3 = view.findViewById(R.id.edit_3);
        edit_4 = view.findViewById(R.id.edit_4);
        edit_5 = view.findViewById(R.id.edit_5);
        edit_6 = view.findViewById(R.id.edit_6);
        TextView tv_1 = view.findViewById(R.id.tv_1);
        TextView tv_2 = view.findViewById(R.id.tv_2);
        v.setOnClickListener(this);
        tv_1.setOnClickListener(new View.OnClickListener() {//清空
            @Override
            public void onClick(View view) {
                edit_1.setText("");
                edit_2.setText("");
                edit_3.setText("");
                edit_4.setText("");
                edit_5.setText("");
                edit_6.setText("");
            }
        });
        tv_2.setOnClickListener(new View.OnClickListener() {//确认
            @Override
            public void onClick(View view) {
                dismiss();
                ongetContentListener.getContent(Dcv, edit_1.getText().toString(), edit_2.getText().toString(), btv, edit_3.getText().toString(), edit_4.getText().toString(), edit_5.getText().toString(), edit_6.getText().toString());
            }
        });
        final List<String> list1 = new ArrayList<>();
        for (int i = 0; i < Data.getDecorationCondition().size(); i++) {
            list1.add(Data.getDecorationCondition().get(i).getValue());
        }
        houseSelectAdapter1 = new HouseSelectAdapter(context, list1);
        if (list1.size() > 0) {
            Dcv = list1.get(0);
        }
        gridView1.setAdapter(houseSelectAdapter1);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                houseSelectAdapter1.setSelected(i);
                Dcv = list1.get(i);
            }
        });
        final List<String> list2 = new ArrayList<>();
        for (int i = 0; i < Data.getBuildingsType().size(); i++) {
            list2.add(Data.getBuildingsType().get(i).getValue());
        }
        if (list2.size() > 0) {
            btv = list2.get(0);
        }
        houseSelectAdapter2 = new HouseSelectAdapter(context, list2);
        gridView2.setAdapter(houseSelectAdapter2);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                houseSelectAdapter2.setSelected(i);
                btv = list2.get(i);
            }
        });
    }


    /**
     * 初始化pop样式
     */
    private void initPop() {

        view = LayoutInflater.from(context).inflate(R.layout.pop_more, null);
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
     * 房型选择回调
     */
    public interface OngetContentListener {
        public void getContent(String Dcv, String areo1, String aer02, String btv, String flo1, String flo2, String year1, String year2);

    }

}
