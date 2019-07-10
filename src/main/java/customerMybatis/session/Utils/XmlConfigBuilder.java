package customerMybatis.session.Utils;

import customerMybatis.io.Resources;
import customerMybatis.session.Configuration;
import customerMybatis.session.Mapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhaojiejun
 * @program: customerMybatis
 * @description: 解析主配置文件以及映射配置文件
 * @create: 2019-07-10 19:32
 **/
public class XmlConfigBuilder {
    /**
     * 使用dom4j和 xpath 解析主配置文件
     *
     * @param inputStream
     */
    public static Configuration loadSqlMapConfig(InputStream inputStream) {
        //创建SaxReader对象
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            List<Element> selectNodes = document.selectNodes("//property");
            Configuration configuration = new Configuration();
            for (Element list : selectNodes) {
                String name = list.attributeValue("name");
                String value = list.attributeValue("value");
                //封装成对象
                if ("driver".equals(name)) {
                    configuration.setDriver(value);
                }
                if ("url".equals(name)) {
                    configuration.setUrl(value);
                }
                if ("username".equals(name)) {
                    configuration.setUsername(value);
                }
                if ("password".equals(name)) {
                    configuration.setPassword(value);
                }
            }
            //获取所有的mapper标签
            List<Element> mapperElements = document.selectNodes("//mapper");
            for (Element element : mapperElements) {
                String resource = element.attributeValue("resource");
                //解析映射配置文件
                Map<String, Mapper> mapper = loadMapper(resource);
                configuration.setMappers(mapper);
            }
            return configuration;
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 解析映射配置文件
     *
     * @param resource
     */
    private static Map<String, Mapper> loadMapper(String resource) {
        Map<String, Mapper> map = new HashMap();
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = Resources.getResourceAsStream(resource);
        try {
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            //获取 namespace 标签值
            String namespace = rootElement.attributeValue("namespace");
            //获取select标签
            List<Element> selectNodes = document.selectNodes("//select");
            for (Element element : selectNodes) {
                String id = element.attributeValue("id");
                String resultType = element.attributeValue("resultType");
                String sql = element.getTextTrim();
                Mapper mapper = new Mapper();
                mapper.setSql(sql);
                mapper.setResultType(resultType);
                //通过nameSpace + 方法名 作为唯一标识
                map.put(namespace + "." + id, mapper);
            }
            return map;
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
