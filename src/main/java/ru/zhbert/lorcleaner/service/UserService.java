/*
 * Copyright (c) 2020.
 * Created by Zhbert.
 * Licensed by GPLv3.
 */

package ru.zhbert.lorcleaner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.zhbert.lorcleaner.domain.Role;
import ru.zhbert.lorcleaner.domain.User;
import ru.zhbert.lorcleaner.repos.UserRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }


        return userRepo.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }


    public void saveUser(User user, String username, String password, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        userRepo.save(user);
    }


    public Boolean addUser(String name, String password, Model model) {
        User userFormDB = userRepo.findByUsername(name);

        if (userFormDB != null) {
            model.addAttribute("message", "User exists!");
            return false;
        }

        User user = new User();
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setUsername(name);
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);

        return true;
    }
}
