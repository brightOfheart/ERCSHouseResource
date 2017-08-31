package ercs.com.ercshouseresources.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;
import java.util.List;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.NewHouseDetailBean;
import ercs.com.ercshouseresources.util.OtherUitl;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ProConAdapter extends ViewHolderAdapter<NewHouseDetailBean.DataBean.PropertyConsultantList> {
    private Context context;
    private Activity activity;

    public ProConAdapter(Activity activity, Context context, List<NewHouseDetailBean.DataBean.PropertyConsultantList> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, NewHouseDetailBean.DataBean.PropertyConsultantList dataBean, int position) {
        return inflate(R.layout.item_procon);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final NewHouseDetailBean.DataBean.PropertyConsultantList datas, final int position) {
        holder.setText(R.id.tv_x, datas.getName().substring(0, 1));
        holder.setText(R.id.tv_name, datas.getName() + "  " + datas.getPhone());
        holder.setText(R.id.tv_type, datas.getDuties());
        if (datas.getSex().equals("1"))//男
        {

            holder.setBackgroundResource(R.id.tv_x, R.drawable.circle_blue_bg);
        } else//女
        {
            holder.setBackgroundResource(R.id.tv_x, R.drawable.circle_powder_bg);
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtherUitl.callPage(context, datas.getPhone());
            }
        });
    }
}
