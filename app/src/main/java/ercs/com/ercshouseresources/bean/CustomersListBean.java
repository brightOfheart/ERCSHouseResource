package ercs.com.ercshouseresources.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 客户列表
 * Created by Administrator on 2017/7/28.
 */

public class CustomersListBean implements Serializable {

    /**
     * Type : 1
     * Content : 成功
     * Data : [{"Id":2,"Name":"100","Remark":"","Sex":0,"PhoneList":[{"CustomerPhoneID":3,"Phone":"123","Remark":null},{"CustomerPhoneID":4,"Phone":"456","Remark":null}]},{"Id":1,"Name":"李磊","Remark":"哈哈哈","Sex":0,"PhoneList":[{"CustomerPhoneID":1,"Phone":"13888888888","Remark":"0000"},{"CustomerPhoneID":2,"Phone":"13999999999","Remark":"000"}]}]
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

    public static class DataBean implements Serializable{
        /**
         * Id : 2
         * Name : 100
         * Remark :
         * Sex : 0
         * PhoneList : [{"CustomerPhoneID":3,"Phone":"123","Remark":null},{"CustomerPhoneID":4,"Phone":"456","Remark":null}]
         */

        private int Id;
        private String Name;
        private String Remark;
        private int Sex;
        private List<PhoneListBean> PhoneList;

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

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public int getSex() {
            return Sex;
        }

        public void setSex(int Sex) {
            this.Sex = Sex;
        }

        public List<PhoneListBean> getPhoneList() {
            return PhoneList;
        }

        public void setPhoneList(List<PhoneListBean> PhoneList) {
            this.PhoneList = PhoneList;
        }

        public static class PhoneListBean implements Serializable{
            /**
             * CustomerPhoneID : 3
             * Phone : 123
             * Remark : null
             */

            private int CustomerPhoneID;
            private String Phone;
            private Object Remark;

            public int getCustomerPhoneID() {
                return CustomerPhoneID;
            }

            public void setCustomerPhoneID(int CustomerPhoneID) {
                this.CustomerPhoneID = CustomerPhoneID;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public Object getRemark() {
                return Remark;
            }

            public void setRemark(Object Remark) {
                this.Remark = Remark;
            }
        }
    }
}
