package ercs.com.ercshouseresources.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;
import java.util.List;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.ClerkBean;
import ercs.com.ercshouseresources.util.OtherUitl;


/**
 * Created by Administrator on 2017/6/22.
 * 职员列表的适配器
 */

public class ClerkAdapter extends ViewHolderRecyclerAdapter<ClerkBean.Datas> {
    private Context context;
    private Activity activity;

    public ClerkAdapter(Activity activity, Context context, List<ClerkBean.Datas> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.item_clerk);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final ClerkBean.Datas datas, final int position) {
        holder.setText(R.id.tv_name, datas.getName());
        if (datas.getName().length() > 0)
            holder.setText(R.id.tv_title, datas.getName().substring(0, 1));
        if (datas.getIsShopkeeper().equals("false")) {

        } else {
            holder.setText(R.id.tv_state, "店");
        }

        if (datas.getSex().equals("1")) {
            //男
            holder.getView(R.id.tv_title).setBackgroundResource(R.drawable.circle_blue_bg);

        } else {
            //女
            holder.getView(R.id.tv_title).setBackgroundResource(R.drawable.circle_powder_bg);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtherUitl.callPage(context, datas.getPhone());

            }
        });
    }
}