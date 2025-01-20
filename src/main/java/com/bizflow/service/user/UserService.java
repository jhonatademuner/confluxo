package com.bizflow.service.user;

import com.bizflow.domain.user.CustomUserDetails;
import com.bizflow.domain.user.User;
import com.bizflow.domain.user.UserRole;
import com.bizflow.domain.user.dto.UserLoginDTO;
import com.bizflow.domain.user.dto.UserRegisterDTO;
import com.bizflow.repository.user.UserRepository;
import com.bizflow.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public User register(UserRegisterDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User entity = User.builder()
            .username(userDTO.getUsername())
            .password(userDTO.getPassword())
            .email(userDTO.getEmail())
            .role(userDTO.getRole())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        return userRepository.save(entity);
    }

    public String verify(UserLoginDTO user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }

        return "Fail";
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }

}
