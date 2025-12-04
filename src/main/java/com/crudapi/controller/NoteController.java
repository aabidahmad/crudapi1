package com.crudapi.controller;

import com.crudapi.dto.NoteRequest;
import com.crudapi.dto.NoteResponseDTO;
import com.crudapi.entity.Note;
import com.crudapi.entity.User;
import com.crudapi.repository.NoteRepository;
import com.crudapi.repository.UserRepository;
import com.crudapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteService noteService;



    @PostMapping("/create")
    public ResponseEntity<?> createNote(@RequestBody NoteRequest req, Authentication auth) {

        User user = userRepository.findByEmail(auth.getName());
        if(user !=null) {
            Note note = noteService.createNode(req, user);
            if(note !=null)
            return new ResponseEntity<>("Note Saved Successfully ", HttpStatus.CREATED);
        }
            return new ResponseEntity<>("Error occurred while creating post ", HttpStatus.BAD_REQUEST);

    }

    @GetMapping
    public List<NoteResponseDTO> getMyNotes(Authentication auth) {
        return noteService.getNotes(auth);
    }

}
