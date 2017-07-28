package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 * 新房详情页的BEAN
 */

public class NewHouseDetailBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private BaseInfoBean BaseInfo;
        private String AreaName;
        private List<HouseTypeListBean> HouseTypeList;


        public BaseInfoBean getBaseInfo() {
            return BaseInfo;
        }

        public void setBaseInfo(BaseInfoBean baseInfo) {
            BaseInfo = baseInfo;
        }

        public String getAreaName() {
            return AreaName;
        }

        public void setAreaName(String areaName) {
            AreaName = areaName;
        }

        public List<HouseTypeListBean> getHouseTypeList() {
            return HouseTypeList;
        }

        public void setHouseTypeList(List<HouseTypeListBean> houseTypeList) {
            HouseTypeList = houseTypeList;
        }



        public class BaseInfoBean {
            private String Name;
            private String SellingPrice;
            private String SellingBrokerage;
            private String AwardDescription;
            private String CommissionAccount;
            private String Address;




            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getSellingBrokerage() {
                return SellingBrokerage;
            }

            public void setSellingBrokerage(String sellingBrokerage) {
                SellingBrokerage = sellingBrokerage;
            }

            public String getSellingPrice() {
                return SellingPrice;
            }

            public void setSellingPrice(String sellingPrice) {
                SellingPrice = sellingPrice;
            }

            public String getAwardDescription() {
                return AwardDescription;
            }

            public void setAwardDescription(String awardDescription) {
                AwardDescription = awardDescription;
            }

            public String getCommissionAccount() {
                return CommissionAccount;
            }

            public void setCommissionAccount(String commissionAccount) {
                CommissionAccount = commissionAccount;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String address) {
                Address = address;
            }
        }

        public class HouseTypeListBean {
            private String ImagePath;
            private String Name;
            private String Price;
            private String Orientations;
            private String DecorationCondition;
            private List<String> HouseTypeTagList;

            public List<String> getHouseTypeTagList() {
                return HouseTypeTagList;
            }

            public void setHouseTypeTagList(List<String> houseTypeTagList) {
                HouseTypeTagList = houseTypeTagList;
            }

            public String getImagePath() {
                return ImagePath;
            }

            public void setImagePath(String imagePath) {
                ImagePath = imagePath;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String price) {
                Price = price;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getOrientations() {
                return Orientations;
            }

            public void setOrientations(String orientations) {
                Orientations = orientations;
            }

            public String getDecorationCondition() {
                return DecorationCondition;
            }

            public void setDecorationCondition(String decorationCondition) {
                DecorationCondition = decorationCondition;
            }
        }

    }

}
