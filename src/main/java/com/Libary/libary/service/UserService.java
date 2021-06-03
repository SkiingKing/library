package com.Libary.libary.service;

import com.Libary.libary.Dto.UserDto;
import com.Libary.libary.entity.Role;
import com.Libary.libary.entity.User;
import com.Libary.libary.exeption.UserNotFoundException;
import com.Libary.libary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user, Role role){
        user.setRole(role);
        userRepository.save(user);
    }

    public void createUser(User user){
            create(user,Role.USER);
    }


    public User createUserFromDTO(UserDto userDto){
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setPassword(bcrypt.encode(userDto.getPassword()));

        return user;
    }

    public User findByName(String name) {
        return userRepository.findByEmail(name);
    }

    public void createLibrarian(User user) {
        create(user, Role.LIBRARIAN);
    }

}
