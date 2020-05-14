package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumow.components.resolvers.CashedIdPool;
import ru.naumow.dto.PostDto;
import ru.naumow.entity.Post;
import ru.naumow.repositories.PostRepository;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired private PostRepository postRepository;
    @Autowired private CashedIdPool   cashedIdPool;


    @Override
    public List<Post> getAllByBlogId(Long blogId) {
        List<Post> list = postRepository.findByBlogId(blogId);
        list.sort((o1, o2) -> -o1.getCratedAt().compareTo(o2.getCratedAt()));

        //list.sort((o1, o2) -> o1.getId() - o2.getId() > 0 ? 1 : -1);
        return list;
    }

    @Override
    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void waitForNew(Long id) {
        id = cashedIdPool.cashedOf(id);
        synchronized (id) {
            try {
                id.wait();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public List<PostDto> postsOf() {
        throw new IllegalStateException("method is not implemented yet");
    }

    @Override
    public List<PostDto> postsOfLevel(Long blogId, int level) {
        List<Post> posts = postRepository.findByBlogIdAndLevelIsLessThanEqual(blogId, level);
        return PostDto.from(posts);
    }
}
