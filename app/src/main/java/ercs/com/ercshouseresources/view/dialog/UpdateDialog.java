package ercs.com.ercshouseresources.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.UpdateActivity;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.DisplayUtil;

/**
 * Created by Administrator on 2017/9/4.
 */

public class UpdateDialog extends Dialog {
    private Button btn_cancel, btn_update;
    private Activity context;
    private TextView tv_content;
    private String content;
    private String url;

    public UpdateDialog(Activity context, int theme, String content, String url) {
        super(context, theme);
        this.context = context;
        this.content = content;
        this.url = url;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_update, null);
        setContentView(view, new ViewGroup.LayoutParams(DisplayUtil.getWidthPixels(context)/2,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_update = (Button) view.findViewById(R.id.btn_update);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_content.setText(content);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();


            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                UpdateActivity.start(context, url);
            }
        });

    }


}
