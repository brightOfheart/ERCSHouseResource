package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class OrderReportListBean {


    /**
     * Type : 1
     * Content : 成功
     * Data : [{"Id":6,"CustomerName":"100","CustomerPhone":"456","BuildingName":"阳光经典公寓","UserName":"张六","State":"已完成","OperTime":"17-07-29 09:52"},{"Id":7,"CustomerName":"vvv","CustomerPhone":"15352552554","BuildingName":"阳光经典公寓","UserName":"张六","State":"已完成","OperTime":"17-07-29 09:53"},{"Id":10,"CustomerName":"测试人","CustomerPhone":"15532485258","BuildingName":"唐轩中心","UserName":"张六","State":"已完成","OperTime":"17-07-29 04:09"},{"Id":5,"CustomerName":"测试题","CustomerPhone":"15245780957","BuildingName":"阳光经典公寓","UserName":"张六","State":"已完成","OperTime":"17-07-29 09:52"},{"Id":11,"CustomerName":"刚刚古古怪怪古古怪","CustomerPhone":"15898878854","BuildingName":"唐轩中心","UserName":"张六","State":"已完成","OperTime":"17-07-29 04:09"},{"Id":8,"CustomerName":"刚刚古古怪怪古古怪","CustomerPhone":"15898878854","BuildingName":"阳光经典公寓","UserName":"张六","State":"已完成","OperTime":"17-07-29 09:53"},{"Id":9,"CustomerName":"刚刚古古怪怪古古怪","CustomerPhone":"14258748096","BuildingName":"阳光经典公寓","UserName":"张六","State":"已完成","OperTime":"17-07-29 09:53"}]
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
         * Id : 6
         * CustomerName : 100
         * CustomerPhone : 456
         * BuildingName : 阳光经典公寓
         * UserName : 张六
         * State : 已完成
         * OperTime : 17-07-29 09:52
         */

        private int Id;
        private String CustomerName;
        private String CustomerPhone;
        private String BuildingName;
        private String UserName;
        private String State;
        private String OperTime;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public String getCustomerPhone() {
            return CustomerPhone;
        }

        public void setCustomerPhone(String CustomerPhone) {
            this.CustomerPhone = CustomerPhone;
        }

        public String getBuildingName() {
            return BuildingName;
        }

        public void setBuildingName(String BuildingName) {
            this.BuildingName = BuildingName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getOperTime() {
            return OperTime;
        }

        public void setOperTime(String OperTime) {
            this.OperTime = OperTime;
        }
    }
}
