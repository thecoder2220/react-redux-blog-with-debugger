package com.claurier.modules.user.services;

import com.claurier.domain.UserForm;
import com.claurier.modules.user.lightbeans.UserLightbean;

public interface UserService {

    public UserLightbean createUser( UserForm userForm);

}

