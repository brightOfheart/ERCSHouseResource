package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * 佣金说明
 * Created by Administrator on 2017/7/27.
 */

public class CommissionExplainBean {


    /**
     * Type : 1
     * Content : 成功
     * Data : {"BuildingsBrokerageGroupList":[],"ModifyBuildingBrokerageLogList":[{"BuildingID":2,"StaffName":"系统管理员","SellingBrokerage":"8000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:39:58","Id":3},{"BuildingID":2,"StaffName":"系统管理员","SellingBrokerage":"8000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:39:58","Id":4},{"BuildingID":2,"StaffName":"系统管理员","SellingBrokerage":"8000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:39:58","Id":5},{"BuildingID":2,"StaffName":"系统管理员","SellingBrokerage":"8000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:39:58","Id":6},{"BuildingID":2,"StaffName":"系统管理员","SellingBrokerage":"8000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:39:58","Id":7},{"BuildingID":2,"StaffName":"系统管理员","SellingBrokerage":"8000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:39:58","Id":8},{"BuildingID":2,"StaffName":"系统管理员","SellingBrokerage":"8000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:39:58","Id":9},{"BuildingID":2,"StaffName":"系统管理员","SellingBrokerage":"8000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:39:58","Id":10}]}
     */

    private int Type;
    private String Content;
    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private List<?> BuildingsBrokerageGroupList;
        private List<ModifyBuildingBrokerageLogListBean> ModifyBuildingBrokerageLogList;

        public List<?> getBuildingsBrokerageGroupList() {
            return BuildingsBrokerageGroupList;
        }

        public void setBuildingsBrokerageGroupList(List<?> BuildingsBrokerageGroupList) {
            this.BuildingsBrokerageGroupList = BuildingsBrokerageGroupList;
        }

        public List<ModifyBuildingBrokerageLogListBean> getModifyBuildingBrokerageLogList() {
            return ModifyBuildingBrokerageLogList;
        }

        public void setModifyBuildingBrokerageLogList(List<ModifyBuildingBrokerageLogListBean> ModifyBuildingBrokerageLogList) {
            this.ModifyBuildingBrokerageLogList = ModifyBuildingBrokerageLogList;
        }

        public static class ModifyBuildingBrokerageLogListBean {
            /**
             * BuildingID : 2
             * StaffName : 系统管理员
             * SellingBrokerage : 8000
             * LeaseBrokerage :
             * BandSawBrokerage : 10
             * IsLocked : false
             * IsDeleted : false
             * CreatedTime : 2017-07-26T09:39:58
             * Id : 3
             */

            private int BuildingID;
            private String StaffName;
            private String SellingBrokerage;
            private String LeaseBrokerage;
            private String BandSawBrokerage;
            private boolean IsLocked;
            private boolean IsDeleted;
            private String CreatedTime;
            private int Id;

            public int getBuildingID() {
                return BuildingID;
            }

            public void setBuildingID(int BuildingID) {
                this.BuildingID = BuildingID;
            }

            public String getStaffName() {
                return StaffName;
            }

            public void setStaffName(String StaffName) {
                this.StaffName = StaffName;
            }

            public String getSellingBrokerage() {
                return SellingBrokerage;
            }

            public void setSellingBrokerage(String SellingBrokerage) {
                this.SellingBrokerage = SellingBrokerage;
            }

            public String getLeaseBrokerage() {
                return LeaseBrokerage;
            }

            public void setLeaseBrokerage(String LeaseBrokerage) {
                this.LeaseBrokerage = LeaseBrokerage;
            }

            public String getBandSawBrokerage() {
                return BandSawBrokerage;
            }

            public void setBandSawBrokerage(String BandSawBrokerage) {
                this.BandSawBrokerage = BandSawBrokerage;
            }

            public boolean isIsLocked() {
                return IsLocked;
            }

            public void setIsLocked(boolean IsLocked) {
                this.IsLocked = IsLocked;
            }

            public boolean isIsDeleted() {
                return IsDeleted;
            }

            public void setIsDeleted(boolean IsDeleted) {
                this.IsDeleted = IsDeleted;
            }

            public String getCreatedTime() {
                return CreatedTime;
            }

            public void setCreatedTime(String CreatedTime) {
                this.CreatedTime = CreatedTime;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }
        }
    }
}
