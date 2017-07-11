package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;
import java.util.List;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.AllProcessBean;
import ercs.com.ercshouseresources.bean.ClerkBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/7/11.
 * 员工外勤记录的Adapter
 */

public class MemberOutAssessAdapter extends ViewHolderAdapter<ClerkBean.Datas> {
    private Context context;

    public MemberOutAssessAdapter(Context context, List<ClerkBean.Datas> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater,ClerkBean.Datas dataBean, int position) {
        return inflate(R.layout.adapter_memberoutassess);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, ClerkBean.Datas dataBean, final int position) {
        holder.setText(R.id.tv_name,dataBean.getName());
        GlideUtil.loadCircleImage(NetHelper.URL + dataBean.getPhotoPath(), (ImageView) holder.getView(R.id.iv_photo));
    }
}
