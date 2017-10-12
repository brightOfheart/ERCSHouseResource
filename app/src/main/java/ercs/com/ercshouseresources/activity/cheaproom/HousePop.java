package ercs.com.ercshouseresources.activity.cheaproom;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
import ercs.com.ercshouseresources.bean.HouseBean;
import ercs.com.ercshouseresources.view.popupwindow.BuildingTypeSelectPop;

/**
 * Created by Administrator on 2017/10/11.
 * 房型
 */

public class HousePop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;


    private OnSelectHouseLayoutListener onSelectHouseLayoutListener;
    private HouseLayoutSelectListviewAdapter houseLayoutSelectListviewAdapter;
    private List<HouseBean.DataBean.HouseTypeBean> list;
    private List<String> lists;

    public HousePop(Context context, List<HouseBean.DataBean.HouseTypeBean> list, OnSelectHouseLayoutListener onSelectHouseLayoutListener) {
        super(context);
        this.context = (Activity) context;
        this.list = list;
        this.onSelectHouseLayoutListener = onSelectHouseLayoutListener;
        initPop();

        initView();

    }

    private void initView() {
        View v = view.findViewById(R.id.view_null);
        v.setOnClickListener(this);

        ListView lv_houselayout = view.findViewById(R.id.lv_houselayout);
        lists = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            lists.add(this.list.get(i).getValue());
        }
        houseLayoutSelectListviewAdapter = new HouseLayoutSelectListviewAdapter(context, lists);
        lv_houselayout.setAdapter(houseLayoutSelectListviewAdapter);

        lv_houselayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onSelectHouseLayoutListener.selectHouseLayout(lists.get(i));
                houseLayoutSelectListviewAdapter.setSelected(i);
                dismiss();
            }
        });
    }


    /**
     * 初始化pop样式
     */
    private void initPop() {

        view = LayoutInflater.from(context).inflate(R.layout.pop_house, null);
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
    public interface OnSelectHouseLayoutListener {
        public void selectHouseLayout(String str);//不限

    }
}
