package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */

public class FinancialOrderBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String StateName;
        private String FinanceType;
        private String HouseUserName;
        private String HouseUserPhone;
        private String TransactStaffName;
        private String TransactStaffPhone;
        private String Free;
        private String Id;
        private String UserName;
        private String UserPhone;
        private String FilingTime;

        public String getStateName() {
            return StateName;
        }

        public void setStateName(String stateName) {
            StateName = stateName;
        }

        public String getFinanceType() {
            return FinanceType;
        }

        public void setFinanceType(String financeType) {
            FinanceType = financeType;
        }

        public String getHouseUserName() {
            return HouseUserName;
        }

        public void setHouseUserName(String houseUserName) {
            HouseUserName = houseUserName;
        }

        public String getHouseUserPhone() {
            return HouseUserPhone;
        }

        public void setHouseUserPhone(String houseUserPhone) {
            HouseUserPhone = houseUserPhone;
        }

        public String getTransactStaffName() {
            return TransactStaffName;
        }

        public void setTransactStaffName(String transactStaffName) {
            TransactStaffName = transactStaffName;
        }

        public String getTransactStaffPhone() {
            return TransactStaffPhone;
        }

        public void setTransactStaffPhone(String transactStaffPhone) {
            TransactStaffPhone = transactStaffPhone;
        }

        public String getFree() {
            return Free;
        }

        public void setFree(String free) {
            Free = free;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getUserPhone() {
            return UserPhone;
        }

        public void setUserPhone(String userPhone) {
            UserPhone = userPhone;
        }

        public String getFilingTime() {
            return FilingTime;
        }

        public void setFilingTime(String filingTime) {
            FilingTime = filingTime;
        }
    }
}
