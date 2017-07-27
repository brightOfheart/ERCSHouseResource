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
     * Data : {"BaseInfo":{"Name":"阳光经典公寓","Address":"皇姑区黄河南大街118号","AreaID":"","SellingPrice":"58","LeasePrice":"","Remark":"这个楼盘相当好","BuildingTypeID":1,"State":0,"AgentID":0,"MediumOfCommunicationStaffID":2,"StationedStaffID":2,"StarTarget":"2","StarTargetRemark":"","LetSellType":2,"BuildingInfo":"此楼盘什么鬼","Province":"210000","City":"210100","District":"210105","SellingBrokerage":"2000","LeaseBrokerage":"","BandSawBrokerage":"10","CommissionAccount":"啊呸","OpeningTime":"2017-07-01T00:00:00","LaunchTime":"2018-02-22T00:00:00","Developer":"泰昇房地产（沈阳）有限公司","DeveloperBrand":"什么鬼","PropertyManagementCompany":"东北物业","CoveredArea":"2000平","TotalHouseholds":"200","PlotRatio":"50%","GreeningRatio":"70&","CarNumber":"100","ParkingRatio":"50%","AveragePrice":"2000元","PropertyManagementFee":"20","BuildingClass":"欧美风格","DecorationCondition":"良好","PropertyRight":"70年","CooperateBeginTime":"2017-07-01T00:00:00","CooperateEndTime":"2018-02-16T00:00:00","SubscriptionDeadline":"2017-02-09T00:00:00","CooperativeHousing":"","DevelopersRules":"此规则为开发商规则，无经批准，不可修改","AwardDescription":"奖励一个家用电器为手电筒","FilingRules":"此规则为报备规则，无经批准，不可修改","BandSawRules":"此规则为带看规则，无经批准，不可修改","TransactionRules":"此规则为成交规则，无经批准，不可修改","PriceAdvantage":"便宜","HouseTypeArea":"100","LivingFacilities":"齐全","SchoolDistrict":"各种学校","Transportation":"各种公交地铁","RegionalDevelopment":"前景良好","Characteristic":"额。。。。","BrandAdvantage":"顶级","HaveProductComparison":"优异极大","X":41.7529445584274,"Y":123.422754665974,"ShowIndex":0,"IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:38:41","Id":1},"ImagePath":"/Images/BuildingsImages/201707261012053972839.png","AreaName":null,"HouseTypeList":[{"ImagePath":"/Images/BuildingsImages/201707270839394861634.jpg","HouseTypeTagList":["南北通透","学区","户型方正"],"BuildingID":1,"Name":"歪户型","Area":100,"Room":2,"Hall":2,"Toilet":2,"Inventory":12,"SalesVolume":31,"Price":"200000000","Preferential":"超优惠","Orientations":"坐地朝天","DecorationCondition":"良好","Reason":"便宜","ShowIndex":0,"Remark":"此户型人生必备","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-27T14:45:35.670396+08:00","Id":0}],"BuildingsBrokerageGroupList":[{"BuildingID":2,"Name":"1111122","Brokerage":"如大为提高低收入房还有多少","Remark":"人的眼光的私人银行的输入法","ShowIndex":0,"PercentageType":2,"Id":1}],"PropertyConsultantList":[{"BuildingID":1,"Name":"张飞","Sex":1,"Phone":"13644038730","Duties":"飞天","ShowIndex":1,"CreateStaffID":1,"IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-01-01T00:00:00","Id":1}],"ModifyBuildingBrokerageLogList":[{"BuildingID":1,"StaffName":"系统管理员","SellingBrokerage":"2000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T08:54:33","Id":1},{"BuildingID":1,"StaffName":"系统管理员","SellingBrokerage":"2000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:28:43","Id":2}]}
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
         * BaseInfo : {"Name":"阳光经典公寓","Address":"皇姑区黄河南大街118号","AreaID":"","SellingPrice":"58","LeasePrice":"","Remark":"这个楼盘相当好","BuildingTypeID":1,"State":0,"AgentID":0,"MediumOfCommunicationStaffID":2,"StationedStaffID":2,"StarTarget":"2","StarTargetRemark":"","LetSellType":2,"BuildingInfo":"此楼盘什么鬼","Province":"210000","City":"210100","District":"210105","SellingBrokerage":"2000","LeaseBrokerage":"","BandSawBrokerage":"10","CommissionAccount":"啊呸","OpeningTime":"2017-07-01T00:00:00","LaunchTime":"2018-02-22T00:00:00","Developer":"泰昇房地产（沈阳）有限公司","DeveloperBrand":"什么鬼","PropertyManagementCompany":"东北物业","CoveredArea":"2000平","TotalHouseholds":"200","PlotRatio":"50%","GreeningRatio":"70&","CarNumber":"100","ParkingRatio":"50%","AveragePrice":"2000元","PropertyManagementFee":"20","BuildingClass":"欧美风格","DecorationCondition":"良好","PropertyRight":"70年","CooperateBeginTime":"2017-07-01T00:00:00","CooperateEndTime":"2018-02-16T00:00:00","SubscriptionDeadline":"2017-02-09T00:00:00","CooperativeHousing":"","DevelopersRules":"此规则为开发商规则，无经批准，不可修改","AwardDescription":"奖励一个家用电器为手电筒","FilingRules":"此规则为报备规则，无经批准，不可修改","BandSawRules":"此规则为带看规则，无经批准，不可修改","TransactionRules":"此规则为成交规则，无经批准，不可修改","PriceAdvantage":"便宜","HouseTypeArea":"100","LivingFacilities":"齐全","SchoolDistrict":"各种学校","Transportation":"各种公交地铁","RegionalDevelopment":"前景良好","Characteristic":"额。。。。","BrandAdvantage":"顶级","HaveProductComparison":"优异极大","X":41.7529445584274,"Y":123.422754665974,"ShowIndex":0,"IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:38:41","Id":1}
         * ImagePath : /Images/BuildingsImages/201707261012053972839.png
         * AreaName : null
         * HouseTypeList : [{"ImagePath":"/Images/BuildingsImages/201707270839394861634.jpg","HouseTypeTagList":["南北通透","学区","户型方正"],"BuildingID":1,"Name":"歪户型","Area":100,"Room":2,"Hall":2,"Toilet":2,"Inventory":12,"SalesVolume":31,"Price":"200000000","Preferential":"超优惠","Orientations":"坐地朝天","DecorationCondition":"良好","Reason":"便宜","ShowIndex":0,"Remark":"此户型人生必备","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-27T14:45:35.670396+08:00","Id":0}]
         * BuildingsBrokerageGroupList : [{"BuildingID":2,"Name":"1111122","Brokerage":"如大为提高低收入房还有多少","Remark":"人的眼光的私人银行的输入法","ShowIndex":0,"PercentageType":2,"Id":1}]
         * PropertyConsultantList : [{"BuildingID":1,"Name":"张飞","Sex":1,"Phone":"13644038730","Duties":"飞天","ShowIndex":1,"CreateStaffID":1,"IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-01-01T00:00:00","Id":1}]
         * ModifyBuildingBrokerageLogList : [{"BuildingID":1,"StaffName":"系统管理员","SellingBrokerage":"2000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T08:54:33","Id":1},{"BuildingID":1,"StaffName":"系统管理员","SellingBrokerage":"2000","LeaseBrokerage":"","BandSawBrokerage":"10","IsLocked":false,"IsDeleted":false,"CreatedTime":"2017-07-26T09:28:43","Id":2}]
         */

        private BaseInfoBean BaseInfo;
        private String ImagePath;
        private Object AreaName;
        private List<HouseTypeListBean> HouseTypeList;
        private List<BuildingsBrokerageGroupListBean> BuildingsBrokerageGroupList;
        private List<PropertyConsultantListBean> PropertyConsultantList;
        private List<ModifyBuildingBrokerageLogListBean> ModifyBuildingBrokerageLogList;

        public BaseInfoBean getBaseInfo() {
            return BaseInfo;
        }

        public void setBaseInfo(BaseInfoBean BaseInfo) {
            this.BaseInfo = BaseInfo;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String ImagePath) {
            this.ImagePath = ImagePath;
        }

        public Object getAreaName() {
            return AreaName;
        }

        public void setAreaName(Object AreaName) {
            this.AreaName = AreaName;
        }

        public List<HouseTypeListBean> getHouseTypeList() {
            return HouseTypeList;
        }

        public void setHouseTypeList(List<HouseTypeListBean> HouseTypeList) {
            this.HouseTypeList = HouseTypeList;
        }

        public List<BuildingsBrokerageGroupListBean> getBuildingsBrokerageGroupList() {
            return BuildingsBrokerageGroupList;
        }

        public void setBuildingsBrokerageGroupList(List<BuildingsBrokerageGroupListBean> BuildingsBrokerageGroupList) {
            this.BuildingsBrokerageGroupList = BuildingsBrokerageGroupList;
        }

        public List<PropertyConsultantListBean> getPropertyConsultantList() {
            return PropertyConsultantList;
        }

        public void setPropertyConsultantList(List<PropertyConsultantListBean> PropertyConsultantList) {
            this.PropertyConsultantList = PropertyConsultantList;
        }

        public List<ModifyBuildingBrokerageLogListBean> getModifyBuildingBrokerageLogList() {
            return ModifyBuildingBrokerageLogList;
        }

        public void setModifyBuildingBrokerageLogList(List<ModifyBuildingBrokerageLogListBean> ModifyBuildingBrokerageLogList) {
            this.ModifyBuildingBrokerageLogList = ModifyBuildingBrokerageLogList;
        }

        public static class BaseInfoBean {
            /**
             * Name : 阳光经典公寓
             * Address : 皇姑区黄河南大街118号
             * AreaID :
             * SellingPrice : 58
             * LeasePrice :
             * Remark : 这个楼盘相当好
             * BuildingTypeID : 1
             * State : 0
             * AgentID : 0
             * MediumOfCommunicationStaffID : 2
             * StationedStaffID : 2
             * StarTarget : 2
             * StarTargetRemark :
             * LetSellType : 2
             * BuildingInfo : 此楼盘什么鬼
             * Province : 210000
             * City : 210100
             * District : 210105
             * SellingBrokerage : 2000
             * LeaseBrokerage :
             * BandSawBrokerage : 10
             * CommissionAccount : 啊呸
             * OpeningTime : 2017-07-01T00:00:00
             * LaunchTime : 2018-02-22T00:00:00
             * Developer : 泰昇房地产（沈阳）有限公司
             * DeveloperBrand : 什么鬼
             * PropertyManagementCompany : 东北物业
             * CoveredArea : 2000平
             * TotalHouseholds : 200
             * PlotRatio : 50%
             * GreeningRatio : 70&
             * CarNumber : 100
             * ParkingRatio : 50%
             * AveragePrice : 2000元
             * PropertyManagementFee : 20
             * BuildingClass : 欧美风格
             * DecorationCondition : 良好
             * PropertyRight : 70年
             * CooperateBeginTime : 2017-07-01T00:00:00
             * CooperateEndTime : 2018-02-16T00:00:00
             * SubscriptionDeadline : 2017-02-09T00:00:00
             * CooperativeHousing :
             * DevelopersRules : 此规则为开发商规则，无经批准，不可修改
             * AwardDescription : 奖励一个家用电器为手电筒
             * FilingRules : 此规则为报备规则，无经批准，不可修改
             * BandSawRules : 此规则为带看规则，无经批准，不可修改
             * TransactionRules : 此规则为成交规则，无经批准，不可修改
             * PriceAdvantage : 便宜
             * HouseTypeArea : 100
             * LivingFacilities : 齐全
             * SchoolDistrict : 各种学校
             * Transportation : 各种公交地铁
             * RegionalDevelopment : 前景良好
             * Characteristic : 额。。。。
             * BrandAdvantage : 顶级
             * HaveProductComparison : 优异极大
             * X : 41.7529445584274
             * Y : 123.422754665974
             * ShowIndex : 0
             * IsLocked : false
             * IsDeleted : false
             * CreatedTime : 2017-07-26T09:38:41
             * Id : 1
             */

            private String Name;
            private String Address;
            private String AreaID;
            private String SellingPrice;
            private String LeasePrice;
            private String Remark;
            private int BuildingTypeID;
            private int State;
            private int AgentID;
            private int MediumOfCommunicationStaffID;
            private int StationedStaffID;
            private String StarTarget;
            private String StarTargetRemark;
            private int LetSellType;
            private String BuildingInfo;
            private String Province;
            private String City;
            private String District;
            private String SellingBrokerage;
            private String LeaseBrokerage;
            private String BandSawBrokerage;
            private String CommissionAccount;
            private String OpeningTime;
            private String LaunchTime;
            private String Developer;
            private String DeveloperBrand;
            private String PropertyManagementCompany;
            private String CoveredArea;
            private String TotalHouseholds;
            private String PlotRatio;
            private String GreeningRatio;
            private String CarNumber;
            private String ParkingRatio;
            private String AveragePrice;
            private String PropertyManagementFee;
            private String BuildingClass;
            private String DecorationCondition;
            private String PropertyRight;
            private String CooperateBeginTime;
            private String CooperateEndTime;
            private String SubscriptionDeadline;
            private String CooperativeHousing;
            private String DevelopersRules;
            private String AwardDescription;
            private String FilingRules;
            private String BandSawRules;
            private String TransactionRules;
            private String PriceAdvantage;
            private String HouseTypeArea;
            private String LivingFacilities;
            private String SchoolDistrict;
            private String Transportation;
            private String RegionalDevelopment;
            private String Characteristic;
            private String BrandAdvantage;
            private String HaveProductComparison;
            private double X;
            private double Y;
            private int ShowIndex;
            private boolean IsLocked;
            private boolean IsDeleted;
            private String CreatedTime;
            private int Id;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getAreaID() {
                return AreaID;
            }

            public void setAreaID(String AreaID) {
                this.AreaID = AreaID;
            }

            public String getSellingPrice() {
                return SellingPrice;
            }

            public void setSellingPrice(String SellingPrice) {
                this.SellingPrice = SellingPrice;
            }

            public String getLeasePrice() {
                return LeasePrice;
            }

            public void setLeasePrice(String LeasePrice) {
                this.LeasePrice = LeasePrice;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public int getBuildingTypeID() {
                return BuildingTypeID;
            }

            public void setBuildingTypeID(int BuildingTypeID) {
                this.BuildingTypeID = BuildingTypeID;
            }

            public int getState() {
                return State;
            }

            public void setState(int State) {
                this.State = State;
            }

            public int getAgentID() {
                return AgentID;
            }

            public void setAgentID(int AgentID) {
                this.AgentID = AgentID;
            }

            public int getMediumOfCommunicationStaffID() {
                return MediumOfCommunicationStaffID;
            }

            public void setMediumOfCommunicationStaffID(int MediumOfCommunicationStaffID) {
                this.MediumOfCommunicationStaffID = MediumOfCommunicationStaffID;
            }

            public int getStationedStaffID() {
                return StationedStaffID;
            }

            public void setStationedStaffID(int StationedStaffID) {
                this.StationedStaffID = StationedStaffID;
            }

            public String getStarTarget() {
                return StarTarget;
            }

            public void setStarTarget(String StarTarget) {
                this.StarTarget = StarTarget;
            }

            public String getStarTargetRemark() {
                return StarTargetRemark;
            }

            public void setStarTargetRemark(String StarTargetRemark) {
                this.StarTargetRemark = StarTargetRemark;
            }

            public int getLetSellType() {
                return LetSellType;
            }

            public void setLetSellType(int LetSellType) {
                this.LetSellType = LetSellType;
            }

            public String getBuildingInfo() {
                return BuildingInfo;
            }

            public void setBuildingInfo(String BuildingInfo) {
                this.BuildingInfo = BuildingInfo;
            }

            public String getProvince() {
                return Province;
            }

            public void setProvince(String Province) {
                this.Province = Province;
            }

            public String getCity() {
                return City;
            }

            public void setCity(String City) {
                this.City = City;
            }

            public String getDistrict() {
                return District;
            }

            public void setDistrict(String District) {
                this.District = District;
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

            public String getCommissionAccount() {
                return CommissionAccount;
            }

            public void setCommissionAccount(String CommissionAccount) {
                this.CommissionAccount = CommissionAccount;
            }

            public String getOpeningTime() {
                return OpeningTime;
            }

            public void setOpeningTime(String OpeningTime) {
                this.OpeningTime = OpeningTime;
            }

            public String getLaunchTime() {
                return LaunchTime;
            }

            public void setLaunchTime(String LaunchTime) {
                this.LaunchTime = LaunchTime;
            }

            public String getDeveloper() {
                return Developer;
            }

            public void setDeveloper(String Developer) {
                this.Developer = Developer;
            }

            public String getDeveloperBrand() {
                return DeveloperBrand;
            }

            public void setDeveloperBrand(String DeveloperBrand) {
                this.DeveloperBrand = DeveloperBrand;
            }

            public String getPropertyManagementCompany() {
                return PropertyManagementCompany;
            }

            public void setPropertyManagementCompany(String PropertyManagementCompany) {
                this.PropertyManagementCompany = PropertyManagementCompany;
            }

            public String getCoveredArea() {
                return CoveredArea;
            }

            public void setCoveredArea(String CoveredArea) {
                this.CoveredArea = CoveredArea;
            }

            public String getTotalHouseholds() {
                return TotalHouseholds;
            }

            public void setTotalHouseholds(String TotalHouseholds) {
                this.TotalHouseholds = TotalHouseholds;
            }

            public String getPlotRatio() {
                return PlotRatio;
            }

            public void setPlotRatio(String PlotRatio) {
                this.PlotRatio = PlotRatio;
            }

            public String getGreeningRatio() {
                return GreeningRatio;
            }

            public void setGreeningRatio(String GreeningRatio) {
                this.GreeningRatio = GreeningRatio;
            }

            public String getCarNumber() {
                return CarNumber;
            }

            public void setCarNumber(String CarNumber) {
                this.CarNumber = CarNumber;
            }

            public String getParkingRatio() {
                return ParkingRatio;
            }

            public void setParkingRatio(String ParkingRatio) {
                this.ParkingRatio = ParkingRatio;
            }

            public String getAveragePrice() {
                return AveragePrice;
            }

            public void setAveragePrice(String AveragePrice) {
                this.AveragePrice = AveragePrice;
            }

            public String getPropertyManagementFee() {
                return PropertyManagementFee;
            }

            public void setPropertyManagementFee(String PropertyManagementFee) {
                this.PropertyManagementFee = PropertyManagementFee;
            }

            public String getBuildingClass() {
                return BuildingClass;
            }

            public void setBuildingClass(String BuildingClass) {
                this.BuildingClass = BuildingClass;
            }

            public String getDecorationCondition() {
                return DecorationCondition;
            }

            public void setDecorationCondition(String DecorationCondition) {
                this.DecorationCondition = DecorationCondition;
            }

            public String getPropertyRight() {
                return PropertyRight;
            }

            public void setPropertyRight(String PropertyRight) {
                this.PropertyRight = PropertyRight;
            }

            public String getCooperateBeginTime() {
                return CooperateBeginTime;
            }

            public void setCooperateBeginTime(String CooperateBeginTime) {
                this.CooperateBeginTime = CooperateBeginTime;
            }

            public String getCooperateEndTime() {
                return CooperateEndTime;
            }

            public void setCooperateEndTime(String CooperateEndTime) {
                this.CooperateEndTime = CooperateEndTime;
            }

            public String getSubscriptionDeadline() {
                return SubscriptionDeadline;
            }

            public void setSubscriptionDeadline(String SubscriptionDeadline) {
                this.SubscriptionDeadline = SubscriptionDeadline;
            }

            public String getCooperativeHousing() {
                return CooperativeHousing;
            }

            public void setCooperativeHousing(String CooperativeHousing) {
                this.CooperativeHousing = CooperativeHousing;
            }

            public String getDevelopersRules() {
                return DevelopersRules;
            }

            public void setDevelopersRules(String DevelopersRules) {
                this.DevelopersRules = DevelopersRules;
            }

            public String getAwardDescription() {
                return AwardDescription;
            }

            public void setAwardDescription(String AwardDescription) {
                this.AwardDescription = AwardDescription;
            }

            public String getFilingRules() {
                return FilingRules;
            }

            public void setFilingRules(String FilingRules) {
                this.FilingRules = FilingRules;
            }

            public String getBandSawRules() {
                return BandSawRules;
            }

            public void setBandSawRules(String BandSawRules) {
                this.BandSawRules = BandSawRules;
            }

            public String getTransactionRules() {
                return TransactionRules;
            }

            public void setTransactionRules(String TransactionRules) {
                this.TransactionRules = TransactionRules;
            }

            public String getPriceAdvantage() {
                return PriceAdvantage;
            }

            public void setPriceAdvantage(String PriceAdvantage) {
                this.PriceAdvantage = PriceAdvantage;
            }

            public String getHouseTypeArea() {
                return HouseTypeArea;
            }

            public void setHouseTypeArea(String HouseTypeArea) {
                this.HouseTypeArea = HouseTypeArea;
            }

            public String getLivingFacilities() {
                return LivingFacilities;
            }

            public void setLivingFacilities(String LivingFacilities) {
                this.LivingFacilities = LivingFacilities;
            }

            public String getSchoolDistrict() {
                return SchoolDistrict;
            }

            public void setSchoolDistrict(String SchoolDistrict) {
                this.SchoolDistrict = SchoolDistrict;
            }

            public String getTransportation() {
                return Transportation;
            }

            public void setTransportation(String Transportation) {
                this.Transportation = Transportation;
            }

            public String getRegionalDevelopment() {
                return RegionalDevelopment;
            }

            public void setRegionalDevelopment(String RegionalDevelopment) {
                this.RegionalDevelopment = RegionalDevelopment;
            }

            public String getCharacteristic() {
                return Characteristic;
            }

            public void setCharacteristic(String Characteristic) {
                this.Characteristic = Characteristic;
            }

            public String getBrandAdvantage() {
                return BrandAdvantage;
            }

            public void setBrandAdvantage(String BrandAdvantage) {
                this.BrandAdvantage = BrandAdvantage;
            }

            public String getHaveProductComparison() {
                return HaveProductComparison;
            }

            public void setHaveProductComparison(String HaveProductComparison) {
                this.HaveProductComparison = HaveProductComparison;
            }

            public double getX() {
                return X;
            }

            public void setX(double X) {
                this.X = X;
            }

            public double getY() {
                return Y;
            }

            public void setY(double Y) {
                this.Y = Y;
            }

            public int getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(int ShowIndex) {
                this.ShowIndex = ShowIndex;
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

        public static class HouseTypeListBean {
            /**
             * ImagePath : /Images/BuildingsImages/201707270839394861634.jpg
             * HouseTypeTagList : ["南北通透","学区","户型方正"]
             * BuildingID : 1
             * Name : 歪户型
             * Area : 100
             * Room : 2
             * Hall : 2
             * Toilet : 2
             * Inventory : 12
             * SalesVolume : 31
             * Price : 200000000
             * Preferential : 超优惠
             * Orientations : 坐地朝天
             * DecorationCondition : 良好
             * Reason : 便宜
             * ShowIndex : 0
             * Remark : 此户型人生必备
             * IsLocked : false
             * IsDeleted : false
             * CreatedTime : 2017-07-27T14:45:35.670396+08:00
             * Id : 0
             */

            private String ImagePath;
            private int BuildingID;
            private String Name;
            private int Area;
            private int Room;
            private int Hall;
            private int Toilet;
            private int Inventory;
            private int SalesVolume;
            private String Price;
            private String Preferential;
            private String Orientations;
            private String DecorationCondition;
            private String Reason;
            private int ShowIndex;
            private String Remark;
            private boolean IsLocked;
            private boolean IsDeleted;
            private String CreatedTime;
            private int Id;
            private List<String> HouseTypeTagList;

            public String getImagePath() {
                return ImagePath;
            }

            public void setImagePath(String ImagePath) {
                this.ImagePath = ImagePath;
            }

            public int getBuildingID() {
                return BuildingID;
            }

            public void setBuildingID(int BuildingID) {
                this.BuildingID = BuildingID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getArea() {
                return Area;
            }

            public void setArea(int Area) {
                this.Area = Area;
            }

            public int getRoom() {
                return Room;
            }

            public void setRoom(int Room) {
                this.Room = Room;
            }

            public int getHall() {
                return Hall;
            }

            public void setHall(int Hall) {
                this.Hall = Hall;
            }

            public int getToilet() {
                return Toilet;
            }

            public void setToilet(int Toilet) {
                this.Toilet = Toilet;
            }

            public int getInventory() {
                return Inventory;
            }

            public void setInventory(int Inventory) {
                this.Inventory = Inventory;
            }

            public int getSalesVolume() {
                return SalesVolume;
            }

            public void setSalesVolume(int SalesVolume) {
                this.SalesVolume = SalesVolume;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String Price) {
                this.Price = Price;
            }

            public String getPreferential() {
                return Preferential;
            }

            public void setPreferential(String Preferential) {
                this.Preferential = Preferential;
            }

            public String getOrientations() {
                return Orientations;
            }

            public void setOrientations(String Orientations) {
                this.Orientations = Orientations;
            }

            public String getDecorationCondition() {
                return DecorationCondition;
            }

            public void setDecorationCondition(String DecorationCondition) {
                this.DecorationCondition = DecorationCondition;
            }

            public String getReason() {
                return Reason;
            }

            public void setReason(String Reason) {
                this.Reason = Reason;
            }

            public int getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(int ShowIndex) {
                this.ShowIndex = ShowIndex;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
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

            public List<String> getHouseTypeTagList() {
                return HouseTypeTagList;
            }

            public void setHouseTypeTagList(List<String> HouseTypeTagList) {
                this.HouseTypeTagList = HouseTypeTagList;
            }
        }

        public static class BuildingsBrokerageGroupListBean {
            /**
             * BuildingID : 2
             * Name : 1111122
             * Brokerage : 如大为提高低收入房还有多少
             * Remark : 人的眼光的私人银行的输入法
             * ShowIndex : 0
             * PercentageType : 2
             * Id : 1
             */

            private int BuildingID;
            private String Name;
            private String Brokerage;
            private String Remark;
            private int ShowIndex;
            private int PercentageType;
            private int Id;

            public int getBuildingID() {
                return BuildingID;
            }

            public void setBuildingID(int BuildingID) {
                this.BuildingID = BuildingID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getBrokerage() {
                return Brokerage;
            }

            public void setBrokerage(String Brokerage) {
                this.Brokerage = Brokerage;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public int getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(int ShowIndex) {
                this.ShowIndex = ShowIndex;
            }

            public int getPercentageType() {
                return PercentageType;
            }

            public void setPercentageType(int PercentageType) {
                this.PercentageType = PercentageType;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }
        }

        public static class PropertyConsultantListBean {
            /**
             * BuildingID : 1
             * Name : 张飞
             * Sex : 1
             * Phone : 13644038730
             * Duties : 飞天
             * ShowIndex : 1
             * CreateStaffID : 1
             * IsLocked : false
             * IsDeleted : false
             * CreatedTime : 2017-01-01T00:00:00
             * Id : 1
             */

            private int BuildingID;
            private String Name;
            private int Sex;
            private String Phone;
            private String Duties;
            private int ShowIndex;
            private int CreateStaffID;
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

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getSex() {
                return Sex;
            }

            public void setSex(int Sex) {
                this.Sex = Sex;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public String getDuties() {
                return Duties;
            }

            public void setDuties(String Duties) {
                this.Duties = Duties;
            }

            public int getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(int ShowIndex) {
                this.ShowIndex = ShowIndex;
            }

            public int getCreateStaffID() {
                return CreateStaffID;
            }

            public void setCreateStaffID(int CreateStaffID) {
                this.CreateStaffID = CreateStaffID;
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

        public static class ModifyBuildingBrokerageLogListBean {
            /**
             * BuildingID : 1
             * StaffName : 系统管理员
             * SellingBrokerage : 2000
             * LeaseBrokerage :
             * BandSawBrokerage : 10
             * IsLocked : false
             * IsDeleted : false
             * CreatedTime : 2017-07-26T08:54:33
             * Id : 1
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
