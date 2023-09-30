package net.phoenix.Webserver.api;

import net.phoenix.Webserver.database.DatabaseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequestMapping("/api/wynncraft")
public class WynncraftController {

    @GetMapping("/playtime")
    public ResponseEntity<String> getPlaytime(@RequestParam(name = "username") String username, @RequestParam(name = "from", required = false) String from) {
        if (from == null) {
            return ResponseEntity.ok(String.format("{\"playtime\": %d)", DatabaseHandler.get_playtime(username)));
        } else {
            return ResponseEntity.ok(String.format("{\"playtime\": %d)", DatabaseHandler.get_playtime(username, Timestamp.from(Instant.ofEpochMilli(Long.parseLong(from))))));
        }
    }

}

