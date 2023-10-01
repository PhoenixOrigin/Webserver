package net.phoenix.Webserver.controllers.website;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping(method = RequestMethod.GET, value = "/error")
    public String getIndex() {
        return "error.html";
    }
}