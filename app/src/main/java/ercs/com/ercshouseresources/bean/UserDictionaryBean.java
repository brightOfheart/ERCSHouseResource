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
     * Data : [{"id":177,"name":"Appliances","text":"  "},{"id":178,"name":"Appliances","text":"可配"}]
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
        public DataBean(int id, String name, String text) {
            this.id = id;
            this.name = name;
            this.text = text;
        }

        /**
         * id : 177
         * name : Appliances
         * text :
         */


        private int id;
        private String name;
        private String text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
