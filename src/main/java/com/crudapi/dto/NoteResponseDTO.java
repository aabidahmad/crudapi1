package com.crudapi.dto;


import lombok.Data;

@Data
public class NoteResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String username;  // email of user
}

