package com.claurier.controllers;


import com.claurier.commons.validation.ValidationErrorBuilder;
import com.claurier.domain.UserForm;
import com.claurier.modules.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value={"/api"})
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

   /* @RequestMapping(value={"/user/create"}, method={RequestMethod.POST})
    public String handleUserCreateForm() {
        UserCreateForm f = new UserCreateForm("sonia.pigot@live.fr", "Sonia", "ccah", "ccah", AuthorityName.ROLE_ADMIN);
        this.userService.create(f);
        return "OK1";
    }*/

    @RequestMapping(value={"/user/create"},method = RequestMethod.POST)
    public ResponseEntity createUser(@Valid @RequestBody UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(bindingResult));
        }
        return new ResponseEntity<>(userService.createUser(userForm), HttpStatus.CREATED);
    }

  /*  @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createFinancement(@AuthenticationPrincipal JwtUser user, @Valid @RequestBody FinancementForm financementForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(bindingResult));
        }
        return new ResponseEntity<>(financementService.createFinancement(user.getId(), financementForm), HttpStatus.CREATED);
    }*/
}


