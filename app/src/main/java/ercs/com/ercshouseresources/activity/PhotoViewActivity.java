package ercs.com.ercshouseresources.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.process.ProcessContentAcvitity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/6/30.
 */

public class PhotoViewActivity extends BaseActivity {
    private PhotoView iv_photo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        iv_photo=(PhotoView)findViewById(R.id.iv_photo);
        GlideUtil.loadCircleImage(this, getUrl(), iv_photo);

    }

    /**
     * 页面跳转
     *
     * @param mActiivty
     * @param url
     */
    public static void start(Activity mActiivty, String url) {
        Intent intent = new Intent(mActiivty, PhotoViewActivity.class);
        intent.putExtra("url", url);
        mActiivty.startActivity(intent);
    }
    private String getUrl() {
        return getIntent().getStringExtra("url");
    }
}
