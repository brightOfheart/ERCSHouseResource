package ercs.com.ercshouseresources.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;
import java.util.List;
import ercs.com.ercshouseresources.R;


/**
 * Created by Administrator on 2017/7/24.
 */

public class ProConAdapter extends ViewHolderRecyclerAdapter<String> {
    private Context context;
    private Activity activity;

    public ProConAdapter(Activity activity, Context context, List<String> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.item_procon);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, String datas, final int position) {
//        holder.setText(R.id.tv_name, datas.getName());
//        GlideUtil.loadCircleImage(NetHelper.URL + datas.getPhotoPath(), (ImageView) holder.getView(R.id.iv_photo));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                OtherUitl.callPage(context, datas.getPhone());
////                BottomDialog bottomDialog = new BottomDialog(activity, new BottomDialog.OnSelectListener() {
////
////                    @Override
////                    public void callphone() {//拨打电话
////                        OtherUitl.callPage(context, datas.getPhone());
////                    }
////
////                    @Override
////                    public void selectattendance() {//查看考勤
////
////                    }
////
////                    @Override
////                    public void selectfield() {//查看外勤
////
////                    }
////                }, datas.getName());
////                bottomDialog.show();
//            }
//        });
    }
}
