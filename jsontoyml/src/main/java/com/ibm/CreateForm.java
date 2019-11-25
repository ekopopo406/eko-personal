package com.ibm;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import com.ibm.model.ProjectNode;

public class CreateForm {
  //  private static final String FILE_NAME = "C://xmltest/src/config//htmltemplate.xml";

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
 
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public ProjectNode loadekotest(String FILE_NAME) throws IOException{
    	
    	  FileInputStream is = null;
          try {
              is = new FileInputStream(FILE_NAME);
              ProjectNode formNode = (ProjectNode) this.unmarshaller.unmarshal(new StreamSource(is));
              return formNode;
          } finally {
              if (is != null) {
                  is.close();
              }
          }
    }


    public static void main(String[] args) throws IOException {
   
    	new CreateForm().getNode("");
    }
    
    public static List<String> getNode(String FILE_NAME) throws IOException{
    	 ApplicationContext appContext = new ClassPathXmlApplicationContext("appcontext.xml");
    	CreateForm ex = (CreateForm)appContext.getBean("createForm");
    	 return ex.go(FILE_NAME);
    }
    public List<String> go(String FILE_NAME) throws IOException {
    	ProjectNode formNode=loadekotest(FILE_NAME);
    	List<String> sectionNodeList = formNode.getSection();
    	return sectionNodeList;
    }
   
}