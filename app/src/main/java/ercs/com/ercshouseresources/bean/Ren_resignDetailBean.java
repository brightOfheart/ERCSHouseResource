package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class Ren_resignDetailBean extends BaseBean {
    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public class DataBean {
        private String Style;
        private ModelBean Model;
        private List<ItemBean> Item;

        public String getStyle() {
            return Style;
        }

        public void setStyle(String style) {
            Style = style;
        }

        public ModelBean getModel() {
            return Model;
        }

        public void setModel(ModelBean model) {
            Model = model;
        }

        public List<ItemBean> getItem() {
            return Item;
        }

        public void setItem(List<ItemBean> item) {
            Item = item;
        }

        public class ModelBean {
            private String DecorationCompanyID;
            private String Name;
            private String ShowPrice;
            private String HouseType;
            private String Area;
            private String AreaTag;
            private String Style;
            private String TimeLimit;
            private String Designer;
            private String OpusType;
            private String Id;

            public String getDecorationCompanyID() {
                return DecorationCompanyID;
            }

            public void setDecorationCompanyID(String decorationCompanyID) {
                DecorationCompanyID = decorationCompanyID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getShowPrice() {
                return ShowPrice;
            }

            public void setShowPrice(String showPrice) {
                ShowPrice = showPrice;
            }

            public String getHouseType() {
                return HouseType;
            }

            public void setHouseType(String houseType) {
                HouseType = houseType;
            }

            public String getArea() {
                return Area;
            }

            public void setArea(String area) {
                Area = area;
            }

            public String getAreaTag() {
                return AreaTag;
            }

            public void setAreaTag(String areaTag) {
                AreaTag = areaTag;
            }

            public String getStyle() {
                return Style;
            }

            public void setStyle(String style) {
                Style = style;
            }

            public String getTimeLimit() {
                return TimeLimit;
            }

            public void setTimeLimit(String timeLimit) {
                TimeLimit = timeLimit;
            }

            public String getDesigner() {
                return Designer;
            }

            public void setDesigner(String designer) {
                Designer = designer;
            }

            public String getOpusType() {
                return OpusType;
            }

            public void setOpusType(String opusType) {
                OpusType = opusType;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }
        }

        public class ItemBean {
            private String title;
            private String img;
            private String comtent;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getComtent() {
                return comtent;
            }

            public void setComtent(String comtent) {
                this.comtent = comtent;
            }
        }
    }
}
