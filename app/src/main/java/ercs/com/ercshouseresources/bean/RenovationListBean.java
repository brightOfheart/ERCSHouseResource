package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 * 封装装修列表的Bean
 */

public class RenovationListBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String Id;
        private String Name;
        private String CommissionAccount;
        private String AwardDescription;
        private String CreatedTime;
        private String ImagePath;
        private String Brokerage;
        private List<AdListBean> AdList;

        public String getBrokerage() {
            return Brokerage;
        }

        public void setBrokerage(String brokerage) {
            Brokerage = brokerage;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getCommissionAccount() {
            return CommissionAccount;
        }

        public void setCommissionAccount(String commissionAccount) {
            CommissionAccount = commissionAccount;
        }

        public String getAwardDescription() {
            return AwardDescription;
        }

        public void setAwardDescription(String awardDescription) {
            AwardDescription = awardDescription;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String createdTime) {
            CreatedTime = createdTime;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String imagePath) {
            ImagePath = imagePath;
        }

        public List<AdListBean> getAdList() {
            return AdList;
        }

        public void setAdList(List<AdListBean> adList) {
            AdList = adList;
        }

        public class AdListBean {
            private String Text;

            public String getText() {
                return Text;
            }

            public void setText(String text) {
                Text = text;
            }
        }
    }
}
