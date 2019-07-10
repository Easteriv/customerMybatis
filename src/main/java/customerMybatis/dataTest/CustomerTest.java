package customerMybatis.dataTest;

import customerMybatis.dao.UserMapper;
import customerMybatis.entity.User;
import customerMybatis.io.Resources;
import customerMybatis.session.SqlSession;
import customerMybatis.session.SqlSessionFactory;
import customerMybatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description:
 * @create: 2019-07-10 19:11
 **/
public class CustomerTest {
    public static void main(String[] args) {
        InputStream is = null;
        SqlSession sqlSession = null;
        try {
            //读取主配置文件
            is = Resources.getResourceAsStream("SqlMapConfig.xml");
            //创建sqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //使用构造着模式，创建sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            //工厂模式 获取 sqlSession
            sqlSession = sqlSessionFactory.openSession();
            //代理模式，获取mapper接口操作statement
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.findAll();
            for (User user : userList) {
                System.out.println("姓名是=========" + user.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
