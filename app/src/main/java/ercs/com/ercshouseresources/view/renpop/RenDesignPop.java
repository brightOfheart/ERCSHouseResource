package ercs.com.ercshouseresources.view.renpop;

import android.content.Context;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.RenDesignPopAdapter;
import ercs.com.ercshouseresources.bean.RenSelectListBean;


/**
 * Created by Administrator on 2017/8/11.
 */

public class RenDesignPop extends PopupWindow implements View.OnClickListener {
    private Context context;
    private View view;
    private GridView gridview;
    private OnSelectContentListener listener;
    private List<RenSelectListBean.DataBean.HouseTypeBean> list;
    private RenDesignPopAdapter renpop;
    private int kind = 0;

    public RenDesignPop(Context context, OnSelectContentListener listener, List<RenSelectListBean.DataBean.HouseTypeBean> list) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.list = list;
        initPop();
    }

    /**
     * 初始化pop样式
     */
    private void initPop() {

        view = LayoutInflater.from(context).inflate(R.layout.pop_rendesign, null);

        View v = view.findViewById(R.id.view_null);
        v.setOnClickListener(this);
        gridview = (GridView) view.findViewById(R.id.gridview);
        renpop = new RenDesignPopAdapter(context, list);
        gridview.setAdapter(renpop);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.selectContent(list.get(i).getId() + "");
                dismiss();
            }
        });
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
     * 装修选择回调
     */
    public interface OnSelectContentListener {
        public void selectContent(String s);//

    }
}
