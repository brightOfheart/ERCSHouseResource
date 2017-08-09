package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 * 低价房的列表BEAN
 */

public class CheapRoomListBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String Id;
        private String BuildingName;
        private String CostPrice;
        private String Brokerage;
        private String CommissionAccount;
        private String ImagePath;
        private String LowPrice;
        private String Area;
        private String IsLoan;
        private String Poundage;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getBuildingName() {
            return BuildingName;
        }

        public void setBuildingName(String buildingName) {
            BuildingName = buildingName;
        }

        public String getCostPrice() {
            return CostPrice;
        }

        public void setCostPrice(String costPrice) {
            CostPrice = costPrice;
        }

        public String getBrokerage() {
            return Brokerage;
        }

        public void setBrokerage(String brokerage) {
            Brokerage = brokerage;
        }

        public String getCommissionAccount() {
            return CommissionAccount;
        }

        public void setCommissionAccount(String commissionAccount) {
            CommissionAccount = commissionAccount;
        }

        public String getLowPrice() {
            return LowPrice;
        }

        public void setLowPrice(String lowPrice) {
            LowPrice = lowPrice;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String imagePath) {
            ImagePath = imagePath;
        }

        public String getArea() {
            return Area;
        }

        public void setArea(String area) {
            Area = area;
        }

        public String getIsLoan() {
            return IsLoan;
        }

        public void setIsLoan(String isLoan) {
            IsLoan = isLoan;
        }

        public String getPoundage() {
            return Poundage;
        }

        public void setPoundage(String poundage) {
            Poundage = poundage;
        }
    }
}
