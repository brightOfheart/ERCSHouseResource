package ercs.com.ercshouseresources.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 * 封装考勤统计的Bean
 */

public class AtendanceBean extends BaseBean {

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * TypeExplain : 5次
         * StatisticsType : 6
         * StatisticsTypeName : 旷工
         * InSideStatisticsListMode : [{"Id":19,"UserId":2009,"TypeExplain":"5次","StatisticsType":6,"StatisticsTypeName":"旷工","StatisticsDateTime":"2017-07-06(星期四)","StatisticsDateTimeExplain":"","StatisticsExplain":"","ParamsDate":"sss"},{"Id":29,"UserId":2009,"TypeExplain":"5次","StatisticsType":6,"StatisticsTypeName":"旷工","StatisticsDateTime":"2017-07-07(星期五)","StatisticsDateTimeExplain":"","StatisticsExplain":"","ParamsDate":null},{"Id":39,"UserId":2009,"TypeExplain":"5次","StatisticsType":6,"StatisticsTypeName":"旷工","StatisticsDateTime":"2017-07-11(星期二)","StatisticsDateTimeExplain":"","StatisticsExplain":"","ParamsDate":null},{"Id":84,"UserId":2009,"TypeExplain":"5次","StatisticsType":6,"StatisticsTypeName":"旷工","StatisticsDateTime":"2017-07-12(星期三)","StatisticsDateTimeExplain":"","StatisticsExplain":"","ParamsDate":null},{"Id":95,"UserId":2009,"TypeExplain":"5次","StatisticsType":6,"StatisticsTypeName":"旷工","StatisticsDateTime":"2017-07-13(星期四)","StatisticsDateTimeExplain":"","StatisticsExplain":"","ParamsDate":null}]
         */

        private String TypeExplain;
        private int StatisticsType;
        private String StatisticsTypeName;
        private List<InSideStatisticsListModeBean> InSideStatisticsListMode;

        public String getTypeExplain() {
            return TypeExplain;
        }

        public void setTypeExplain(String TypeExplain) {
            this.TypeExplain = TypeExplain;
        }

        public String getStatisticsType() {
            return StatisticsType+"";
        }

        public void setStatisticsType(int StatisticsType) {
            this.StatisticsType = StatisticsType;
        }

        public String getStatisticsTypeName() {
            return StatisticsTypeName;
        }

        public void setStatisticsTypeName(String StatisticsTypeName) {
            this.StatisticsTypeName = StatisticsTypeName;
        }

        public List<InSideStatisticsListModeBean> getInSideStatisticsListMode() {
            return InSideStatisticsListMode;
        }

        public void setInSideStatisticsListMode(List<InSideStatisticsListModeBean> InSideStatisticsListMode) {
            this.InSideStatisticsListMode = InSideStatisticsListMode;
        }

        public static class InSideStatisticsListModeBean {
            /**
             * Id : 19
             * UserId : 2009
             * TypeExplain : 5次
             * StatisticsType : 6
             * StatisticsTypeName : 旷工
             * StatisticsDateTime : 2017-07-06(星期四)
             * StatisticsDateTimeExplain :
             * StatisticsExplain :
             * ParamsDate : sss
             */

            private int Id;
            private int UserId;
            private String TypeExplain;
            private int StatisticsType;
            private String StatisticsTypeName;
            private String StatisticsDateTime;
            private String StatisticsDateTimeExplain;
            private String StatisticsExplain;
            private String ParamsDate;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public String getTypeExplain() {
                return TypeExplain;
            }

            public void setTypeExplain(String TypeExplain) {
                this.TypeExplain = TypeExplain;
            }

            public int getStatisticsType() {
                return StatisticsType;
            }

            public void setStatisticsType(int StatisticsType) {
                this.StatisticsType = StatisticsType;
            }

            public String getStatisticsTypeName() {
                return StatisticsTypeName;
            }

            public void setStatisticsTypeName(String StatisticsTypeName) {
                this.StatisticsTypeName = StatisticsTypeName;
            }

            public String getStatisticsDateTime() {
                return StatisticsDateTime;
            }

            public void setStatisticsDateTime(String StatisticsDateTime) {
                this.StatisticsDateTime = StatisticsDateTime;
            }

            public String getStatisticsDateTimeExplain() {
                return StatisticsDateTimeExplain;
            }

            public void setStatisticsDateTimeExplain(String StatisticsDateTimeExplain) {
                this.StatisticsDateTimeExplain = StatisticsDateTimeExplain;
            }

            public String getStatisticsExplain() {
                return StatisticsExplain;
            }

            public void setStatisticsExplain(String StatisticsExplain) {
                this.StatisticsExplain = StatisticsExplain;
            }

            public String getParamsDate() {
                return ParamsDate;
            }

            public void setParamsDate(String ParamsDate) {
                this.ParamsDate = ParamsDate;
            }
        }
    }
}
