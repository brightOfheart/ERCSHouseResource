package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 * 重新封装的外勤统计的BEEAN
 */

public class FieldCustomBean {
    private String time;
    private String count;
    private FieldCustomContentBean contentBean;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public FieldCustomContentBean getContentBean() {
        return contentBean;
    }

    public void setContentBean(FieldCustomContentBean contentBean) {
        this.contentBean = contentBean;
    }
}
