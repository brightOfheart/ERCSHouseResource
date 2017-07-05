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

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public class DataBean {
        private String UserId;
        private String StatisticsType;
        private String StatisticsTypeName;
        private String TypeExplain;
        private String StatisticsDateTime;
        private String StatisticsDateTimeExplain;
        private String StatisticsExplain;
        private String ParamsDate;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getStatisticsType() {
            return StatisticsType;
        }

        public void setStatisticsType(String statisticsType) {
            StatisticsType = statisticsType;
        }

        public String getStatisticsTypeName() {
            return StatisticsTypeName;
        }

        public void setStatisticsTypeName(String statisticsTypeName) {
            StatisticsTypeName = statisticsTypeName;
        }

        public String getTypeExplain() {
            return TypeExplain;
        }

        public void setTypeExplain(String typeExplain) {
            TypeExplain = typeExplain;
        }

        public String getStatisticsDateTime() {
            return StatisticsDateTime;
        }

        public void setStatisticsDateTime(String statisticsDateTime) {
            StatisticsDateTime = statisticsDateTime;
        }

        public String getStatisticsDateTimeExplain() {
            return StatisticsDateTimeExplain;
        }

        public void setStatisticsDateTimeExplain(String statisticsDateTimeExplain) {
            StatisticsDateTimeExplain = statisticsDateTimeExplain;
        }

        public String getStatisticsExplain() {
            return StatisticsExplain;
        }

        public void setStatisticsExplain(String statisticsExplain) {
            StatisticsExplain = statisticsExplain;
        }

        public String getParamsDate() {
            return ParamsDate;
        }

        public void setParamsDate(String paramsDate) {
            ParamsDate = paramsDate;
        }
    }
}
