package com.example.app.service;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;

@Service
public class ImplUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.getRoles().forEach(role -> role.getPrivileges().size());

        return new UserPrincipal(user);
    }

}
