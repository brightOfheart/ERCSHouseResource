package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.allprocess.AllProcessActivity;
import ercs.com.ercshouseresources.activity.attendance.AtendanceActivity;
import ercs.com.ercshouseresources.activity.clockin.ClockinActivity;
import ercs.com.ercshouseresources.activity.field.FieldActivity;
import ercs.com.ercshouseresources.activity.field.FieldClockInActivity;
import ercs.com.ercshouseresources.activity.process.ProcessAcvitity;
import ercs.com.ercshouseresources.activity.process.ProcessApplyAcvitity;
import ercs.com.ercshouseresources.activity.scheduling.SchedulingActivity;
import ercs.com.ercshouseresources.bean.MineBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.NetHelper;

/**
 * Created by Administrator on 2017/6/22.
 * 我的里面的适配器
 */

public class MineAdapter extends ViewHolderAdapter<MineBean> {
    private Context context;

    public MineAdapter(Context context, List<MineBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, MineBean mineBean, int position) {
        return inflate(R.layout.item_mine);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, MineBean mineBean, final int position) {
        holder.setText(R.id.tv_title, mineBean.getTitle());
        holder.setImageResource(R.id.iv_icon, mineBean.getIconid());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        context.startActivity(new Intent(context, ClockinActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, AtendanceActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, FieldClockInActivity.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, FieldActivity.class));
                        break;
                    case 4:
                        context.startActivity(new Intent(context, ProcessApplyAcvitity.class));
                        break;
                    case 5:
                        context.startActivity(new Intent(context, AllProcessActivity.class));
                        break;
                    case 6:
                        context.startActivity(new Intent(context, SchedulingActivity.class));
                        break;
                }
            }
        });
    }


}
