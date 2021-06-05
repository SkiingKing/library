package com.Libary.libary.security;

import com.Libary.libary.Controller.LibrarianController;
import com.Libary.libary.Dto.UserDto;
import com.Libary.libary.entity.Role;
import com.Libary.libary.entity.User;
import com.Libary.libary.exeption.UserNotFoundException;
import com.Libary.libary.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User dosen`t exixts!"));
        logger.info("User:"+user);
     return SecurityUser.fromUser(user);
    }
}