package com.elproyectegrande.service;

import com.elproyectegrande.model.ApplicationUser;
import com.elproyectegrande.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User is not valid"));
        } catch (UsernameNotFoundException ex) {
            System.out.println("Username not found: " + ex.getMessage());
            throw ex; 
        }
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }
}
