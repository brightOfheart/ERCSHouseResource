package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class FinancialListBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String text;
        private String id;
        private String icon;
        private String mode;
        private String children;
        private List<attrBean> attr;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getChildren() {
            return children;
        }

        public void setChildren(String children) {
            this.children = children;
        }

        public List<attrBean> getAttr() {
            return attr;
        }

        public void setAttr(List<attrBean> attr) {
            this.attr = attr;
        }

        public class attrBean {
            private String Id;
            private String AttrKey;
            private String AttrValue;
            private String Name;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public String getAttrKey() {
                return AttrKey;
            }

            public void setAttrKey(String attrKey) {
                AttrKey = attrKey;
            }

            public String getAttrValue() {
                return AttrValue;
            }

            public void setAttrValue(String attrValue) {
                AttrValue = attrValue;
            }


        }
    }
}
