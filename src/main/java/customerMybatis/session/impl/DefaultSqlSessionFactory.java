package customerMybatis.session.impl;

import customerMybatis.session.Configuration;
import customerMybatis.session.SqlSession;
import customerMybatis.session.SqlSessionFactory;
import customerMybatis.session.Utils.XmlConfigBuilder;

import java.io.InputStream;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description:
 * @create: 2019-07-10 11:54
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private InputStream inputStream;

    public SqlSession openSession() {
        DefaultSqlSession sqlSession = new DefaultSqlSession();
        //开始解析主配置文件
        Configuration configuration = XmlConfigBuilder.loadSqlMapConfig(inputStream);
        sqlSession.setConfiguration(configuration);
        return sqlSession;
    }

    public void setIs(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
