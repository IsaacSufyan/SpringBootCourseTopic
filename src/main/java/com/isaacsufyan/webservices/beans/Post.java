package com.isaacsufyan.webservices.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descriptions;

    @ManyToOne()
//    @JoinColumn(name="user_id", nullable=false)
    @JoinColumn()
    @JsonIgnore
    private User user;

    public Post(){}

    public Post(Integer id, String descriptions, User user) {
        this.id = id;
        this.descriptions = descriptions;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
