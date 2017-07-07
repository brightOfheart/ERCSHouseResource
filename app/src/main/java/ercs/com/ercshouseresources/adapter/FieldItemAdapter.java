package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.PhotoViewActivity;
import ercs.com.ercshouseresources.bean.AllProcessBean;
import ercs.com.ercshouseresources.bean.FieldBean;
import ercs.com.ercshouseresources.bean.FieldCustomBean;
import ercs.com.ercshouseresources.bean.FieldCustomContentBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/6/28.
 * 外勤统计的子类适配器
 */

public class FieldItemAdapter extends ViewHolderAdapter<FieldCustomContentBean> {
    private Activity context;

    public FieldItemAdapter(Activity context, List<FieldCustomContentBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, FieldCustomContentBean dataBean, int position) {
        return inflate(R.layout.adapter_fielditem);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, final FieldCustomContentBean dataBean, final int position) {
        holder.setText(R.id.tv_hour, dataBean.getHour());
        holder.setText(R.id.tv_address,dataBean.getAddress());
        holder.setText(R.id.tv_content, dataBean.getContent());
        GlideUtil.loadCircleImage(NetHelper.URL + dataBean.getPath(), (ImageView) holder.getView(R.id.iv_content));
        holder.getView(R.id.iv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoViewActivity.start(context, NetHelper.URL + dataBean.getPath());
            }
        });
    }
}
