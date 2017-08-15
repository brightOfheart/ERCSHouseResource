package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class RenListBean extends BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String id;
        private String CaseImagePath;
        private String Name;
        private String Content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCaseImagePath() {
            return CaseImagePath;
        }

        public void setCaseImagePath(String caseImagePath) {
            CaseImagePath = caseImagePath;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }
    }
}
