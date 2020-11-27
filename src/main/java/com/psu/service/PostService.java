package com.psu.service;

import com.psu.entity.Post;
import com.psu.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post getPost(String name){
        return postRepository.findByName(name);
    }
}
