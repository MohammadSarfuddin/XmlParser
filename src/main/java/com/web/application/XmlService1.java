package com.web.application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class XmlService1 {


	public void parsingXmlFile(File file) {
		List<String> valueList = new ArrayList<>();
		Map<String, String> nodeAttributeMap = new HashMap<>();
		StringBuilder parentNode2 = new StringBuilder();
		StringBuilder column = new StringBuilder();
		String parentNode1 = "";

		List<String> columnList = new ArrayList<>();

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);

			NodeList nodeList = doc.getChildNodes();

			for (int count = 0; count < nodeList.getLength(); count++) {

				Node tempNode = nodeList.item(count);

// make sure it's element node.
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
// get root node or parent node
					parentNode1 = tempNode.getNodeName();
					if (tempNode.hasAttributes()) {

// get attributes names and values
						NamedNodeMap nodeMap = tempNode.getAttributes();
						for (int i = 0; i < nodeMap.getLength(); i++) {
							Node node = nodeMap.item(i);
							nodeAttributeMap.put(node.getNodeName(), node.getNodeValue());
						}
					}
				}
			}

			Node firstChildNodeList = doc.getFirstChild();

			NodeList nl = firstChildNodeList.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				if (node.hasChildNodes()) {
// second parent node or outer node
					if (!parentNode2.toString().equalsIgnoreCase(node.getNodeName())) {
						parentNode2.append(node.getNodeName());
					}
					readChildNodes(node, valueList, column);
				}
			}

		} catch (Exception e) {
		}

		String columnNames = column.toString().substring(0, column.toString().length() - 1);
		String columns[] = columnNames.split(",");

		for (int i = 0; i < columns.length; i++) {
			columnList.add(columns[i]);
		}

		columnList.clear();
		columnList = null;
	}

// this method for getting inner node
	public String readChildNodes(Node node, List<String> valueList, StringBuilder column) {
		NodeList nl = node.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node innernode = nl.item(i);
			if (innernode.hasChildNodes()) {
				if (!column.toString().contains(innernode.getNodeName())) {
// get element node name
					column.append(innernode.getNodeName());
					column.append(",");
				}
// get element node value
				valueList.add(innernode.getTextContent());
				readChildNodes(innernode, valueList, column);
			}
		}

		return column.toString();
	}
}