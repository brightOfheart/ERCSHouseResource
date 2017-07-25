package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * 新房列表bean
 * Created by Administrator on 2017/7/25.
 */

public class NewHouseListBean {

    /**
     * Type : 1
     * Content : 查询成功
     * Data : [{"TradeType":117,"TradeTypeChild":0,"Orientation":101,"AreaId":469,"BuildingType":89,"CityId":0,"Decoration":165,"EstateId":1,"OwnerNationality":250,"PresentSituation":156,"PropertyRightType":116,"Purpose":77,"Source":192,"Tax":112,"DataStatus":1137,"StreetId":2,"TrustType":205,"Complement":173,"UserId":4,"DepId":1,"No":"BM000011","TradeTypeName":"出租","TradeTypeChildName":null,"BuildingPosition":"www一单元","Floor":3,"RoomNo":"131","RoomType":"1室2厅0阳0卫","Square":33,"OrientationName":"南北","SaleTotal":22,"RentTotal":33,"BedroomCount":1,"BuildingTypeName":"多层","BuildYear":0,"ContactsName":"222","ContactsPhone":"33","CreatedTime":"2017-07-20T15:55:36.517","DecorationName":"豪装","FloorAll":3,"GiveUpData":"2017-07-14T00:00:00","Id":2,"LivingRoomCount":2,"MgtCost":0,"OwnerName":"111","OwnerNationalityName":"澳门","OwnerPhone":null,"PresentSituationName":"空置","PropertyNo":null,"PropertyRightTypeName":"商品房","PurposeName":"住宅","Remark":null,"RentPrice":33,"SalePrice":22,"SourceName":"58同城","SquareUse":44,"DataStatusName":"有效","TaxName":"各付","ToiletCount":0,"TrustBeginData":"2017-07-01T00:00:00","TrustEndData":"2017-07-20T00:00:00","TrustTypeName":"A-独家","VerandaCount":0,"AreaName":"和平区","StreetName":"123","EstateName":"aaaaaa","RoomNumber":0,"UserName":"C1","DepName":"赵兴昌测试","ComplementName":"阁楼","TrustDay":"已过期"}]
     */

    private int Type;
    private String Content;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * TradeType : 117
         * TradeTypeChild : 0
         * Orientation : 101
         * AreaId : 469
         * BuildingType : 89
         * CityId : 0
         * Decoration : 165
         * EstateId : 1
         * OwnerNationality : 250
         * PresentSituation : 156
         * PropertyRightType : 116
         * Purpose : 77
         * Source : 192
         * Tax : 112
         * DataStatus : 1137
         * StreetId : 2
         * TrustType : 205
         * Complement : 173
         * UserId : 4
         * DepId : 1
         * No : BM000011
         * TradeTypeName : 出租
         * TradeTypeChildName : null
         * BuildingPosition : www一单元
         * Floor : 3
         * RoomNo : 131
         * RoomType : 1室2厅0阳0卫
         * Square : 33
         * OrientationName : 南北
         * SaleTotal : 22
         * RentTotal : 33
         * BedroomCount : 1
         * BuildingTypeName : 多层
         * BuildYear : 0
         * ContactsName : 222
         * ContactsPhone : 33
         * CreatedTime : 2017-07-20T15:55:36.517
         * DecorationName : 豪装
         * FloorAll : 3
         * GiveUpData : 2017-07-14T00:00:00
         * Id : 2
         * LivingRoomCount : 2
         * MgtCost : 0
         * OwnerName : 111
         * OwnerNationalityName : 澳门
         * OwnerPhone : null
         * PresentSituationName : 空置
         * PropertyNo : null
         * PropertyRightTypeName : 商品房
         * PurposeName : 住宅
         * Remark : null
         * RentPrice : 33
         * SalePrice : 22
         * SourceName : 58同城
         * SquareUse : 44
         * DataStatusName : 有效
         * TaxName : 各付
         * ToiletCount : 0
         * TrustBeginData : 2017-07-01T00:00:00
         * TrustEndData : 2017-07-20T00:00:00
         * TrustTypeName : A-独家
         * VerandaCount : 0
         * AreaName : 和平区
         * StreetName : 123
         * EstateName : aaaaaa
         * RoomNumber : 0
         * UserName : C1
         * DepName : 赵兴昌测试
         * ComplementName : 阁楼
         * TrustDay : 已过期
         */

        private int TradeType;
        private int TradeTypeChild;
        private int Orientation;
        private int AreaId;
        private int BuildingType;
        private int CityId;
        private int Decoration;
        private int EstateId;
        private int OwnerNationality;
        private int PresentSituation;
        private int PropertyRightType;
        private int Purpose;
        private int Source;
        private int Tax;
        private int DataStatus;
        private int StreetId;
        private int TrustType;
        private int Complement;
        private int UserId;
        private int DepId;
        private String No;
        private String TradeTypeName;
        private Object TradeTypeChildName;
        private String BuildingPosition;
        private int Floor;
        private String RoomNo;
        private String RoomType;
        private int Square;
        private String OrientationName;
        private int SaleTotal;
        private int RentTotal;
        private int BedroomCount;
        private String BuildingTypeName;
        private int BuildYear;
        private String ContactsName;
        private String ContactsPhone;
        private String CreatedTime;
        private String DecorationName;
        private int FloorAll;
        private String GiveUpData;
        private int Id;
        private int LivingRoomCount;
        private int MgtCost;
        private String OwnerName;
        private String OwnerNationalityName;
        private Object OwnerPhone;
        private String PresentSituationName;
        private Object PropertyNo;
        private String PropertyRightTypeName;
        private String PurposeName;
        private Object Remark;
        private int RentPrice;
        private int SalePrice;
        private String SourceName;
        private int SquareUse;
        private String DataStatusName;
        private String TaxName;
        private int ToiletCount;
        private String TrustBeginData;
        private String TrustEndData;
        private String TrustTypeName;
        private int VerandaCount;
        private String AreaName;
        private String StreetName;
        private String EstateName;
        private int RoomNumber;
        private String UserName;
        private String DepName;
        private String ComplementName;
        private String TrustDay;

        public int getTradeType() {
            return TradeType;
        }

        public void setTradeType(int TradeType) {
            this.TradeType = TradeType;
        }

        public int getTradeTypeChild() {
            return TradeTypeChild;
        }

        public void setTradeTypeChild(int TradeTypeChild) {
            this.TradeTypeChild = TradeTypeChild;
        }

        public int getOrientation() {
            return Orientation;
        }

        public void setOrientation(int Orientation) {
            this.Orientation = Orientation;
        }

        public int getAreaId() {
            return AreaId;
        }

        public void setAreaId(int AreaId) {
            this.AreaId = AreaId;
        }

        public int getBuildingType() {
            return BuildingType;
        }

        public void setBuildingType(int BuildingType) {
            this.BuildingType = BuildingType;
        }

        public int getCityId() {
            return CityId;
        }

        public void setCityId(int CityId) {
            this.CityId = CityId;
        }

        public int getDecoration() {
            return Decoration;
        }

        public void setDecoration(int Decoration) {
            this.Decoration = Decoration;
        }

        public int getEstateId() {
            return EstateId;
        }

        public void setEstateId(int EstateId) {
            this.EstateId = EstateId;
        }

        public int getOwnerNationality() {
            return OwnerNationality;
        }

        public void setOwnerNationality(int OwnerNationality) {
            this.OwnerNationality = OwnerNationality;
        }

        public int getPresentSituation() {
            return PresentSituation;
        }

        public void setPresentSituation(int PresentSituation) {
            this.PresentSituation = PresentSituation;
        }

        public int getPropertyRightType() {
            return PropertyRightType;
        }

        public void setPropertyRightType(int PropertyRightType) {
            this.PropertyRightType = PropertyRightType;
        }

        public int getPurpose() {
            return Purpose;
        }

        public void setPurpose(int Purpose) {
            this.Purpose = Purpose;
        }

        public int getSource() {
            return Source;
        }

        public void setSource(int Source) {
            this.Source = Source;
        }

        public int getTax() {
            return Tax;
        }

        public void setTax(int Tax) {
            this.Tax = Tax;
        }

        public int getDataStatus() {
            return DataStatus;
        }

        public void setDataStatus(int DataStatus) {
            this.DataStatus = DataStatus;
        }

        public int getStreetId() {
            return StreetId;
        }

        public void setStreetId(int StreetId) {
            this.StreetId = StreetId;
        }

        public int getTrustType() {
            return TrustType;
        }

        public void setTrustType(int TrustType) {
            this.TrustType = TrustType;
        }

        public int getComplement() {
            return Complement;
        }

        public void setComplement(int Complement) {
            this.Complement = Complement;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getDepId() {
            return DepId;
        }

        public void setDepId(int DepId) {
            this.DepId = DepId;
        }

        public String getNo() {
            return No;
        }

        public void setNo(String No) {
            this.No = No;
        }

        public String getTradeTypeName() {
            return TradeTypeName;
        }

        public void setTradeTypeName(String TradeTypeName) {
            this.TradeTypeName = TradeTypeName;
        }

        public Object getTradeTypeChildName() {
            return TradeTypeChildName;
        }

        public void setTradeTypeChildName(Object TradeTypeChildName) {
            this.TradeTypeChildName = TradeTypeChildName;
        }

        public String getBuildingPosition() {
            return BuildingPosition;
        }

        public void setBuildingPosition(String BuildingPosition) {
            this.BuildingPosition = BuildingPosition;
        }

        public int getFloor() {
            return Floor;
        }

        public void setFloor(int Floor) {
            this.Floor = Floor;
        }

        public String getRoomNo() {
            return RoomNo;
        }

        public void setRoomNo(String RoomNo) {
            this.RoomNo = RoomNo;
        }

        public String getRoomType() {
            return RoomType;
        }

        public void setRoomType(String RoomType) {
            this.RoomType = RoomType;
        }

        public int getSquare() {
            return Square;
        }

        public void setSquare(int Square) {
            this.Square = Square;
        }

        public String getOrientationName() {
            return OrientationName;
        }

        public void setOrientationName(String OrientationName) {
            this.OrientationName = OrientationName;
        }

        public int getSaleTotal() {
            return SaleTotal;
        }

        public void setSaleTotal(int SaleTotal) {
            this.SaleTotal = SaleTotal;
        }

        public int getRentTotal() {
            return RentTotal;
        }

        public void setRentTotal(int RentTotal) {
            this.RentTotal = RentTotal;
        }

        public int getBedroomCount() {
            return BedroomCount;
        }

        public void setBedroomCount(int BedroomCount) {
            this.BedroomCount = BedroomCount;
        }

        public String getBuildingTypeName() {
            return BuildingTypeName;
        }

        public void setBuildingTypeName(String BuildingTypeName) {
            this.BuildingTypeName = BuildingTypeName;
        }

        public int getBuildYear() {
            return BuildYear;
        }

        public void setBuildYear(int BuildYear) {
            this.BuildYear = BuildYear;
        }

        public String getContactsName() {
            return ContactsName;
        }

        public void setContactsName(String ContactsName) {
            this.ContactsName = ContactsName;
        }

        public String getContactsPhone() {
            return ContactsPhone;
        }

        public void setContactsPhone(String ContactsPhone) {
            this.ContactsPhone = ContactsPhone;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String CreatedTime) {
            this.CreatedTime = CreatedTime;
        }

        public String getDecorationName() {
            return DecorationName;
        }

        public void setDecorationName(String DecorationName) {
            this.DecorationName = DecorationName;
        }

        public int getFloorAll() {
            return FloorAll;
        }

        public void setFloorAll(int FloorAll) {
            this.FloorAll = FloorAll;
        }

        public String getGiveUpData() {
            return GiveUpData;
        }

        public void setGiveUpData(String GiveUpData) {
            this.GiveUpData = GiveUpData;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getLivingRoomCount() {
            return LivingRoomCount;
        }

        public void setLivingRoomCount(int LivingRoomCount) {
            this.LivingRoomCount = LivingRoomCount;
        }

        public int getMgtCost() {
            return MgtCost;
        }

        public void setMgtCost(int MgtCost) {
            this.MgtCost = MgtCost;
        }

        public String getOwnerName() {
            return OwnerName;
        }

        public void setOwnerName(String OwnerName) {
            this.OwnerName = OwnerName;
        }

        public String getOwnerNationalityName() {
            return OwnerNationalityName;
        }

        public void setOwnerNationalityName(String OwnerNationalityName) {
            this.OwnerNationalityName = OwnerNationalityName;
        }

        public Object getOwnerPhone() {
            return OwnerPhone;
        }

        public void setOwnerPhone(Object OwnerPhone) {
            this.OwnerPhone = OwnerPhone;
        }

        public String getPresentSituationName() {
            return PresentSituationName;
        }

        public void setPresentSituationName(String PresentSituationName) {
            this.PresentSituationName = PresentSituationName;
        }

        public Object getPropertyNo() {
            return PropertyNo;
        }

        public void setPropertyNo(Object PropertyNo) {
            this.PropertyNo = PropertyNo;
        }

        public String getPropertyRightTypeName() {
            return PropertyRightTypeName;
        }

        public void setPropertyRightTypeName(String PropertyRightTypeName) {
            this.PropertyRightTypeName = PropertyRightTypeName;
        }

        public String getPurposeName() {
            return PurposeName;
        }

        public void setPurposeName(String PurposeName) {
            this.PurposeName = PurposeName;
        }

        public Object getRemark() {
            return Remark;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public int getRentPrice() {
            return RentPrice;
        }

        public void setRentPrice(int RentPrice) {
            this.RentPrice = RentPrice;
        }

        public int getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(int SalePrice) {
            this.SalePrice = SalePrice;
        }

        public String getSourceName() {
            return SourceName;
        }

        public void setSourceName(String SourceName) {
            this.SourceName = SourceName;
        }

        public int getSquareUse() {
            return SquareUse;
        }

        public void setSquareUse(int SquareUse) {
            this.SquareUse = SquareUse;
        }

        public String getDataStatusName() {
            return DataStatusName;
        }

        public void setDataStatusName(String DataStatusName) {
            this.DataStatusName = DataStatusName;
        }

        public String getTaxName() {
            return TaxName;
        }

        public void setTaxName(String TaxName) {
            this.TaxName = TaxName;
        }

        public int getToiletCount() {
            return ToiletCount;
        }

        public void setToiletCount(int ToiletCount) {
            this.ToiletCount = ToiletCount;
        }

        public String getTrustBeginData() {
            return TrustBeginData;
        }

        public void setTrustBeginData(String TrustBeginData) {
            this.TrustBeginData = TrustBeginData;
        }

        public String getTrustEndData() {
            return TrustEndData;
        }

        public void setTrustEndData(String TrustEndData) {
            this.TrustEndData = TrustEndData;
        }

        public String getTrustTypeName() {
            return TrustTypeName;
        }

        public void setTrustTypeName(String TrustTypeName) {
            this.TrustTypeName = TrustTypeName;
        }

        public int getVerandaCount() {
            return VerandaCount;
        }

        public void setVerandaCount(int VerandaCount) {
            this.VerandaCount = VerandaCount;
        }

        public String getAreaName() {
            return AreaName;
        }

        public void setAreaName(String AreaName) {
            this.AreaName = AreaName;
        }

        public String getStreetName() {
            return StreetName;
        }

        public void setStreetName(String StreetName) {
            this.StreetName = StreetName;
        }

        public String getEstateName() {
            return EstateName;
        }

        public void setEstateName(String EstateName) {
            this.EstateName = EstateName;
        }

        public int getRoomNumber() {
            return RoomNumber;
        }

        public void setRoomNumber(int RoomNumber) {
            this.RoomNumber = RoomNumber;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getDepName() {
            return DepName;
        }

        public void setDepName(String DepName) {
            this.DepName = DepName;
        }

        public String getComplementName() {
            return ComplementName;
        }

        public void setComplementName(String ComplementName) {
            this.ComplementName = ComplementName;
        }

        public String getTrustDay() {
            return TrustDay;
        }

        public void setTrustDay(String TrustDay) {
            this.TrustDay = TrustDay;
        }
    }
}
