package com.crudapi.service;

import com.crudapi.dto.RegisterRequest;
import com.crudapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.crudapi.entity.User;

@Service
public class UserService {



    @Autowired
    UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    public User convertToEntity(RegisterRequest request) {
        request.setPassword(encoder.encode(request.getPassword()));
        return modelMapper.map(request, User.class);
    }
    public void register(RegisterRequest req) {

       userRepository.save(convertToEntity(req));


    }
}
