package ercs.com.ercshouseresources.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;
import java.util.List;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.SchedulingBean;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/6/26.
 * 排班管理子类的适配器
 */

public class SchedulingAdapterChildItemAdapter  extends ViewHolderAdapter<SchedulingBean.DataBean.UDataBean> {
    private Context context;
    public SchedulingAdapterChildItemAdapter(Context context, List<SchedulingBean.DataBean.UDataBean> listData) {
        super(context, listData);
        this.context=context;
    }



    @Override
    public View buildConvertView(LayoutInflater layoutInflater, SchedulingBean.DataBean.UDataBean uDataBean, int position) {
        return inflate(R.layout.adapter_schdulingadapterchilditemadapter) ;
    }

    @Override
    public void bindViewDatas(ViewHolder holder, SchedulingBean.DataBean.UDataBean uDataBean, final int position) {
        GlideUtil.loadCircleImage(NetHelper.URL + uDataBean.getPhotoPath(), (ImageView) holder.getView(R.id.iv_photo));
        holder.setText(R.id.tv_name, uDataBean.getUserName());
        holder.setOnClickListener(R.id.iv_phoneNum, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
