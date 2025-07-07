package com.module.RegistrationSesurityModule.service;

import com.module.RegistrationSesurityModule.model.User;
import com.module.RegistrationSesurityModule.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author tunyaa
 */
@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
