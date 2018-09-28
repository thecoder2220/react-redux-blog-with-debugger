package com.claurier.modules.user.services;

import com.claurier.domain.UserForm;
import com.claurier.model.Authority;
import com.claurier.model.AuthorityName;
import com.claurier.model.User;
import com.claurier.modules.user.LightBeanToBeanUtils;
import com.claurier.modules.user.lightbeans.UserLightbean;
import com.claurier.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserLightbean  createUser(UserForm userForm) {

        User user = new User();
        user.setUsername(userForm.getUserName());
        user.setDateCreation(LocalDateTime.now());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setEnabled(false);
        user.setDateModification(LocalDateTime.now());
        user.setLastPasswordResetDate(LocalDate.now());
        ArrayList authorities = new ArrayList<Authority>();
        authorities.add(new SimpleGrantedAuthority(AuthorityName.ROLE_USER.toString()));
        user.setAuthorities(authorities);
        User userB = userRepository.save(user);
        return LightBeanToBeanUtils.copyFrom(userB);

    }

}
