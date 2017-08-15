package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class RenSelectListBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private List<HouseTypeBean> HouseType;
        private List<StyleBean> Style;
        private List<AreaTagBean> AreaTag;

        public List<HouseTypeBean> getHouseType() {
            return HouseType;
        }

        public void setHouseType(List<HouseTypeBean> houseType) {
            HouseType = houseType;
        }

        public List<StyleBean> getStyle() {
            return Style;
        }

        public void setStyle(List<StyleBean> style) {
            Style = style;
        }

        public List<AreaTagBean> getAreaTag() {
            return AreaTag;
        }

        public void setAreaTag(List<AreaTagBean> areaTag) {
            AreaTag = areaTag;
        }

        public class HouseTypeBean {
            private String Name;
            private String DecorationCaseHouseType;
            private String Value;
            private String Remark;
            private String ShowIndex;
            private String Id;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getDecorationCaseHouseType() {
                return DecorationCaseHouseType;
            }

            public void setDecorationCaseHouseType(String decorationCaseHouseType) {
                DecorationCaseHouseType = decorationCaseHouseType;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String value) {
                Value = value;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String remark) {
                Remark = remark;
            }

            public String getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(String showIndex) {
                ShowIndex = showIndex;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }
        }

        public class StyleBean {
            private String Name;
            private String Value;
            private String Remark;
            private String ShowIndex;
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

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String remark) {
                Remark = remark;
            }

            public String getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(String showIndex) {
                ShowIndex = showIndex;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }
        }

        public class AreaTagBean {
            private String Name;
            private String Value;
            private String Id;
            private String Remark;
            private String ShowIndex;

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

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String remark) {
                Remark = remark;
            }

            public String getShowIndex() {
                return ShowIndex;
            }

            public void setShowIndex(String showIndex) {
                ShowIndex = showIndex;
            }
        }
    }
}
