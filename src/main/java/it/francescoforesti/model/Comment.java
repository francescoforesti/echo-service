package it.francescoforesti.model;

import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Comment {

    private Long id;
    private String text;
    private String author;
    private Boolean deleted;

}
