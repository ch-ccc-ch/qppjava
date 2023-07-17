package org.myutils;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XMLtoTXT {
    public static void main(String[] args) {
        SAXBuilder saxBuilder = new SAXBuilder();
        File inputFile = new File("data/topics.401-450.xml");

        try {
            Document document = saxBuilder.build(inputFile);
            Element classElement = document.getRootElement();
            List<Element> topicList = classElement.getChildren();
            PrintWriter writer = new PrintWriter("401-450.txt", "UTF-8");

            for (int i = 0; i < topicList.size(); i++) {
                Element topic = topicList.get(i);
                String num = topic.getChild("num").getTextTrim();
                String desc = topic.getChild("desc").getTextTrim();

                // Remove newlines, carriage returns and tabs from desc
                desc = desc.replace("\n", "").replace("\r", "").replace("\t", " ");

                writer.println(num + "\t" + desc);
            }

            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
