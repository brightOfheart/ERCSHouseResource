package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * 新房列表bean
 * Created by Administrator on 2017/7/25.
 */

public class NewHouseListBean {


    /**
     * Type : 1
     * Content : 成功
     * Data : [{"Id":2,"Name":"唐轩中心","SellingPrice":"5000完","SellingBrokerage":"8000","CommissionAccount":"sdsadsad","CreatedTime":"2017-07-26T09:39:58.73","ImagePath":"\\Images\\BuildingsImages\\201707260946050812877.jpg"},{"Id":1,"Name":"阳光经典公寓","SellingPrice":"58","SellingBrokerage":"2000","CommissionAccount":null,"CreatedTime":"2017-07-26T09:38:41.433","ImagePath":"\\Images\\BuildingsImages\\201707261012053972839.png"}]
     */

    private int Type;
    private String Content;
    private List<DataBean> Data;

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 2
         * Name : 唐轩中心
         * SellingPrice : 5000完
         * SellingBrokerage : 8000
         * CommissionAccount : sdsadsad
         * CreatedTime : 2017-07-26T09:39:58.73
         * ImagePath : \Images\BuildingsImages\201707260946050812877.jpg
         */

        private int Id;
        private String Name;
        private String SellingPrice;
        private String SellingBrokerage;
        private String CommissionAccount;
        private String CreatedTime;
        private String ImagePath;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getSellingPrice() {
            return SellingPrice;
        }

        public void setSellingPrice(String SellingPrice) {
            this.SellingPrice = SellingPrice;
        }

        public String getSellingBrokerage() {
            return SellingBrokerage;
        }

        public void setSellingBrokerage(String SellingBrokerage) {
            this.SellingBrokerage = SellingBrokerage;
        }

        public String getCommissionAccount() {
            return CommissionAccount;
        }

        public void setCommissionAccount(String CommissionAccount) {
            this.CommissionAccount = CommissionAccount;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String CreatedTime) {
            this.CreatedTime = CreatedTime;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String ImagePath) {
            this.ImagePath = ImagePath;
        }
    }
}
