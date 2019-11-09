package it.francescoforesti.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "comments")
@CrossOrigin(origins = "*")
public class CommentResource {

    private List<String> comments = new ArrayList<String>() {{
        add("primo commento"); add("secondo commento"); add("terzo commento");
    }};

    private HashMap<String, Long> reportsByComment = new HashMap<>();

    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addComment(@RequestBody String body) {
        comments.add(body);
        return body;
    }

    @GetMapping(produces = "application/json")
    public List<String> findAll() {
        return new ArrayList<>(comments);
    }

    @DeleteMapping(path = "{comment}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("comment") String comment) {
        comments.remove(comment);
        reportsByComment.remove(comment);
    }

    @PutMapping(path = "{comment}/report")
    @ResponseStatus(HttpStatus.OK)
    public void report(@PathVariable String comment) {
        reportsByComment.computeIfPresent(comment, (commentId, oldValue) -> oldValue + 1);
        reportsByComment.putIfAbsent(comment, 1L);
    }

    @GetMapping(path="reports", produces = "application/json")
    public  Map<String, Long> findAllReports() {
        return reportsByComment.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
