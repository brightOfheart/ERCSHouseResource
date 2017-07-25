package ercs.com.ercshouseresources.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.service.NewHouseDetailActivity;

/**
 * Created by Administrator on 2017/7/25.
 */

public class NewBuildingAdapter extends ViewHolderRecyclerAdapter<String> {
    public NewBuildingAdapter(Context context, List<String> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return layoutInflater.inflate(R.layout.item_newbuilding,null);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, String s, int position) {

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, NewHouseDetailActivity.class));
            }
        });
    }
}
