package ercs.com.ercshouseresources.view.popupwindow;

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
import ercs.com.ercshouseresources.util.DisplayUtil;
import ercs.com.ercshouseresources.util.OtherUitl;

/**
 * 新房 房源类型
 * Created by Administrator on 2017/7/13.
 */

public class BuildingTypeSelectPop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;


    private OnSelectHouseLayoutListener onSelectHouseLayoutListener;
    private HouseLayoutSelectListviewAdapter houseLayoutSelectListviewAdapter;

    public BuildingTypeSelectPop(Context context, OnSelectHouseLayoutListener onSelectHouseLayoutListener) {
        super(context);
        this.context = (Activity) context;

        this.onSelectHouseLayoutListener = onSelectHouseLayoutListener;
        initPop();

        initView();

    }

    private void initView() {
        View v = view.findViewById(R.id.view_null);
        v.setOnClickListener(this);

        ListView lv_houselayout = view.findViewById(R.id.lv_houselayout);
        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("住宅");
        list.add("旅游地产");
        list.add("商铺");
        houseLayoutSelectListviewAdapter = new HouseLayoutSelectListviewAdapter(context, list);
        lv_houselayout.setAdapter(houseLayoutSelectListviewAdapter);

        lv_houselayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onSelectHouseLayoutListener.selectHouseLayout(i);
                houseLayoutSelectListviewAdapter.setSelected(i);
                dismiss();
            }
        });
    }


    /**
     * 初始化pop样式
     */
    private void initPop() {

        view = LayoutInflater.from(context).inflate(R.layout.pop_houseayoutselect, null);
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
        public void selectHouseLayout(int i);//不限

    }
}
