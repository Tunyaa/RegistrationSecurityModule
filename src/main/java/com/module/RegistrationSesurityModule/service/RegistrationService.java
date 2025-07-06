package com.module.RegistrationSesurityModule.service;

import com.module.RegistrationSesurityModule.model.User;
import com.module.RegistrationSesurityModule.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



/**
 *
 * @author tunyaa
 */
@Service
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public void register(User user) {
        if (userRepository.existByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
