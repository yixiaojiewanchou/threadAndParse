package com.threadAndParse.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml配置文件读取工具类
 */
public class ParseXmlUtil {
    private static final Logger log = LoggerFactory.getLogger(ParseXmlUtil.class);

    //配置文件路径,注意：路径必须加"/"
    private static final String XML_FILE = "/mapper.xml";

    //存储xml解析结果
    private static Map<String, Map<String, String>> dateMap = new HashMap<>();

    static {
        loadXml();
    }

    public static void loadXml() {

        try (InputStream inputStream = ParseXmlUtil.class.getResourceAsStream(XML_FILE)) {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);

            //获取根节点
            Element root = document.getRootElement();
            List<Element> childElements = root.elements();

            for (Element element : childElements) {
                Map<String, String> map = new HashMap<>();
                List<Element> sunzis = element.elements();
                for (Element sunzi : sunzis) {
                    map.put(sunzi.getName(), sunzi.getText());
                }
                dateMap.put(element.getName(), map);
            }

        } catch (Exception e) {
            log.error("读取xml配置文件失败{}", e);
        }
    }

    public static Map<String, Map<String, String>> getDateMap() {
        if (null == dateMap) {
            loadXml();
        }
        return dateMap;
    }
}
