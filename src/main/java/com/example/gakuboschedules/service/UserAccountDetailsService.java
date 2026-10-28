package com.example.gakuboschedules.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gakuboschedules.entity.User;
import com.example.gakuboschedules.repository.UserRepository;

@Service
public class UserAccountDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserAccountDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.insert(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);

            return org.springframework.security.core.userdetails.User
               .withUsername(user.getUserId())
               .password(user.getUserPassword())
               .roles(user.getUserRole())
               .disabled(false)
               .accountExpired(false)
               .credentialsExpired(false)
               .accountLocked(false)
               .build();
        } catch(UsernameNotFoundException e) {
            throw new UsernameNotFoundException("ユーザー名が見つかりません");
        } catch(EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("ユーザーが見つかりません", e.getExpectedSize());
        }
    }
}
