package ercs.com.ercshouseresources.activity.financial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.king.base.adapter.ViewHolderRecyclerAdapter;
import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.bean.FinancialOrderBean;


/**
 * Created by Administrator on 2017/8/18.
 */

public class FinancialOrderAdapter extends ViewHolderRecyclerAdapter<FinancialOrderBean.DataBean> {
    private Context context;
    private Activity activity;
    private String title;
    private String content;

    public FinancialOrderAdapter(Activity activity, Context context, List<FinancialOrderBean.DataBean> Data) {
        super(context, Data);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater, int viewType) {
        return inflate(R.layout.adapter_financialorder);
    }

    @Override
    public void bindViewDatas(final ViewHolder holder, final FinancialOrderBean.DataBean datas, final int position) {
        holder.setText(R.id.tv_salename, datas.getHouseUserName());
        holder.setText(R.id.tv_salephone, datas.getHouseUserPhone());
        holder.setText(R.id.tv_buyname, datas.getCustomerUserName());
        holder.setText(R.id.tv_buyphone, datas.getCustomerUserPhone());
        holder.setText(R.id.tv_type, datas.getFinanceType());
        holder.setText(R.id.tv_money, datas.getFree());
        holder.setText(R.id.tv_name, datas.getTransactStaffName());
        holder.setText(R.id.tv_phone, datas.getTransactStaffPhone());
        holder.setText(R.id.tv_jname, datas.getUserName());
        holder.setText(R.id.tv_jphone, datas.getUserPhone());
        holder.setText(R.id.tv_time, datas.getStateName());
        holder.setText(R.id.tv_state, datas.getFilingTime());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FinancialOrderReportDetailActivity.class);
                intent.putExtra("Id", datas.getId() + "");
                intent.putExtra("title", datas.getFinanceType());
                context.startActivity(intent);
            }
        });
    }


}
