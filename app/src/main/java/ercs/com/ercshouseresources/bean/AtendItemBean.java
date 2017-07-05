package ercs.com.ercshouseresources.bean;

/**
 * Created by Administrator on 2017/6/27.
 * 封装考勤统计从新解析的数据
 */

public class AtendItemBean {
    private String StatisticsTypeName;
    private String TypeExplain;
    private String StatisticsType;

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
}
