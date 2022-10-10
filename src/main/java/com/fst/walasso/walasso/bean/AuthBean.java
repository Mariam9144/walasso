package com.fst.walasso.walasso.bean;


import javax.inject.Named;

import com.fst.walasso.walasso.model.Users;
import com.fst.walasso.walasso.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Named
public class AuthBean {

    String username;
    Users currentUser;

    @Autowired
    UsersRepository usersRepository;

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        username = authentication.getName();
        return username;
    }

    public Users getCurrentUser() {
        currentUser= usersRepository.findByUsername(getUsername());
        return currentUser;
    }

}
