package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */

public class Ren_OrderBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String Id;
        private String CustomerName;
        private String CustomerPhone;
        private String CustomerAddress;
        private String DecorationCompanyName;
        private String UserName;
        private String Area;
        private String State;
        private String OperTime;
        private String Remark;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String customerName) {
            CustomerName = customerName;
        }

        public String getCustomerPhone() {
            return CustomerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            CustomerPhone = customerPhone;
        }

        public String getCustomerAddress() {
            return CustomerAddress;
        }

        public void setCustomerAddress(String customerAddress) {
            CustomerAddress = customerAddress;
        }

        public String getDecorationCompanyName() {
            return DecorationCompanyName;
        }

        public void setDecorationCompanyName(String decorationCompanyName) {
            DecorationCompanyName = decorationCompanyName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getArea() {
            return Area;
        }

        public void setArea(String area) {
            Area = area;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getOperTime() {
            return OperTime;
        }

        public void setOperTime(String operTime) {
            OperTime = operTime;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }
    }
}
