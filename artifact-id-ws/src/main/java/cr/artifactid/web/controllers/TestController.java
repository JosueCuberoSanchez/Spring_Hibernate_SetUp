package cr.artifactid.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @RequestMapping("/index")
    public ModelAndView helloWorld() {

        String message = "<h3>This is artifact-id app!</h3>";
        return new ModelAndView("index", "message", message);

    }

}