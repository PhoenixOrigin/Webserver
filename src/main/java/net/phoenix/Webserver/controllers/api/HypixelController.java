package net.phoenix.Webserver.controllers.api;

import net.phoenix.Webserver.containers.NucleusRun;
import net.phoenix.Webserver.handlers.api.HypixelHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hypixel")
public class HypixelController {

    @GetMapping("/nucleusCost")
    public ResponseEntity<String> bazaarCost(@RequestParam(name = "pretty", required = false) String prettyPrint) {
        NucleusRun run = new NucleusRun(HypixelHandler.bazaar);
        if (prettyPrint != null && prettyPrint.equals("true")) {
            return ResponseEntity.ok(run.toPrettyJson());
        }
        return ResponseEntity.ok(run.toJson());
    }

}

