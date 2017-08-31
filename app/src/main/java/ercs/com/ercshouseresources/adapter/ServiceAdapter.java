package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.ServiceBean;

/**
 * Created by Administrator on 2017/7/24.
 * 服务适配器
 */

public class ServiceAdapter extends ViewHolderAdapter<ServiceBean> {
    private Context context;

    public ServiceAdapter(Context context, List<ServiceBean> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, ServiceBean string, int position) {
        return inflate(R.layout.adapter_service);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, ServiceBean bean, final int position) {
        holder.setText(R.id.tv_title, bean.getTitle());
        holder.setImageResource(R.id.iv_pic, bean.getPic());
    }
}
