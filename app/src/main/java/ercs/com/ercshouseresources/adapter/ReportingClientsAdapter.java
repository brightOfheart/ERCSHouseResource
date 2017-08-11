package ercs.com.ercshouseresources.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.CustomersListBean;
import ercs.com.ercshouseresources.view.dialog.CheapConfirmationClientsDialog;
import ercs.com.ercshouseresources.view.dialog.ConfirmationClientsDialog;

/**
 * 报备客户列表
 * Created by Administrator on 2017/7/24.
 */

public class ReportingClientsAdapter extends ViewHolderRecyclerAdapter<CustomersListBean.DataBean> {
        private String BuildingID;
        public ReportingClientsAdapter(Context context, List<CustomersListBean.DataBean> listData,String BuildingID) {
            super(context, listData);
            this.BuildingID=BuildingID;
        }

        @Override
        public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
            return layoutInflater.inflate(R.layout.item_reporting_clients,null);
        }

        @Override
        public void bindViewDatas(ViewHolder holder, final CustomersListBean.DataBean dataBean, int position) {

            holder.setText(R.id.tv_name,dataBean.getName());
            if (dataBean.getPhoneList().get(0)!=null)
                holder.setText(R.id.tv_tel,dataBean.getPhoneList().get(0).getPhone());

            if (!"".equals(dataBean.getName()))
                holder.setText(R.id.tv_title,dataBean.getName().substring(0,1));

            if (dataBean.getSex()==0)
            {
                //男
                holder.getView(R.id.tv_title).setBackgroundResource(R.drawable.circle_blue_bg);

            }else
            {
                //女
                holder.getView(R.id.tv_title).setBackgroundResource(R.drawable.circle_powder_bg);
            }

            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ConfirmationClientsDialog confirmationClientsDialog = new ConfirmationClientsDialog((Activity) context, dataBean.getName(), dataBean.getPhoneList(),BuildingID,dataBean.getId()+"");
                    confirmationClientsDialog.show();
                }
            });
        }
}
