package com.jpmorgan.csv.util;

import com.jpmorgan.csv.metadata.MetaData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class XmlUtil {


    /**
     * Get the MetaData Object from the filename
     * @param filename
     * @return
     */
    public static List<MetaData> getMetaList(String filename) {
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("column");
            List<MetaData> metaDataLinkedList = new LinkedList<>();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    MetaData metaData = new MetaData();
                    metaData.setColumn(eElement.getElementsByTagName("name").item(0).getTextContent());
                    metaData.setSortOrder(eElement.getElementsByTagName("order").item(0).getTextContent());
                    metaData.setPriority(Integer.valueOf(eElement.getElementsByTagName("priority").item(0).getTextContent()));
                    metaDataLinkedList.add(metaData);
                }
            }
            metaDataLinkedList.sort(new Comparator<MetaData>() {
                @Override
                public int compare(MetaData o1, MetaData o2) {
                    return o1.getPriority().compareTo(o2.getPriority());
                }
            });
            return metaDataLinkedList;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
}
