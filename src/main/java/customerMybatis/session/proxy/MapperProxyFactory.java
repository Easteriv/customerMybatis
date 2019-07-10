package customerMybatis.session.proxy;

import customerMybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description:
 * @create: 2019-07-10 21:35
 **/
public class MapperProxyFactory implements InvocationHandler {
    private SqlSession sqlSession;

    public MapperProxyFactory(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        //只有返回值类型为List时才执行
        if(returnType==List.class){
            String methodName = method.getName();
            Class<?> clazz = method.getDeclaringClass();
            String clazzName = clazz.getName();
            String key = clazzName+"."+methodName;
            List<Object> list = sqlSession.selectList(key);
            return list;
        }
        else {
            //其他情况日后考虑，暂时查询多条数据
            return null;
        }

    }
}
