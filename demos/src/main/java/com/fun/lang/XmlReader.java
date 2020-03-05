package com.fun.lang;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * xml解析测试
 *
 * @author yehuan
 * @version v1.0.0
 * @date 2017-03-09 15:39
 */
public class XmlReader {

	public static void main(String[] args) throws Exception {

		new XmlReader().test();

	}

	public void test() throws Exception {

		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		File xmlFile = new File(path+"conf.xml");
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document doc = builder.parse(xmlFile);

		Element root = doc.getDocumentElement();
		System.out.println("Root element: "+root.getNodeName());

		NodeList nodes = root.getChildNodes();
		System.out.println(nodes.getLength());

		for (int i = 0; i < nodes.getLength(); i++) {

            System.out.println("nodeName=" + nodes.item(i).getNodeName()+"___end");
            // 上面打印的length是11，tab/回车/空格都被当成子节点了，所以只处理是Element类型的节点
		    if(/*nodes.item(i) instanceof Element*/nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nodes.item(i);
                System.out.println("nodename = " + e.getNodeName() + " ,attribute name = " + e.getAttribute("name"));
            }
        }
	}

}
