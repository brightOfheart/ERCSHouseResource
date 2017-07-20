package ercs.com.ercshouseresources.view.popupwindow;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.MoreGridViewAdapter;
import ercs.com.ercshouseresources.bean.UserDictionaryBean;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TimeCountUtil;
import ercs.com.ercshouseresources.view.MyGridView;
import ercs.com.ercshouseresources.view.dialog.CustomerDatePickerDialog;

/**
 * 更多
 * Created by Administrator on 2017/7/13.
 */

public class MoreSelectPop extends PopupWindow implements View.OnClickListener {

    private Activity context;
    private View view;




    private MoreGridViewAdapter transactionAdapter;//交易
    private MoreGridViewAdapter orientationAdapter;//朝向
    private MoreGridViewAdapter typeAdapter;//建筑类型
    private MoreGridViewAdapter purposeAdapter;//用途
    private MoreGridViewAdapter renovationAdapter;//装修
    private MoreGridViewAdapter dataAdapter;//日期


    private UserDictionaryBean userDictionaryBean;//房源类型列表

   private TextView tv_startdate,tv_enddate;

    private EditText edit_minarea,edit_maxarea;//最小面积 最大面积

    private List<UserDictionaryBean.DataBean> tradeTypelist,buildingTypelist,purposelist,renovationlist,orientationlist;//交易 建筑类型 用途 装修  朝向

    private List<UserDictionaryBean.DataBean> datetypelist;//日期类型
    private OnSelectMoreListener onSelectMoreListener;
    public MoreSelectPop(Context context,UserDictionaryBean userDictionaryBean,String startdate ,String enddate,OnSelectMoreListener onSelectMoreListener) {
        super(context);
        this.context = (Activity) context;
        this.onSelectMoreListener=onSelectMoreListener;

        this.userDictionaryBean=userDictionaryBean;
        initData();
        initPop();

        initTransactionView();
        initOrientation();
        initBuildingType();
        initPurpose();
        initRenovation();
        initDate();

        initempty();

        tv_enddate.setText(enddate);
        tv_startdate.setText(startdate);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        tradeTypelist =new ArrayList<>();
        buildingTypelist =new ArrayList<>();
        purposelist =new ArrayList<>();
        renovationlist =new ArrayList<>();
        orientationlist =new ArrayList<>();

        for (int i = 0; i < userDictionaryBean.getData().size(); i++) {
            switch (userDictionaryBean.getData().get(i).getName())
            {
                case "TradeType":
                    //交易
                    tradeTypelist.add((userDictionaryBean.getData().get(i)));
                    break;
                case "BuildingType":
                    //建筑类型
                    buildingTypelist.add((userDictionaryBean.getData().get(i)));
                    break;
                case "Purpose":
                    //用途
                    purposelist.add((userDictionaryBean.getData().get(i)));
                    break;
                case "Decoration":
                    //装修
                    renovationlist.add((userDictionaryBean.getData().get(i)));
                    break;
                case "Orientation":
                    //朝向
                    orientationlist.add((userDictionaryBean.getData().get(i)));
                    break;
            }
        }


    }

    /**
     * 清空 确定
     */
    private void initempty() {
        TextView tv_empty=view.findViewById(R.id.tv_empty);
        tv_empty.setOnClickListener(this);

        TextView tv_sure=view.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(this);

        edit_minarea=view.findViewById(R.id.edit_minarea);
        edit_maxarea=view.findViewById(R.id.edit_maxarea);
    }

    /**
     * 获得最小面积
     * @return
     */
    private String getMinAear()
    {
        if (!"".equals(edit_minarea.getText().toString()))
        {
            return edit_minarea.getText().toString();
        }
        return "0";
    }
    /**
     * 获得最大面积
     * @return
     */
    private String getMaxAear()
    {
        if (!"".equals(edit_maxarea.getText().toString()))
        {
            return edit_maxarea.getText().toString();
        }
        return "0";
    }
    /**
     * 初始化 交易
     */
    private void initTransactionView() {
        MyGridView gridview_transaction=view.findViewById(R.id.gridview_transaction);


        transactionAdapter=new MoreGridViewAdapter(context,tradeTypelist,-1);
        gridview_transaction.setAdapter(transactionAdapter);
        gridview_transaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                transactionAdapter.setSelected(i);
            }
        });
    }

    /**
     * 初始化 建筑类型gridview
     */
    private void initBuildingType() {
        MyGridView gridview_transaction=view.findViewById(R.id.gridview_type);


        typeAdapter=new MoreGridViewAdapter(context,buildingTypelist,-1);
        gridview_transaction.setAdapter(typeAdapter);
        gridview_transaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeAdapter.setSelected(i);
            }
        });
    }
    /**
     * 用途
     */
    private void initPurpose() {
        MyGridView gridview_transaction=view.findViewById(R.id.gridview_purpose);


        purposeAdapter=new MoreGridViewAdapter(context,purposelist,-1);
        gridview_transaction.setAdapter(purposeAdapter);
        gridview_transaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                purposeAdapter.setSelected(i);
            }
        });
    }

    /**
     * 装修
     */
    private void initRenovation() {
        MyGridView gridview_transaction=view.findViewById(R.id.gridview_renovation);


        renovationAdapter=new MoreGridViewAdapter(context,renovationlist,-1);
        gridview_transaction.setAdapter(renovationAdapter);
        gridview_transaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                renovationAdapter.setSelected(i);
            }
        });
    }
    /**
     * 日期
     */
    private void initDate() {
        MyGridView gridview_transaction=view.findViewById(R.id.gridview_date);
        datetypelist=new ArrayList<>();
        datetypelist.add(new UserDictionaryBean.DataBean(1,"DateType","录入日期"));
        datetypelist.add(new UserDictionaryBean.DataBean(0,"DateType","委托日期"));
        datetypelist.add(new UserDictionaryBean.DataBean(3,"DateType","交房日期"));

        dataAdapter=new MoreGridViewAdapter(context,datetypelist,-1);
        gridview_transaction.setAdapter(dataAdapter);
        gridview_transaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dataAdapter.setSelected(i);

            }
        });

         tv_startdate=view.findViewById(R.id.tv_startdate);
        tv_startdate.setOnClickListener(this);

        tv_enddate=view.findViewById(R.id.tv_enddate);
        tv_enddate.setOnClickListener(this);
    }



    /**
     * 朝向
     */
    private void initOrientation() {
        MyGridView gridview_transaction=view.findViewById(R.id.gridview_orientation);


        orientationAdapter=new MoreGridViewAdapter(context,orientationlist,-1);
        gridview_transaction.setAdapter(orientationAdapter);
        gridview_transaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                orientationAdapter.setSelected(i);
            }
        });
    }
    /**
     * 初始化pop样式
     */
    private void initPop() {
        view= LayoutInflater.from(context).inflate(R.layout.pop_more_select,null);
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
            case R.id.tv_startdate:
                new CustomerDatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tv_startdate.setText(year + "-" + (month + 1) + "-" + day );

                    }
                }, getYear(), getMonth(), getDay(), 1);
                break;
            case R.id.tv_enddate:

                new CustomerDatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tv_enddate.setText(year + "-" + (month + 1) + "-" + day );

                    }
                }, getYear(), getMonth(), getDay(), 1);
                break;
            case R.id.tv_empty:
                //清空
                transactionAdapter.setSelected(-1);//交易
               orientationAdapter.setSelected(-1);//朝向
                typeAdapter.setSelected(-1);//建筑类型
               purposeAdapter.setSelected(-1);//用途
                 renovationAdapter.setSelected(-1);//装修
                dataAdapter.setSelected(-1);//日期
                break;
            case R.id.tv_sure:
                //确定
                onSelectMoreListener.getMore(gettradeTypeId(),getorientationId(),
                       getMinAear(),getMaxAear(),getbuildingTypeId() ,getpurposeId() ,getrenovationId()
                        ,tv_startdate.getText().toString(),tv_enddate.getText().toString(),getdatetypeId());
                dismiss();
                break;

        }
    }


    /**
     * 获取交易id
     * @return
     */
    public int gettradeTypeId()
    {
        if (transactionAdapter.getSelectedPos()!=-1)
        {
            return tradeTypelist.get(transactionAdapter.getSelectedPos()).getId();
        }
        return 0;
    }

    /**
     * 获取朝向id
     * @return
     */
    public int getorientationId()
    {
        if (orientationAdapter.getSelectedPos()!=-1)
        {
            return orientationlist.get(orientationAdapter.getSelectedPos()).getId();
        }
        return 0;
    }
    /**
     * 获取建筑类型id
     * @return
     */
    public int getbuildingTypeId()
    {
        if (typeAdapter.getSelectedPos()!=-1)
        {
            return buildingTypelist.get(typeAdapter.getSelectedPos()).getId();
        }
        return 0;
    }
    /**
     * 获取用途id
     * @return
     */
    public int getpurposeId()
    {
        if (purposeAdapter.getSelectedPos()!=-1)
        {
            return purposelist.get(purposeAdapter.getSelectedPos()).getId();
        }
        return 0;
    }
    /**
     * 获取装修id
     * @return
     */
    public int getrenovationId()
    {
        if (renovationAdapter.getSelectedPos()!=-1)
        {
            return renovationlist.get(renovationAdapter.getSelectedPos()).getId();
        }
        return 0;
    }
    /**
     * 获取日期类型id
     * @return
     */
    public int getdatetypeId()
    {
        if (dataAdapter.getSelectedPos()!=-1)
        {
            return datetypelist.get(dataAdapter.getSelectedPos()).getId();
        }
        return 0;
    }
    /**
     * 获取当前的年份
     *
     * @return
     */
    private int getYear() {
        return Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * 获取当前的月份
     *
     * @return
     */
    private int getMonth() {
        return Integer.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
    }

    /**
     * 获取当前的日
     */
    private int getDay() {
        return Integer.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }


    public interface OnSelectMoreListener
    {
        /**
         *
         * @param tradeType 交易
         * @param orientation 朝向
         * @param minArea 最小面积
         * @param maxArea 最大面积
         * @param buildingType 建筑类型
         * @param purpose 用途
         * @param renovation 装修
         * @param startdate 开始时间
         * @param enddate 结束时间
         * @param datetype 时间类型
         */
        public void getMore(int tradeType, int orientation,String minArea,String maxArea,int buildingType,int purpose,int renovation,String startdate ,String enddate,int datetype);
    }
}
