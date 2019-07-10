package customerMybatis.session;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description:
 * @create: 2019-07-10 11:38
 **/
public interface SqlSessionFactory {

    SqlSession openSession();
}
