package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 * 低价房房源详情
 */

public class CheapRoomDetailBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private String AreaName;
        private BaseInfoBean BaseInfo;
        private String BuildingsType;
        private String ResidenceType;
        private String Orientations;
        private List<String> ImageList ;
        private BuildingInfoBean BuildingInfo;

        public BuildingInfoBean getBuildingInfo() {
            return BuildingInfo;
        }

        public void setBuildingInfo(BuildingInfoBean buildingInfo) {
            BuildingInfo = buildingInfo;
        }

        public List<String> getImageList() {
            return ImageList;
        }

        public void setImageList(List<String> imageList) {
            ImageList = imageList;
        }

        public String getOrientations() {
            return Orientations;
        }

        public void setOrientations(String orientations) {
            Orientations = orientations;
        }

        public String getResidenceType() {
            return ResidenceType;
        }

        public void setResidenceType(String residenceType) {
            ResidenceType = residenceType;
        }

        public BaseInfoBean getBaseInfo() {
            return BaseInfo;
        }

        public void setBaseInfo(BaseInfoBean baseInfo) {
            BaseInfo = baseInfo;
        }

        public String getBuildingsType() {
            return BuildingsType;
        }

        public void setBuildingsType(String buildingsType) {
            BuildingsType = buildingsType;
        }

        public String getAreaName() {
            return AreaName;
        }

        public void setAreaName(String areaName) {
            AreaName = areaName;
        }

        public BaseInfoBean getModel() {
            return BaseInfo;
        }

        public void setModel(BaseInfoBean model) {
            BaseInfo = model;
        }
        public class BuildingInfoBean
        {
            private String Name;
            private String ProvinceID;
            private String CityID;
            private String AreaID;
            private String Address;
            private String Remark;
            private String OpeningTime;
            private String LaunchTime;
            private String Developer;
            private String DeveloperBrand;
            private String PropertyManagementCompany;
            private String PropertyManagementFee;
            private String CoveredArea;
            private String TotalHouseholds;
            private String PlotRatio;
            private String GreeningRatio;
            private String CarNumber;
            private String ParkingRatio;
            private String PriceAdvantage;
            private String HouseTypeArea;
            private String LivingFacilities;
            private String SchoolDistrict;
            private String Transportation;
            private String RegionalDevelopment;
            private String Characteristic;
            private String BrandAdvantage;
            private String HaveProductComparison;
            private String X;
            private String Y;
            private String Id;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getProvinceID() {
                return ProvinceID;
            }

            public void setProvinceID(String provinceID) {
                ProvinceID = provinceID;
            }

            public String getCityID() {
                return CityID;
            }

            public void setCityID(String cityID) {
                CityID = cityID;
            }

            public String getAreaID() {
                return AreaID;
            }

            public void setAreaID(String areaID) {
                AreaID = areaID;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String address) {
                Address = address;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String remark) {
                Remark = remark;
            }

            public String getOpeningTime() {
                return OpeningTime;
            }

            public void setOpeningTime(String openingTime) {
                OpeningTime = openingTime;
            }

            public String getLaunchTime() {
                return LaunchTime;
            }

            public void setLaunchTime(String launchTime) {
                LaunchTime = launchTime;
            }

            public String getDeveloper() {
                return Developer;
            }

            public void setDeveloper(String developer) {
                Developer = developer;
            }

            public String getDeveloperBrand() {
                return DeveloperBrand;
            }

            public void setDeveloperBrand(String developerBrand) {
                DeveloperBrand = developerBrand;
            }

            public String getPropertyManagementCompany() {
                return PropertyManagementCompany;
            }

            public void setPropertyManagementCompany(String propertyManagementCompany) {
                PropertyManagementCompany = propertyManagementCompany;
            }

            public String getPropertyManagementFee() {
                return PropertyManagementFee;
            }

            public void setPropertyManagementFee(String propertyManagementFee) {
                PropertyManagementFee = propertyManagementFee;
            }

            public String getCoveredArea() {
                return CoveredArea;
            }

            public void setCoveredArea(String coveredArea) {
                CoveredArea = coveredArea;
            }

            public String getTotalHouseholds() {
                return TotalHouseholds;
            }

            public void setTotalHouseholds(String totalHouseholds) {
                TotalHouseholds = totalHouseholds;
            }

            public String getPlotRatio() {
                return PlotRatio;
            }

            public void setPlotRatio(String plotRatio) {
                PlotRatio = plotRatio;
            }

            public String getGreeningRatio() {
                return GreeningRatio;
            }

            public void setGreeningRatio(String greeningRatio) {
                GreeningRatio = greeningRatio;
            }

            public String getCarNumber() {
                return CarNumber;
            }

            public void setCarNumber(String carNumber) {
                CarNumber = carNumber;
            }

            public String getParkingRatio() {
                return ParkingRatio;
            }

            public void setParkingRatio(String parkingRatio) {
                ParkingRatio = parkingRatio;
            }

            public String getPriceAdvantage() {
                return PriceAdvantage;
            }

            public void setPriceAdvantage(String priceAdvantage) {
                PriceAdvantage = priceAdvantage;
            }

            public String getHouseTypeArea() {
                return HouseTypeArea;
            }

            public void setHouseTypeArea(String houseTypeArea) {
                HouseTypeArea = houseTypeArea;
            }

            public String getLivingFacilities() {
                return LivingFacilities;
            }

            public void setLivingFacilities(String livingFacilities) {
                LivingFacilities = livingFacilities;
            }

            public String getSchoolDistrict() {
                return SchoolDistrict;
            }

            public void setSchoolDistrict(String schoolDistrict) {
                SchoolDistrict = schoolDistrict;
            }

            public String getTransportation() {
                return Transportation;
            }

            public void setTransportation(String transportation) {
                Transportation = transportation;
            }

            public String getRegionalDevelopment() {
                return RegionalDevelopment;
            }

            public void setRegionalDevelopment(String regionalDevelopment) {
                RegionalDevelopment = regionalDevelopment;
            }

            public String getCharacteristic() {
                return Characteristic;
            }

            public void setCharacteristic(String characteristic) {
                Characteristic = characteristic;
            }

            public String getBrandAdvantage() {
                return BrandAdvantage;
            }

            public void setBrandAdvantage(String brandAdvantage) {
                BrandAdvantage = brandAdvantage;
            }

            public String getHaveProductComparison() {
                return HaveProductComparison;
            }

            public void setHaveProductComparison(String haveProductComparison) {
                HaveProductComparison = haveProductComparison;
            }

            public String getX() {
                return X;
            }

            public void setX(String x) {
                X = x;
            }

            public String getY() {
                return Y;
            }

            public void setY(String y) {
                Y = y;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }
        }
        public class BaseInfoBean {
            private String LowPriceHouseBuildingID;
            private String HouseNumber;
            private String CostPrice;
            private String LowPrice;
            private String NegotiatedPrice;
            private String PriceCondition;
            private String Poundage;
            private String Brokerage;
            private String BrokerageAmount;
            private String BandSawBrokerage;
            private String CommissionAccount;
            private String AwardDescription;
            private String Rules;
            private String IsLoan;
            private String LoanRules;
            private String DownPaymentRate;
            private String Area;
            private String UseArea;
            private String HouseType;
            private String HouseTypeRemark;
            private String RoomNumber;
            private String HallNumber;
            private String ToiletNumber;
            private String CookNumber;
            private String BalconyNumber;
            private String Installation;
            private String Nature;
            private String ResidenceType;
            private String BuildingsType;
            private String BuildingClass;
            private String DecorationCondition;
            private String PropertyRight;
            private String PropertyType;
            private String CreateYear;
            private String Storey;
            private String TotalStorey;
            private String Orientations;
            private String StarTarget;
            private String StarTargetRemark;
            private String ShowIndex;
            private String State;
            private String Remark;
            private String AgentID;
            private String MediumOfCommunicationStaffID;
            private String StationedStaffID;
            private String OwnerID;
            private String ReNameDeadline;
            private String LowHouseType;
            private String IsLocked;
            private String IsDeleted;
            private String CreatedTime;

            public String getLowPriceHouseBuildingID() {
                return LowPriceHouseBuildingID;
            }

            public void setLowPriceHouseBuildingID(String lowPriceHouseBuildingID) {
                LowPriceHouseBuildingID = lowPriceHouseBuildingID;
            }

            public String getHouseNumber() {
                return HouseNumber;
            }

            public void setHouseNumber(String houseNumber) {
                HouseNumber = houseNumber;
            }

            public String getCostPrice() {
                return CostPrice;
            }

            public void setCostPrice(String costPrice) {
                CostPrice = costPrice;
            }

            public String getLowPrice() {
                return LowPrice;
            }

            public void setLowPrice(String lowPrice) {
                LowPrice = lowPrice;
            }

            public String getNegotiatedPrice() {
                return NegotiatedPrice;
            }

            public void setNegotiatedPrice(String negotiatedPrice) {
                NegotiatedPrice = negotiatedPrice;
            }

            public String getPriceCondition() {
                return PriceCondition;
            }

            public void setPriceCondition(String priceCondition) {
                PriceCondition = priceCondition;
            }

            public String getPoundage() {
                return Poundage;
            }

            public void setPoundage(String poundage) {
                Poundage = poundage;
            }

            public String getBrokerage() {
                return Brokerage;
            }

            public void setBrokerage(String brokerage) {
                Brokerage = brokerage;
            }

            public String getBrokerageAmount() {
                return BrokerageAmount;
            }

            public void setBrokerageAmount(String brokerageAmount) {
                BrokerageAmount = brokerageAmount;
            }

            public String getBandSawBrokerage() {
                return BandSawBrokerage;
            }

            public void setBandSawBrokerage(String bandSawBrokerage) {
                BandSawBrokerage = bandSawBrokerage;
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

            public String getRules() {
                return Rules;
            }

            public void setRules(String rules) {
                Rules = rules;
            }

            public String getIsLoan() {
                return IsLoan;
            }

            public void setIsLoan(String isLoan) {
                IsLoan = isLoan;
            }

            public String getLoanRules() {
                return LoanRules;
            }

            public void setLoanRules(String loanRules) {
                LoanRules = loanRules;
            }

            public String getDownPaymentRate() {
                return DownPaymentRate;
            }

            public void setDownPaymentRate(String downPaymentRate) {
                DownPaymentRate = downPaymentRate;
            }

            public String getArea() {
                return Area;
            }

            public void setArea(String area) {
                Area = area;
            }

            public String getUseArea() {
                return UseArea;
            }

            public void setUseArea(String useArea) {
                UseArea = useArea;
            }

            public String getHouseType() {
                return HouseType;
            }

            public void setHouseType(String houseType) {
                HouseType = houseType;
            }

            public String getHouseTypeRemark() {
                return HouseTypeRemark;
            }

            public void setHouseTypeRemark(String houseTypeRemark) {
                HouseTypeRemark = houseTypeRemark;
            }

            public String getRoomNumber() {
                return RoomNumber;
            }

            public void setRoomNumber(String roomNumber) {
                RoomNumber = roomNumber;
            }

            public String getHallNumber() {
                return HallNumber;
            }

            public void setHallNumber(String hallNumber) {
                HallNumber = hallNumber;
            }

            public String getToiletNumber() {
                return ToiletNumber;
            }

            public void setToiletNumber(String toiletNumber) {
                ToiletNumber = toiletNumber;
            }

            public String getCookNumber() {
                return CookNumber;
            }

            public void setCookNumber(String cookNumber) {
                CookNumber = cookNumber;
            }

            public String getBalconyNumber() {
                return BalconyNumber;
            }

            public void setBalconyNumber(String balconyNumber) {
                BalconyNumber = balconyNumber;
            }

            public String getInstallation() {
                return Installation;
            }

            public void setInstallation(String installation) {
                Installation = installation;
            }

            public String getNature() {
                return Nature;
            }

            public void setNature(String nature) {
                Nature = nature;
            }

            public String getResidenceType() {
                return ResidenceType;
            }

            public void setResidenceType(String residenceType) {
                ResidenceType = residenceType;
            }

            public String getBuildingsType() {
                return BuildingsType;
            }

            public void setBuildingsType(String buildingsType) {
                BuildingsType = buildingsType;
            }

            public String getBuildingClass() {
                return BuildingClass;
            }

            public void setBuildingClass(String buildingClass) {
                BuildingClass = buildingClass;
            }

            public String getDecorationCondition() {
                return DecorationCondition;
            }

            public void setDecorationCondition(String decorationCondition) {
                DecorationCondition = decorationCondition;
            }

            public String getPropertyRight() {
                return PropertyRight;
            }

            public void setPropertyRight(String propertyRight) {
                PropertyRight = propertyRight;
            }

            public String getPropertyType() {
                return PropertyType;
            }

            public void setPropertyType(String propertyType) {
                PropertyType = propertyType;
            }

            public String getCreateYear() {
                return CreateYear;
            }

            public void setCreateYear(String createYear) {
                CreateYear = createYear;
            }

            public String getStorey() {
                return Storey;
            }

            public void setStorey(String storey) {
                Storey = storey;
            }

            public String getTotalStorey() {
                return TotalStorey;
            }

            public void setTotalStorey(String totalStorey) {
                TotalStorey = totalStorey;
            }

            public String getOrientations() {
                return Orientations;
            }

            public void setOrientations(String orientations) {
                Orientations = orientations;
            }

            public String getStarTarget() {
                return StarTarget;
            }

            public void setStarTarget(String starTarget) {
                StarTarget = starTarget;
            }

            public String getStarTargetRemark() {
                return StarTargetRemark;
            }

            public void setStarTargetRemark(String starTargetRemark) {
                StarTargetRemark = starTargetRemark;
            }

            public String getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(String showIndex) {
                ShowIndex = showIndex;
            }

            public String getState() {
                return State;
            }

            public void setState(String state) {
                State = state;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String remark) {
                Remark = remark;
            }

            public String getAgentID() {
                return AgentID;
            }

            public void setAgentID(String agentID) {
                AgentID = agentID;
            }

            public String getMediumOfCommunicationStaffID() {
                return MediumOfCommunicationStaffID;
            }

            public void setMediumOfCommunicationStaffID(String mediumOfCommunicationStaffID) {
                MediumOfCommunicationStaffID = mediumOfCommunicationStaffID;
            }

            public String getStationedStaffID() {
                return StationedStaffID;
            }

            public void setStationedStaffID(String stationedStaffID) {
                StationedStaffID = stationedStaffID;
            }

            public String getOwnerID() {
                return OwnerID;
            }

            public void setOwnerID(String ownerID) {
                OwnerID = ownerID;
            }

            public String getReNameDeadline() {
                return ReNameDeadline;
            }

            public void setReNameDeadline(String reNameDeadline) {
                ReNameDeadline = reNameDeadline;
            }

            public String getLowHouseType() {
                return LowHouseType;
            }

            public void setLowHouseType(String lowHouseType) {
                LowHouseType = lowHouseType;
            }

            public String getIsLocked() {
                return IsLocked;
            }

            public void setIsLocked(String isLocked) {
                IsLocked = isLocked;
            }

            public String getIsDeleted() {
                return IsDeleted;
            }

            public void setIsDeleted(String isDeleted) {
                IsDeleted = isDeleted;
            }

            public String getCreatedTime() {
                return CreatedTime;
            }

            public void setCreatedTime(String createdTime) {
                CreatedTime = createdTime;
            }
        }
    }
}
