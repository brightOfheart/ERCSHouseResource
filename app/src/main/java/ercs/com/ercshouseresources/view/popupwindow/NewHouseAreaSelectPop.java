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
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.HouseLayoutSelectListviewAdapter;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.AreaBean;
import ercs.com.ercshouseresources.bean.NewHouseAreaBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;

/**
 * 新房 区域选择
 * Created by Administrator on 2017/7/13.
 */

public class NewHouseAreaSelectPop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;



    private OnSelectNewAreaListener onSelectHouseLayoutListener;
    private HouseLayoutSelectListviewAdapter houseLayoutSelectListviewAdapter;

    private List<NewHouseAreaBean.DataBean> dataBeanList;
    public NewHouseAreaSelectPop(Context context, List<NewHouseAreaBean.DataBean> dataBeanList,OnSelectNewAreaListener onSelectHouseLayoutListener) {
        super(context);
        this.context = (Activity) context;

        this.onSelectHouseLayoutListener=onSelectHouseLayoutListener;
        this.dataBeanList=dataBeanList;
        this.dataBeanList.add(0,new NewHouseAreaBean.DataBean(0,"全部",0));
        initPop();

        initView();



    }




    private void initView() {
        View v=view.findViewById(R.id.view_null);
        v.setOnClickListener(this);

        ListView lv_houselayout=view.findViewById(R.id.lv_houselayout);
        final List<String> list=new ArrayList<>();
        //添加数据
        for (int i = 0; i < dataBeanList.size(); i++) {
            list.add(dataBeanList.get(i).getName());
        }
        houseLayoutSelectListviewAdapter = new HouseLayoutSelectListviewAdapter(context,list);
        lv_houselayout.setAdapter(houseLayoutSelectListviewAdapter);

        lv_houselayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onSelectHouseLayoutListener.selectArea(dataBeanList.get(i).getId());
                houseLayoutSelectListviewAdapter.setSelected(i);
                dismiss();
            }
        });
    }


    /**
     * 初始化pop样式
     */
    private void initPop() {

        view= LayoutInflater.from(context).inflate(R.layout.pop_newhouseareaselect,null);
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

        }
    }

    /**
     * 新房区域选择回调
     */
    public  interface OnSelectNewAreaListener
    {
        public void selectArea(int i);//

    }
}
