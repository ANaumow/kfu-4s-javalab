package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumow.entity.Post;
import ru.naumow.repositories.PostRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired private PostRepository postRepository;

    @Override
    public List<Post> getByBlogId(Long blogId) {
        List<Post> list = postRepository.findByBlog_Id(blogId);
        list.sort((o1, o2) -> o1.getId() - o2.getId() > 0 ? -1 : 1);
        return list;
    }
}
