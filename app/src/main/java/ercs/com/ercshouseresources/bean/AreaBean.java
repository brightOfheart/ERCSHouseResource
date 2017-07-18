package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class AreaBean {

    /**
     * Type : 1
     * Content : 查询成功
     * Data : [{"Id":469,"Name":"和平区","ParentId":468},{"Id":470,"Name":"沈河区","ParentId":468},{"Id":471,"Name":"大东区","ParentId":468},{"Id":472,"Name":"皇姑区","ParentId":468},{"Id":473,"Name":"铁西区","ParentId":468},{"Id":474,"Name":"东陵区","ParentId":468},{"Id":475,"Name":"苏家屯区","ParentId":468},{"Id":476,"Name":"浑南区","ParentId":468},{"Id":477,"Name":"沈北新区","ParentId":468},{"Id":478,"Name":"于洪区","ParentId":468},{"Id":479,"Name":"辽中县","ParentId":468},{"Id":480,"Name":"康平县","ParentId":468},{"Id":481,"Name":"法库县","ParentId":468},{"Id":482,"Name":"新民市","ParentId":468},{"Id":1,"Name":"aaaa","ParentId":482},{"Id":2,"Name":"123","ParentId":469},{"Id":5,"Name":"111","ParentId":475},{"Id":6,"Name":"1234","ParentId":469},{"Id":1006,"Name":"123333","ParentId":469},{"Id":1007,"Name":"5555","ParentId":475},{"Id":1008,"Name":"1123","ParentId":473}]
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
        public DataBean(int id, String name, int parentId) {
            Id = id;
            Name = name;
            ParentId = parentId;
        }

        /**
         * Id : 469
         * Name : 和平区
         * ParentId : 468
         */

        private int Id;
        private String Name;
        private int ParentId;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }
    }
}
