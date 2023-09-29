package net.phoenix.APIForwarder.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hypixel")
public class HypixelController {

    @GetMapping("/bazaarCost")
    public ResponseEntity<String> getPlayer(@PathVariable String username) {
        return ResponseEntity.ok("Fetching player data for: " + username);
    }

}

