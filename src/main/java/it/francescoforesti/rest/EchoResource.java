package it.francescoforesti.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "echo", consumes = "*/*", produces = "text/plain")
@CrossOrigin(origins = "*")
public class EchoResource {

    @PostMapping
    public String echo(@RequestBody String body) {
        return body;
    }

    @PostMapping(path = "reverse")
    public String reverse(@RequestBody String body) {
        return new StringBuilder(body).reverse().toString();
    }

    @GetMapping
    public String echo() {
        return "hello";
    }
}
