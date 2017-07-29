package ercs.com.ercshouseresources.bean;

/**
 * 报备订单详情
 * Created by Administrator on 2017/7/29.
 */

public class ReportOrderDetailBean {

    /**
     * Type : 1
     * Content : 成功
     * Data : {"BuildingID":1,"BuildingName":"阳光经典公寓","IntermediaryID":1,"IntermediaryName":"IT不动产","UserID":1,"UserName":"张三","UserPhone":"13888888888","CustomerID":1,"CustomerName":"李磊","CustomerPhone":"13888888888","FilingTime":"2017-07-28T16:49:11.42","FilingAuditingTime":"2017-07-28T16:49:11.42","FilingAuditingStaffID":0,"FilingAuditingRemark":null,"BandSawTime":null,"BandSawStaffID":0,"BandSawRemark":null,"SubscribeTime":"2017-07-28T16:49:11.42","SubscribeStaffID":0,"SubscribeReamrk":null,"BuildingsBrokerageGroupID":0,"BuildingsBrokerageGroupName":null,"Brokerage":null,"TotalPrice":0,"Area":0,"Address":null,"BargainMoney":0,"DownPaymentTime":"2017-07-28T16:49:11.42","DownPaymentStaffID":0,"DownPaymentMoney":0,"DownPaymentRemark":null,"PutOnRecordTime":"2017-07-28T16:49:11.42","PutOnRecordStaffID":0,"PutOnRecordRemark":null,"CompleteTime":"2017-07-28T16:49:11.42","CompleteStaffID":0,"CompleteRemark":null,"ReturnHouseTime":"2017-07-28T16:49:11.42","ReturnHouseStaffID":0,"ReturnHouseRemark":null,"ReturnHouseAuditingTime":"2017-07-28T16:49:11.42","ReturnHouseAuditingStaffID":0,"ReturnHouseAuditingRemark":null,"State":0,"Remark":null,"StarTarget":null,"StarTargetRemark":null,"PropertyConsultantName":null,"OriginStaffID":0,"SellStaffID":0,"AgentID":0,"Id":2}
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
        /**
         * BuildingID : 1
         * BuildingName : 阳光经典公寓
         * IntermediaryID : 1
         * IntermediaryName : IT不动产
         * UserID : 1
         * UserName : 张三
         * UserPhone : 13888888888
         * CustomerID : 1
         * CustomerName : 李磊
         * CustomerPhone : 13888888888
         * FilingTime : 2017-07-28T16:49:11.42
         * FilingAuditingTime : 2017-07-28T16:49:11.42
         * FilingAuditingStaffID : 0
         * FilingAuditingRemark : null
         * BandSawTime : null
         * BandSawStaffID : 0
         * BandSawRemark : null
         * SubscribeTime : 2017-07-28T16:49:11.42
         * SubscribeStaffID : 0
         * SubscribeReamrk : null
         * BuildingsBrokerageGroupID : 0
         * BuildingsBrokerageGroupName : null
         * Brokerage : null
         * TotalPrice : 0.0
         * Area : 0.0
         * Address : null
         * BargainMoney : 0.0
         * DownPaymentTime : 2017-07-28T16:49:11.42
         * DownPaymentStaffID : 0
         * DownPaymentMoney : 0.0
         * DownPaymentRemark : null
         * PutOnRecordTime : 2017-07-28T16:49:11.42
         * PutOnRecordStaffID : 0
         * PutOnRecordRemark : null
         * CompleteTime : 2017-07-28T16:49:11.42
         * CompleteStaffID : 0
         * CompleteRemark : null
         * ReturnHouseTime : 2017-07-28T16:49:11.42
         * ReturnHouseStaffID : 0
         * ReturnHouseRemark : null
         * ReturnHouseAuditingTime : 2017-07-28T16:49:11.42
         * ReturnHouseAuditingStaffID : 0
         * ReturnHouseAuditingRemark : null
         * State : 0
         * Remark : null
         * StarTarget : null
         * StarTargetRemark : null
         * PropertyConsultantName : null
         * OriginStaffID : 0
         * SellStaffID : 0
         * AgentID : 0
         * Id : 2
         */

        private int BuildingID;
        private String BuildingName;
        private int IntermediaryID;
        private String IntermediaryName;
        private int UserID;
        private String UserName;
        private String UserPhone;
        private int CustomerID;
        private String CustomerName;
        private String CustomerPhone;
        private String FilingTime;
        private String FilingAuditingTime;
        private int FilingAuditingStaffID;
        private Object FilingAuditingRemark;
        private Object BandSawTime;
        private int BandSawStaffID;
        private Object BandSawRemark;
        private String SubscribeTime;
        private int SubscribeStaffID;
        private Object SubscribeReamrk;
        private int BuildingsBrokerageGroupID;
        private Object BuildingsBrokerageGroupName;
        private Object Brokerage;
        private double TotalPrice;
        private double Area;
        private Object Address;
        private double BargainMoney;
        private String DownPaymentTime;
        private int DownPaymentStaffID;
        private double DownPaymentMoney;
        private Object DownPaymentRemark;
        private String PutOnRecordTime;
        private int PutOnRecordStaffID;
        private Object PutOnRecordRemark;
        private String CompleteTime;
        private int CompleteStaffID;
        private Object CompleteRemark;
        private String ReturnHouseTime;
        private int ReturnHouseStaffID;
        private Object ReturnHouseRemark;
        private String ReturnHouseAuditingTime;
        private int ReturnHouseAuditingStaffID;
        private Object ReturnHouseAuditingRemark;
        private int State;
        private Object Remark;
        private Object StarTarget;
        private Object StarTargetRemark;
        private Object PropertyConsultantName;
        private int OriginStaffID;
        private int SellStaffID;
        private int AgentID;
        private int Id;

        public int getBuildingID() {
            return BuildingID;
        }

        public void setBuildingID(int BuildingID) {
            this.BuildingID = BuildingID;
        }

        public String getBuildingName() {
            return BuildingName;
        }

        public void setBuildingName(String BuildingName) {
            this.BuildingName = BuildingName;
        }

        public int getIntermediaryID() {
            return IntermediaryID;
        }

        public void setIntermediaryID(int IntermediaryID) {
            this.IntermediaryID = IntermediaryID;
        }

        public String getIntermediaryName() {
            return IntermediaryName;
        }

        public void setIntermediaryName(String IntermediaryName) {
            this.IntermediaryName = IntermediaryName;
        }

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserPhone() {
            return UserPhone;
        }

        public void setUserPhone(String UserPhone) {
            this.UserPhone = UserPhone;
        }

        public int getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(int CustomerID) {
            this.CustomerID = CustomerID;
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

        public String getFilingTime() {
            return FilingTime;
        }

        public void setFilingTime(String FilingTime) {
            this.FilingTime = FilingTime;
        }

        public String getFilingAuditingTime() {
            return FilingAuditingTime;
        }

        public void setFilingAuditingTime(String FilingAuditingTime) {
            this.FilingAuditingTime = FilingAuditingTime;
        }

        public int getFilingAuditingStaffID() {
            return FilingAuditingStaffID;
        }

        public void setFilingAuditingStaffID(int FilingAuditingStaffID) {
            this.FilingAuditingStaffID = FilingAuditingStaffID;
        }

        public Object getFilingAuditingRemark() {
            return FilingAuditingRemark;
        }

        public void setFilingAuditingRemark(Object FilingAuditingRemark) {
            this.FilingAuditingRemark = FilingAuditingRemark;
        }

        public Object getBandSawTime() {
            return BandSawTime;
        }

        public void setBandSawTime(Object BandSawTime) {
            this.BandSawTime = BandSawTime;
        }

        public int getBandSawStaffID() {
            return BandSawStaffID;
        }

        public void setBandSawStaffID(int BandSawStaffID) {
            this.BandSawStaffID = BandSawStaffID;
        }

        public Object getBandSawRemark() {
            return BandSawRemark;
        }

        public void setBandSawRemark(Object BandSawRemark) {
            this.BandSawRemark = BandSawRemark;
        }

        public String getSubscribeTime() {
            return SubscribeTime;
        }

        public void setSubscribeTime(String SubscribeTime) {
            this.SubscribeTime = SubscribeTime;
        }

        public int getSubscribeStaffID() {
            return SubscribeStaffID;
        }

        public void setSubscribeStaffID(int SubscribeStaffID) {
            this.SubscribeStaffID = SubscribeStaffID;
        }

        public Object getSubscribeReamrk() {
            return SubscribeReamrk;
        }

        public void setSubscribeReamrk(Object SubscribeReamrk) {
            this.SubscribeReamrk = SubscribeReamrk;
        }

        public int getBuildingsBrokerageGroupID() {
            return BuildingsBrokerageGroupID;
        }

        public void setBuildingsBrokerageGroupID(int BuildingsBrokerageGroupID) {
            this.BuildingsBrokerageGroupID = BuildingsBrokerageGroupID;
        }

        public Object getBuildingsBrokerageGroupName() {
            return BuildingsBrokerageGroupName;
        }

        public void setBuildingsBrokerageGroupName(Object BuildingsBrokerageGroupName) {
            this.BuildingsBrokerageGroupName = BuildingsBrokerageGroupName;
        }

        public Object getBrokerage() {
            return Brokerage;
        }

        public void setBrokerage(Object Brokerage) {
            this.Brokerage = Brokerage;
        }

        public double getTotalPrice() {
            return TotalPrice;
        }

        public void setTotalPrice(double TotalPrice) {
            this.TotalPrice = TotalPrice;
        }

        public double getArea() {
            return Area;
        }

        public void setArea(double Area) {
            this.Area = Area;
        }

        public Object getAddress() {
            return Address;
        }

        public void setAddress(Object Address) {
            this.Address = Address;
        }

        public double getBargainMoney() {
            return BargainMoney;
        }

        public void setBargainMoney(double BargainMoney) {
            this.BargainMoney = BargainMoney;
        }

        public String getDownPaymentTime() {
            return DownPaymentTime;
        }

        public void setDownPaymentTime(String DownPaymentTime) {
            this.DownPaymentTime = DownPaymentTime;
        }

        public int getDownPaymentStaffID() {
            return DownPaymentStaffID;
        }

        public void setDownPaymentStaffID(int DownPaymentStaffID) {
            this.DownPaymentStaffID = DownPaymentStaffID;
        }

        public double getDownPaymentMoney() {
            return DownPaymentMoney;
        }

        public void setDownPaymentMoney(double DownPaymentMoney) {
            this.DownPaymentMoney = DownPaymentMoney;
        }

        public Object getDownPaymentRemark() {
            return DownPaymentRemark;
        }

        public void setDownPaymentRemark(Object DownPaymentRemark) {
            this.DownPaymentRemark = DownPaymentRemark;
        }

        public String getPutOnRecordTime() {
            return PutOnRecordTime;
        }

        public void setPutOnRecordTime(String PutOnRecordTime) {
            this.PutOnRecordTime = PutOnRecordTime;
        }

        public int getPutOnRecordStaffID() {
            return PutOnRecordStaffID;
        }

        public void setPutOnRecordStaffID(int PutOnRecordStaffID) {
            this.PutOnRecordStaffID = PutOnRecordStaffID;
        }

        public Object getPutOnRecordRemark() {
            return PutOnRecordRemark;
        }

        public void setPutOnRecordRemark(Object PutOnRecordRemark) {
            this.PutOnRecordRemark = PutOnRecordRemark;
        }

        public String getCompleteTime() {
            return CompleteTime;
        }

        public void setCompleteTime(String CompleteTime) {
            this.CompleteTime = CompleteTime;
        }

        public int getCompleteStaffID() {
            return CompleteStaffID;
        }

        public void setCompleteStaffID(int CompleteStaffID) {
            this.CompleteStaffID = CompleteStaffID;
        }

        public Object getCompleteRemark() {
            return CompleteRemark;
        }

        public void setCompleteRemark(Object CompleteRemark) {
            this.CompleteRemark = CompleteRemark;
        }

        public String getReturnHouseTime() {
            return ReturnHouseTime;
        }

        public void setReturnHouseTime(String ReturnHouseTime) {
            this.ReturnHouseTime = ReturnHouseTime;
        }

        public int getReturnHouseStaffID() {
            return ReturnHouseStaffID;
        }

        public void setReturnHouseStaffID(int ReturnHouseStaffID) {
            this.ReturnHouseStaffID = ReturnHouseStaffID;
        }

        public Object getReturnHouseRemark() {
            return ReturnHouseRemark;
        }

        public void setReturnHouseRemark(Object ReturnHouseRemark) {
            this.ReturnHouseRemark = ReturnHouseRemark;
        }

        public String getReturnHouseAuditingTime() {
            return ReturnHouseAuditingTime;
        }

        public void setReturnHouseAuditingTime(String ReturnHouseAuditingTime) {
            this.ReturnHouseAuditingTime = ReturnHouseAuditingTime;
        }

        public int getReturnHouseAuditingStaffID() {
            return ReturnHouseAuditingStaffID;
        }

        public void setReturnHouseAuditingStaffID(int ReturnHouseAuditingStaffID) {
            this.ReturnHouseAuditingStaffID = ReturnHouseAuditingStaffID;
        }

        public Object getReturnHouseAuditingRemark() {
            return ReturnHouseAuditingRemark;
        }

        public void setReturnHouseAuditingRemark(Object ReturnHouseAuditingRemark) {
            this.ReturnHouseAuditingRemark = ReturnHouseAuditingRemark;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public Object getRemark() {
            return Remark;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public Object getStarTarget() {
            return StarTarget;
        }

        public void setStarTarget(Object StarTarget) {
            this.StarTarget = StarTarget;
        }

        public Object getStarTargetRemark() {
            return StarTargetRemark;
        }

        public void setStarTargetRemark(Object StarTargetRemark) {
            this.StarTargetRemark = StarTargetRemark;
        }

        public Object getPropertyConsultantName() {
            return PropertyConsultantName;
        }

        public void setPropertyConsultantName(Object PropertyConsultantName) {
            this.PropertyConsultantName = PropertyConsultantName;
        }

        public int getOriginStaffID() {
            return OriginStaffID;
        }

        public void setOriginStaffID(int OriginStaffID) {
            this.OriginStaffID = OriginStaffID;
        }

        public int getSellStaffID() {
            return SellStaffID;
        }

        public void setSellStaffID(int SellStaffID) {
            this.SellStaffID = SellStaffID;
        }

        public int getAgentID() {
            return AgentID;
        }

        public void setAgentID(int AgentID) {
            this.AgentID = AgentID;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }
}
