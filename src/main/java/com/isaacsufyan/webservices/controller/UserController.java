package com.isaacsufyan.webservices.controller;

import com.isaacsufyan.webservices.beans.User;
import com.isaacsufyan.webservices.beans.WebServiceResponse;
import com.isaacsufyan.webservices.daoservices.UserDaoService;
import com.isaacsufyan.webservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        User user = userDaoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("This id is not exist - " + id);
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder mvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        model.add(mvcLinkBuilder.withRel("all-users-link"));
        return model;
    }

    @PostMapping("/users")
    public WebServiceResponse<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        WebServiceResponse<User> response = new WebServiceResponse<>();
        response.setMessage("User is created");
        response.setData(savedUser);
        return response;
    }

    @DeleteMapping("/users/{id}")
    public WebServiceResponse<User> deleteUser(@PathVariable Integer id) {
        User deletedUser = userDaoService.deleteById(id);
        if (deletedUser == null)
            throw new UserNotFoundException("This id is not exist - " + id);
        WebServiceResponse<User> response = new WebServiceResponse<>();
        response.setMessage("User Is Deleted");
        response.setData(deletedUser);
        return response;
    }
}
