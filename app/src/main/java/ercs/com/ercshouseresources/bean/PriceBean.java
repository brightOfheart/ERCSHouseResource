package ercs.com.ercshouseresources.bean;

/**
 * 价格
 * Created by Administrator on 2017/7/18.
 */

public class PriceBean {

    private String title;
    private String minprice;//最低价格
    private String maxprice;///最高价格

    public PriceBean(String title, String minprice, String maxprice) {
        this.title = title;
        this.minprice = minprice;
        this.maxprice = maxprice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(String maxprice) {
        this.maxprice = maxprice;
    }

    public String getMinprice() {
        return minprice;
    }

    public void setMinprice(String minprice) {
        this.minprice = minprice;
    }
}
