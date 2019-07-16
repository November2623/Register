package com.citynow.smallerp.configuration.security.service;

import com.citynow.smallerp.configuration.security.jwt.UserPrinciple;
import com.citynow.smallerp.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword("$2a$10$aevtiNiX577UqlHb1Z8/Z.qBiYRSRQKc17F5uGCwFioNEbc74mxGa"); // pass 123456

        return UserPrinciple.build(user);
    }
}
