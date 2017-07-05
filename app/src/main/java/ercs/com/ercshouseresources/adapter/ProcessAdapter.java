package ercs.com.ercshouseresources.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;
import java.util.List;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.ProcessBean;


/**
 * Created by Administrator on 2017/6/23.
 * 流程审批适配器
 */

public class ProcessAdapter extends ViewHolderRecyclerAdapter<ProcessBean.DataBean> {
    private Context context;
    private Activity activity;
    private LinearLayout ly_rest, ly_out, ly_retroactive;

    public ProcessAdapter(Activity activity, Context context, List<ProcessBean.DataBean> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.item_process);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, ProcessBean.DataBean datas, final int position) {
//        if (ly_rest == null)
//            ly_rest = holder.getView(R.id.ly_rest);
//        if (ly_out == null)
//            ly_out = holder.getView(R.id.ly_out);
//        if (ly_retroactive == null)
//            ly_retroactive = holder.getView(R.id.ly_retroactive);
//        if (datas.getLeaveType().equals("1")) {
//            ly_rest.addView(new ProcessAdapterChildItem(context,datas));
//        } else if (datas.getLeaveType().equals("2")) {
//            ly_out.addView(new ProcessAdapterChildItem(context,datas));
//        } else if (datas.getLeaveType().equals("3")) {
//            ly_retroactive.addView(new ProcessAdapterChildItem(context,datas));
//        }
    }
}
