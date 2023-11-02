package net.phoenix.Webserver.controllers.api;

import net.phoenix.Webserver.database.DatabaseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/wynncraft")
public class WynncraftController {

    private final Pattern tagRegex = Pattern.compile("^[a-zA-Z]{3,4}$");

    @GetMapping("/playtime")
    public ResponseEntity<String> getPlaytime(@RequestParam(name = "username") String username, @RequestParam(name = "from", required = false) String from) {
        if (from == null) {
            return ResponseEntity.ok(String.format("{\"playtime\": %d}", DatabaseHandler.get_playtime(username)));
        } else {
            return ResponseEntity.ok(String.format("{\"playtime\": %d}", DatabaseHandler.get_playtime(username, Timestamp.from(Instant.ofEpochMilli(Long.parseLong(from))))));
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        return "{\"success\": false}";
    }
}

