package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */

public class HouseBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private List<HouseTypeBean> HouseType;

        public List<HouseTypeBean> getHouseType() {
            return HouseType;
        }

        public void setHouseType(List<HouseTypeBean> houseType) {
            HouseType = houseType;
        }

        public List<BuildingsTypeBean> getBuildingsType() {
            return BuildingsType;
        }

        public void setBuildingsType(List<BuildingsTypeBean> buildingsType) {
            BuildingsType = buildingsType;
        }

        public List<DecorationConditionBean> getDecorationCondition() {
            return DecorationCondition;
        }

        public void setDecorationCondition(List<DecorationConditionBean> decorationCondition) {
            DecorationCondition = decorationCondition;
        }

        public class HouseTypeBean {
            private String Name;
            private String Value;
            private String Id;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String value) {
                Value = value;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }
        }

        private List<BuildingsTypeBean> BuildingsType;

        public class BuildingsTypeBean {
            private String Name;
            private String Value;
            private String Id;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String value) {
                Value = value;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }
        }

        private List<DecorationConditionBean> DecorationCondition;

        public class DecorationConditionBean {
            private String Name;
            private String Value;
            private String Id;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String value) {
                Value = value;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }
        }
    }


}
