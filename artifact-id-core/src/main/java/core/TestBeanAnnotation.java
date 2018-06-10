package core;

import org.springframework.stereotype.Component;

@Component
public class TestBeanAnnotation {

    private String testText;

    public TestBeanAnnotation(){
        this.testText = "This is a test bean Annotation";
    }

    public String getTestText() {
        return this.testText;
    }

    public void setTestText(String testText) {
        this.testText = testText;
    }

}