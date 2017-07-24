package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/7/24.
 * 服务适配器
 */

public class ServiceAdapter extends ViewHolderAdapter<String> {
    private Context context;

    public ServiceAdapter(Context context, List<String> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, String string, int position) {
        return inflate(R.layout.adapter_service);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, String string, final int position) {
//        holder.setText(R.id.tv_date, string);

    }
}
