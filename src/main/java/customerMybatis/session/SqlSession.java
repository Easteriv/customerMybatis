package customerMybatis.session;

import customerMybatis.dao.UserMapper;

import java.util.List;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description:
 * @create: 2019-07-10 11:40
 **/
public interface SqlSession {

    <E> E getMapper(Class<E> daoClass);

    void close();

    /**
     * 执行查询多条数据的sql
     * @param key
     * @param <E>
     * @return
     */
    <E> List<E> selectList(String key);
}
