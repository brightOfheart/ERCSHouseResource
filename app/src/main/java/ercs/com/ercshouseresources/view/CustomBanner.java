package ercs.com.ercshouseresources.view;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.DisplayUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/7/18.
 */

public class CustomBanner {
    private XBanner xBanner;
    private Context context;
    private List<String> imgesUrl;
    public CustomBanner(Context context, XBanner xBanner,List<String> imgesUrl) {
        this.xBanner=xBanner;
        this.context=context;
        this.imgesUrl=imgesUrl;
        initview();
        setbanner();
    }

    /**
     * 设置图片展示View的宽高比为屏幕像素的2:1
     */
    private void initview()
    {
        xBanner.setLayoutParams(new LinearLayout.LayoutParams(DisplayUtil.getWidthPixels(context), DisplayUtil.getWidthPixels(context)/2));
    }
    /**
     * 设置首页广告页
     */
    private void setbanner() {
        xBanner.setData(imgesUrl, null);//第二个参数为提示文字资源集合
        xBanner.setPageTransformer(Transformer.Accordion);//图片滑动的方式Accordion
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {//设置显示的图片内容
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                Glide.with(context).load(imgesUrl.get(position)).into((ImageView) view);
                GlideUtil.loadImage(context,imgesUrl.get(position),(ImageView) view, R.mipmap.ic_launcher,R.mipmap.ic_launcher);
            }
        });
        xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {

            @Override

            public void onItemClick(XBanner banner, int position) {

//                Toast.makeText(context, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();

            }

        });
    }
}
