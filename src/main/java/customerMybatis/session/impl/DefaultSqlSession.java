package customerMybatis.session.impl;

import customerMybatis.session.Configuration;
import customerMybatis.session.Mapper;
import customerMybatis.session.SqlSession;
import customerMybatis.session.Utils.ReflectUtils;
import customerMybatis.session.proxy.MapperProxyFactory;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description:
 * @create: 2019-07-10 11:53
 **/
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    /**
     * 执行查询多条sql语句
     * @param key
     * @param <E>
     * @return
     */
    public <E> List<E> selectList(String key) {
        try {
            //获取连接对象
            Connection connection = configuration.getConnection();
            //获取sql语句
            Map<String, Mapper> mappers = configuration.getMappers();
            Mapper mapper = mappers.get(key);
            String sql = mapper.getSql();
            //获取javaBean全限定名
            String resultType = mapper.getResultType();
            //预编译sql语句
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //执行查询
            ResultSet resultSet = preparedStatement.executeQuery();
            //将结果集封装到javabean---->反射技术
            List<E> objects = ReflectUtils.converList(resultSet, Class.forName(resultType));
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 使用动态代理获取代理对象
     * @param daoClass
     * @param <E>
     * @return
     */
    public <E> E getMapper(Class<E> daoClass) {
        //传入三个参数：classloader类加载器 ，代理对象实现的接口的数组，invocationHandler接口的实现类对象
        return (E)Proxy.newProxyInstance(daoClass.getClassLoader(),new Class[]{daoClass},new MapperProxyFactory(this));
    }

    public void close() {

    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
