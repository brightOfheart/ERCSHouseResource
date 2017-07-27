package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 * 新房详情页的BEAN
 */

public class NewHouseDetailBean extends BaseBean {


    public class DataBean
    {
        private String Name;
        private String AreaName;
        private String SellingPrice;
        private String SellingBrokerage;
        private String AwardDescription;
        private String CommissionAccount;
        private String Address;
        private List<HouseTypeListBean> HouseTypeList;
        public class HouseTypeListBean
        {
            private String a;
            private String b;
            private String c;
        }
    }

}
