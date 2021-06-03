package com.Libary.libary.service;

import com.Libary.libary.Dto.UserDto;
import com.Libary.libary.entity.Role;
import com.Libary.libary.entity.User;
import com.Libary.libary.exeption.UserNotFoundException;
import com.Libary.libary.repository.UserRepository;
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

@Service
@Qualifier("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName){
        User user = userRepository.findByEmail(userName);
        if (user == null) throw new UsernameNotFoundException(userName);

        List<Role> grantedAuthorities = Arrays.asList(Role.values());
//        for (Role role : grantedAuthorities){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
//        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}