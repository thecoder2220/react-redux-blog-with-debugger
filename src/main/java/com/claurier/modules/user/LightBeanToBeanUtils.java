package com.claurier.modules.user;


import com.claurier.model.User;
import com.claurier.modules.user.lightbeans.UserLightbean;

public class LightBeanToBeanUtils {

    public static UserLightbean copyFrom(User user) {
        UserLightbean userLightbean = new UserLightbean();
        userLightbean.setId(user.getId());
        userLightbean.setUsername(user.getUsername());
        userLightbean.setEmail(user.getEmail());
        userLightbean.setLogin(user.getUsername());
        return userLightbean;
    }
}
