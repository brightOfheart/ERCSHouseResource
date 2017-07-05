package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.text.ParseException;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.AllProcessBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.TimeUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/6/27.
 */

public class AllProcessAdapter extends ViewHolderAdapter<AllProcessBean.DataBean> {
    private Context context;

    public AllProcessAdapter(Context context, List<AllProcessBean.DataBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, AllProcessBean.DataBean dataBean, int position) {
        return inflate(R.layout.adapter_allprocess);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, AllProcessBean.DataBean dataBean, final int position) {
        holder.setText(R.id.tv_title,"我的"+type(dataBean.getLeaveType())+"申请");
        holder.setText(R.id.tv_state, state(dataBean.getLeaveState()));
        holder.setText(R.id.tv_time, getDay(dataBean.getCreatedTime()));
        GlideUtil.loadCircleImage(context, NetHelper.URL + dataBean.getPhotoPath(), (ImageView) holder.getView(R.id.iv_photo));
    }

    /**
     * 获取当前的年月日
     *
     * @param str
     * @return
     */
    private String getDay(String str) {
        String newdate = "";
        try {
            newdate = TimeUtil.dealDateFormat(str);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newdate;
    }

    /**
     * 判断状态
     *
     * @param str
     * @return
     */
    private String state(String str) {
        String state = "";
        if (str.equals("1"))
            state = "待审核";
        else if (str.equals("3"))
            state = "未通过";
        else
            state = "通过";
        return state;
    }

    /**
     * 选择类型
     *
     * @param str
     * @return
     */
    private String type(String str) {
        String type = "";

        if (str.equals("1"))
            type = "出勤";
        else if (str.equals("2"))
            type = "休息";
        else if (str.equals("3"))
            type = "迟到";
        else if (str.equals("4"))
            type = "早退";
        else if (str.equals("5"))
            type = "缺卡";
        else if (str.equals("6"))
            type = "旷工";
        else if (str.equals("7"))
            type = "外出";
        return type;
    }
}
