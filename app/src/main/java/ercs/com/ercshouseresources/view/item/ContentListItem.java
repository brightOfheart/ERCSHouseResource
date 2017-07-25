package ercs.com.ercshouseresources.view.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ContentListItem extends RelativeLayout {
    private LinearLayout linearLayout;

    //    @BindView(R.id.tv_time)13591734
//    TextView tv_time;
//    @BindView(R.id.litviews)
//    NoScrollListView litview;
    public ContentListItem(Context context) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_contentlist, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);

    }

}
