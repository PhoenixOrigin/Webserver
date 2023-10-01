package net.phoenix.Webserver.controllers.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebsiteController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getIndex() {
        return "index.html";
    }
}