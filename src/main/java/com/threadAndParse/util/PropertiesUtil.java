package com.threadAndParse.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * properties配置文件读取工具类
 */
public class PropertiesUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties properties;

    //配置文件路径
    private static final String PROPERTIES_FILE = "mapper.properties";

    private PropertiesUtil(){}

    static {
        loadProperties();
    }

    //加载配置数据
    private static void loadProperties(){
        if(null==properties){
            properties=new Properties();
        }

        try(InputStream is=PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)){
            properties.load(is);
        } catch (Exception e) {
            log.error("加载properties文件失败{}",e);
        }
    }

    /**
     * 获取配置属性值
     *
     * @param proName  属性名称
     * @return  属性值
     */
    public static String get(String proName){
        if( null == properties){
            loadProperties();
        }

        return properties.getProperty(proName);
    }

}
