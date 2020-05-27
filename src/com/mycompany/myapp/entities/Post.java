/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author arche
 */
public class Post {
    int id;
    int creator ;
    String title;
    String description;
    String photo;
    String postdate ;
    int rating;
    int blocke;

    public Post(String title, String description, String photo, int rating, int blocke) {
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.rating = rating;
        this.blocke = blocke;
    }

    public Post(int id, int creator, String title, String description, String photo, String postdate, int rating, int blocke) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.postdate = postdate;
        this.rating = rating;
        this.blocke = blocke;
    }

    public Post(String title, String description, int rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public Post(int id, String title, String description, int rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getBlocke() {
        return blocke;
    }

    public void setBlocke(int blocke) {
        this.blocke = blocke;
    }

    @Override
    public String toString() {
        return "Post{" + "title=" + title + ", description=" + description + '}';
    }



   

}
