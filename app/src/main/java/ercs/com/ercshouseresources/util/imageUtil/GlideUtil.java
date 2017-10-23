package ercs.com.ercshouseresources.util.imageUtil;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import static ercs.com.ercshouseresources.base.BaseApplication.context;

/**
 * Created by Administrator on 2017/3/17.
 */

public class GlideUtil {
    /**
     * 加载网络图片
     *
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 带占位图的加载
     *
     * @param url
     * @param imageView
     * @param defultImg
     * @param errorImg
     */
    public static void loadImage(Context context, String url, ImageView imageView, int defultImg, int errorImg) {
        Glide.with(context)
                .load(url)
                .placeholder(defultImg)
                .error(errorImg)
                .into(imageView);
    }
    public static void loadImages(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
    public static void loadImagess(Context context, String url, ImageView imageView,int errorImg) {
        Glide.with(context)
                .load(url)
                .error(errorImg)
                .into(imageView);
    }
    /**
     * 加载圆角图片
     *
     * @param url
     * @param imageView
     * @param dp
     */
    public static void loadRoundCornerImage(Context context, String url, ImageView imageView, int dp) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .transform(new GRoundTransform(context, dp))
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .transform(new GCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载本地圆形图片
     *
     * @param id
     * @param imageView
     */
    public static void loadCircleImage(int id, ImageView imageView) {
        Glide.with(context)
                .load(id)
                .centerCrop()
                .transform(new GCircleTransform(context))
                .into(imageView);
    }

    public static void loadCircleImage(byte[] id, ImageView imageView, int errorImg) {
        Glide.with(context)
                .load(id)
                .centerCrop().error(errorImg)
                .transform(new GCircleTransform(context))
                .into(imageView);
    }

}
