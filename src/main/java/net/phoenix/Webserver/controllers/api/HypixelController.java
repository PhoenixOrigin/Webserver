package net.phoenix.Webserver.controllers.api;

import net.phoenix.Webserver.containers.NucleusRun;
import net.phoenix.Webserver.handlers.api.HypixelHandler;
import net.phoenix.Webserver.handlers.callables.NickChecker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/nicked")
    public ResponseEntity<String> nicked(@RequestParam(name = "username", required = false) String username) {
        boolean nicked = new NickChecker(username).call();
        return ResponseEntity.ok(String.format("""
                {"nicked": %b}
                """, nicked));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        return "{\"success\": false}";
    }
}

