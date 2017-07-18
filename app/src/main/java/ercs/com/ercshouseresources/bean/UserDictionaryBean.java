package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * 获取房源类型列表
 * Created by Administrator on 2017/7/17.
 */

public class UserDictionaryBean {

    /**
     * Type : 1
     * Content : 查询成功
     * Data : {"Purpose":[{"Id":678,"Text":""},{"Id":77,"Text":"住宅"},{"Id":78,"Text":"商住"},{"Id":79,"Text":"商铺"},{"Id":80,"Text":"写字间"},{"Id":81,"Text":"厂房"},{"Id":82,"Text":"仓库"},{"Id":83,"Text":"地皮"},{"Id":84,"Text":"车位"},{"Id":85,"Text":"车库"},{"Id":86,"Text":"其他"}],"BuildingType":[{"Id":679,"Text":""},{"Id":89,"Text":"多层"},{"Id":90,"Text":"高层"},{"Id":91,"Text":"小高层"},{"Id":92,"Text":"多层复式"},{"Id":93,"Text":"高层复式 "},{"Id":94,"Text":"多层跃式 "},{"Id":95,"Text":"高层跃式"},{"Id":96,"Text":"独立别墅"},{"Id":97,"Text":"联体别墅"},{"Id":98,"Text":"双拼别墅"},{"Id":99,"Text":"裙楼"},{"Id":100,"Text":"四合院"}],"Orientation":[{"Id":680,"Text":""},{"Id":101,"Text":"南北"},{"Id":102,"Text":"东西"},{"Id":103,"Text":"南"},{"Id":104,"Text":"北"},{"Id":105,"Text":"东"},{"Id":106,"Text":"西"},{"Id":107,"Text":"东南"},{"Id":108,"Text":"西南"},{"Id":109,"Text":"东北"},{"Id":110,"Text":"西北"},{"Id":111,"Text":"不限"}],"DataStuse":[{"Id":1137,"Text":"有效"},{"Id":1138,"Text":"已租"},{"Id":1139,"Text":"已售"},{"Id":1140,"Text":"无效"}],"TradeType":[{"Id":117,"Text":"出租"},{"Id":118,"Text":"出售"},{"Id":119,"Text":"租售"}],"TradeTypeChild":[{"Id":1133,"Text":"单间出租"},{"Id":1134,"Text":"整租"},{"Id":1135,"Text":"合租"}]}
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
        private List<PurposeBean> Purpose;
        private List<BuildingTypeBean> BuildingType;
        private List<OrientationBean> Orientation;
        private List<DataStuseBean> DataStuse;
        private List<TradeTypeBean> TradeType;
        private List<TradeTypeChildBean> TradeTypeChild;

        public List<PurposeBean> getPurpose() {
            return Purpose;
        }

        public void setPurpose(List<PurposeBean> Purpose) {
            this.Purpose = Purpose;
        }

        public List<BuildingTypeBean> getBuildingType() {
            return BuildingType;
        }

        public void setBuildingType(List<BuildingTypeBean> BuildingType) {
            this.BuildingType = BuildingType;
        }

        public List<OrientationBean> getOrientation() {
            return Orientation;
        }

        public void setOrientation(List<OrientationBean> Orientation) {
            this.Orientation = Orientation;
        }

        public List<DataStuseBean> getDataStuse() {
            return DataStuse;
        }

        public void setDataStuse(List<DataStuseBean> DataStuse) {
            this.DataStuse = DataStuse;
        }

        public List<TradeTypeBean> getTradeType() {
            return TradeType;
        }

        public void setTradeType(List<TradeTypeBean> TradeType) {
            this.TradeType = TradeType;
        }

        public List<TradeTypeChildBean> getTradeTypeChild() {
            return TradeTypeChild;
        }

        public void setTradeTypeChild(List<TradeTypeChildBean> TradeTypeChild) {
            this.TradeTypeChild = TradeTypeChild;
        }

        public static class PurposeBean {
            /**
             * Id : 678
             * Text :
             */

            private int Id;
            private String Text;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }
        }

        public static class BuildingTypeBean {
            /**
             * Id : 679
             * Text :
             */

            private int Id;
            private String Text;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }
        }

        public static class OrientationBean {
            /**
             * Id : 680
             * Text :
             */

            private int Id;
            private String Text;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }
        }

        public static class DataStuseBean {
            /**
             * Id : 1137
             * Text : 有效
             */

            private int Id;
            private String Text;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }
        }

        public static class TradeTypeBean {
            /**
             * Id : 117
             * Text : 出租
             */

            private int Id;
            private String Text;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }
        }

        public static class TradeTypeChildBean {
            /**
             * Id : 1133
             * Text : 单间出租
             */

            private int Id;
            private String Text;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }
        }
    }
}
