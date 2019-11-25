package com.ibm;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class XmlAdd{
	public static void main(String[] args){
		try {
			new XmlAdd().exmple();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//example is attemp to add new dependency node onto dependencies
    public void exmple() throws Exception{
  
        SAXReader reader = new SAXReader();
        File file = new File(System.getProperty("user.dir")+"/pom.xml");
        Document document = reader.read(file );
 
        Element rootElem = document.getRootElement();
 
         Element dependenciesElem = rootElem.element("dependencies");
         Element newDepElement = dependenciesElem.addElement("dependency");
         //add new group node to target dependenciesElem node
         Element newGroupElement = newDepElement.addElement("group");
         newGroupElement.setText("org.springframework.boot");
         //add new ArtifactId node to target dependenciesElem node
         Element newArtifactIdElement = newDepElement.addElement("artifactId");
         newArtifactIdElement.setText("spring-boot-starter-test");
         //add new Version node to target dependenciesElem node
         Element newVersionElement = newDepElement.addElement("version");
         newVersionElement.setText("1.2.3");

        XMLWriter writer = new XMLWriter(new FileWriter(System.getProperty("user.dir")+"/pom.xml"));
        writer.write(document);
        writer.close();
 
    }
}