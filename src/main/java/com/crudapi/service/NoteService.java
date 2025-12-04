package com.crudapi.service;

import com.crudapi.dto.NoteRequest;
import com.crudapi.dto.NoteResponseDTO;
import com.crudapi.entity.Note;
import com.crudapi.entity.User;
import com.crudapi.repository.NoteRepository;
import com.crudapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Note mapToEntity(NoteRequest req){
        return modelMapper.map(req,Note.class);
    }


    public Note createNode(NoteRequest req, User user) {
        Note note = mapToEntity(req);
        note.setUser(user);
        note.setCreatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public List<NoteResponseDTO> getNotes(Authentication auth) {

        User user = userRepository.findByEmail(auth.getName());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Note> notes = noteRepository.findByUserId(user.getId());

        return notes.stream().map(note -> {
            NoteResponseDTO dto = modelMapper.map(note, NoteResponseDTO.class);
            dto.setUsername(user.getName());   // add username manually
            return dto;
        }).toList();
    }
}
