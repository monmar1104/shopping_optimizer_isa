package com.infoshareacademy.czerwoni;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ParseXML {

    public static void main(String args[]) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {

            File fXmlFile = new File("Categories.xml");
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            NodeList questList = doc.getElementsByTagName("quest-item");
            NodeList catList = doc.getElementsByTagName("cat-item");
            for (int k=0;k<questList.getLength();k++) {
                Node questItem = questList.item(k);
                Element quest = (Element) questItem;
                System.out.println(quest.getTextContent());
                for (int i = 0; i < catList.getLength(); i++) {
                    Node catItem = catList.item(i);
                    if (catItem.getNodeType() == Node.ELEMENT_NODE) {
                        Element category = (Element) catItem;
                        NodeList itemList = category.getChildNodes();
                        for (int j = 0; j < itemList.getLength(); j++) {
                            Node details = itemList.item(j);
                            if (details.getNodeType() == Node.ELEMENT_NODE) {
                                Element itemName = (Element) details;
                                //System.out.println(itemName.getTagName()+" : "+itemName.getTextContent());

                                if(quest.getAttribute("id").equals(itemName.getAttribute("question"))) {

                                        System.out.println(itemName.getTextContent());

                                }

                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e){
            e.printStackTrace();
        }
        catch (SAXException e){
            e.printStackTrace();
        }
    }

}

