package customerMybatis.session;

import customerMybatis.session.impl.DefaultSqlSessionFactory;

import java.io.InputStream;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description:
 * @create: 2019-07-10 11:37
 **/
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory();
        sqlSessionFactory.setIs(inputStream);
        return sqlSessionFactory;
    }
}
