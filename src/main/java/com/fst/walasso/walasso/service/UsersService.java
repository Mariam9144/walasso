package com.fst.walasso.walasso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fst.walasso.walasso.model.Users;

import com.fst.walasso.walasso.repository.UsersRepository;

import lombok.Data;

@Data
@Service
public class UsersService {

	@Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Users> getUsers(final int id) {
        return usersRepository.findById(id);
    }

    public List<Users> getUserss() {
        return usersRepository.findAll();
    }

    public void deleteUsers(final int id) {
    	usersRepository.deleteById(id);
    }

    public Users saveUsers(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUsers = usersRepository.save(users);
        return savedUsers;
    }

}
