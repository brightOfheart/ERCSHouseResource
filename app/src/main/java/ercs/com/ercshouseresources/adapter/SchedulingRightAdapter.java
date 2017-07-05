package ercs.com.ercshouseresources.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;
import java.util.List;
import ercs.com.ercshouseresources.R;
/**
 * Created by Administrator on 2017/6/26.
 * 排班管理右侧点击事件
 */

public class SchedulingRightAdapter extends ViewHolderAdapter<String> {
    private Context context;

    public SchedulingRightAdapter(Context context, List<String> listData) {
        super(context, listData);
        this.context = context;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, String string, int position) {
        return inflate(R.layout.adapter_schedulingrightadapter);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, String string, final int position) {
        holder.setText(R.id.tv_date, string);

    }
}
