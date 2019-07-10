package customerMybatis.io;

import java.io.InputStream;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description: 将配置文件转换成流
 * @create: 2019-07-10 11:32
 **/
public class Resources {
    public static InputStream getResourceAsStream(String path) {
        //类加载器将文件转换成流
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
