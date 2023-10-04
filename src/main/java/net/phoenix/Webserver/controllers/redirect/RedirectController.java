package net.phoenix.Webserver.controllers.redirect;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {

    @RequestMapping("/hax")
    public RedirectView localRedirect() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://discord.gg/eH5ynaQ");
        return redirectView;
    }

}
