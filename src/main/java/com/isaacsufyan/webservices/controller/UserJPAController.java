package com.isaacsufyan.webservices.controller;

import com.isaacsufyan.webservices.beans.Post;
import com.isaacsufyan.webservices.beans.User;
import com.isaacsufyan.webservices.beans.WebServiceResponse;
import com.isaacsufyan.webservices.exception.UserNotFoundException;
import com.isaacsufyan.webservices.repositories.PostRepository;
import com.isaacsufyan.webservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAController {

    // @Autowired
    // private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
    private final PostRepository postRepository;

    @Autowired
    UserJPAController(PostRepository userRepository){
        this.postRepository = userRepository;
    }

    @GetMapping(path = "/jpa/users", produces = {"application/xml"})
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

//    @GetMapping("/jpa/users/{id}")
//    public EntityModel<Optional<User>> retrieveUser(@PathVariable Integer id) {
//        Optional<User> users = userRepository.findById(id);
//        if (users.isEmpty()) {
//            throw new UserNotFoundException("This id is not exist - " + id);
//        }
//        EntityModel<Optional<User>> model = EntityModel.of(users);
//        WebMvcLinkBuilder mvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
//        model.add(mvcLinkBuilder.withRel("all-users-link"));
//        return model;
//    }

    @GetMapping(path = "/jpa/users/{id}")
    public Optional<User> retrieveUser(@PathVariable Integer id) {
        Optional<User> users = userRepository.findById(id);
        if (users.isEmpty()) {
            throw new UserNotFoundException("This id is not exist - " + id);
        }
        return users;
    }

    @PostMapping(path = "/jpa/users", consumes = {"application/json"})
    public WebServiceResponse<User> createUser(@Valid @RequestBody User user) {
        if(user.getBirthDate()==null){
            user.setBirthDate(new Date());
        }
        User savedUser = userRepository.save(user);
        WebServiceResponse<User> response = new WebServiceResponse<>();
        response.setMessage("User is created");
        response.setData(savedUser);
        return response;
    }

    @DeleteMapping("/jpa/users/{id}")
    public WebServiceResponse<User> deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        WebServiceResponse<User> response = new WebServiceResponse<>();
        response.setMessage("User Is Deleted");
        return response;
    }


    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("id- "+id);
        }

        return userOptional.get().getPosts();
    }

    @GetMapping("/jpa/users/{user_id}/posts/{post_id}")
    public EntityModel<Post> retrieveSpecificUserPostInfo(@PathVariable Integer user_id, @PathVariable Integer post_id) {
//        Optional<User> users = userRepository.findById(user_id);
//        if (users.isEmpty()) {
//            throw new UserNotFoundException("This id is not exist - " + user_id);
//        }
//
//        List<Post> postList = users.get().getPosts();
//        Stream<Post> postStream = postList.stream().filter(post -> Objects.equals(post.getId(), post_id));
//        return postStream.findFirst();
        Post post = postRepository.getPostByIdAndUser_Id(user_id, post_id);
        if (post==null){
            throw new UserNotFoundException("User Id: "+user_id+ " or Post Id: "+post_id+" is incorrect");
        }

        EntityModel<Post> model = EntityModel.of(post);
        WebMvcLinkBuilder mvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllPosts());
        model.add(mvcLinkBuilder.withRel("go-to-all-posts"));
        return model;
    }

    @GetMapping("/jpa/posts/")
    public List<Post> retrieveAllPosts() {
        List<Post> post = postRepository.findAll();
        if (post.isEmpty()){
            throw new UserNotFoundException("Have No Posts");
        }
        return post;
    }

    @PostMapping("/jpa/users/{id}/posts")
    public WebServiceResponse<Post> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("id- "+id);
        }

        User user = userOptional.get();
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        WebServiceResponse<Post> response = new WebServiceResponse<>();
        response.setMessage("Post is created");
        response.setData(savedPost);
        return response;
    }

}
