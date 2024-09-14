package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryStubImpl implements PostRepository {

    private final Map<Long, Post> allPosts;
    private final AtomicLong idCounter = new AtomicLong();

    public PostRepositoryStubImpl() {
        this.allPosts = new ConcurrentHashMap<>();
    }

    public List<Post> all() {
        return (List<Post>) allPosts.values();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(allPosts.get(id));
    }

    public Post save(Post savePost) {
        if (savePost.getId() == 0) {
            long id = idCounter.incrementAndGet();
            savePost.setId(id);
            allPosts.put(id, savePost);
        } else if (savePost.getId() != 0) {
            Long currentId = savePost.getId();
            allPosts.put(currentId, savePost);
        }
        return savePost;
    }

    public void removeById(long id) {
        allPosts.remove(id);
    }
}