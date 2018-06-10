package build;

import core.TestBeanAnnotation;
import core.TestBeanXML;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataImporter {

    private ClassPathXmlApplicationContext classPathXmlApplicationContext;

    public DataImporter(){
        this.classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("artifact-id-core.spring.xml");
    }

    public void testBeans(){
        TestBeanXML testBeanXML = this.classPathXmlApplicationContext.getBean(TestBeanXML.class);
        TestBeanAnnotation testBeanAnnotation = this.classPathXmlApplicationContext.getBean(TestBeanAnnotation.class);

        System.out.println(testBeanXML.getTestText());
        System.out.println(testBeanAnnotation.getTestText());

    }
}
