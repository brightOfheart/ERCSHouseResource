package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.CityAcvitity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.ChoseCityBean;
import ercs.com.ercshouseresources.bean.CityBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;


/**
 * Created by Administrator on 2017/8/31.
 */

public class CityAdapter extends ViewHolderRecyclerAdapter<CityBean.DataBean> {
    private Context context;
    private Activity activity;
    private SPUtil spUtil;

    public CityAdapter(Activity activity, Context context, List<CityBean.DataBean> listData) {
        super(context, listData);
        this.context = context;
        this.activity = activity;
        spUtil = new SPUtil(context, "fileName");
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.item_city);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final CityBean.DataBean datas, final int position) {
        holder.setText(R.id.tv_city, datas.getCityName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final LoadingDialog dialog = new LoadingDialog(activity, 0);
                dialog.show();
                NetHelperNew.getChoseCityList(datas.getCityID(), new HttpUtils.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        dialog.dismiss();
                        final ChoseCityBean cityBean = MyGson.getInstance().fromJson(data, ChoseCityBean.class);
                        if (cityBean.getType().equals("1")) {
                            List<String> list = cityBean.getData().getTabs();
                            String str = "";
                            for (int i = 0; i < list.size(); i++) {
                                str = str + list.get(i) + ",";
                            }
                            saveCitys(cityBean.getData().getCityName(), cityBean.getData().getCityID(), str);
                            activity.finish();

                        } else {
                            ToastUtil.showToast(context, cityBean.getContent());
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        dialog.dismiss();
                        ToastUtil.showToast(context, msg);
                    }
                });
            }
        });
    }

    private void saveCitys(String city, String cityid, String tabs) {
        spUtil.putString(BaseApplication.CITY, city);
        spUtil.putString(BaseApplication.CITYID, cityid);
        spUtil.putString(BaseApplication.TABS, tabs);
    }
}
