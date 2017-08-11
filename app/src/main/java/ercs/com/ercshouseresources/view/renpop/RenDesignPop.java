package ercs.com.ercshouseresources.view.renpop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.RenDesignPopAdapter;

import static com.baidu.location.d.j.n;
import static com.baidu.location.d.j.t;

/**
 * Created by Administrator on 2017/8/11.
 */

public class RenDesignPop extends PopupWindow {
    private Context context;
    private View view;
    private GridView gridview;
    private OnSelectContentListener listener;
    public RenDesignPop(Context context,OnSelectContentListener listener) {
        super(context);
        this.context = context;
        this.listener=listener;
        initPop();
    }

    /**
     * 初始化pop样式
     */
    private void initPop() {

        view= LayoutInflater.from(context).inflate(R.layout.pop_rendesign,null);
        gridview=(GridView)view.findViewById(R.id.gridview);
        gridview.setAdapter(new RenDesignPopAdapter(context,null));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.selectContent("ss");
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
    /**
     * 装修选择回调
     */
    public  interface OnSelectContentListener
    {
        public void selectContent(String s);//

    }
}
