package customerMybatis.session;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description:
 * @create: 2019-07-10 20:23
 **/
public class Mapper {
    private String sql;
    private String resultType;

    public String getSql() {
        return sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
