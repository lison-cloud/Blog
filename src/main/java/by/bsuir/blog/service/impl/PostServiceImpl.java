package by.bsuir.blog.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.bsuir.blog.dto.Post;
import by.bsuir.blog.entities.PostEntity;
import by.bsuir.blog.entities.UserEntity;
import by.bsuir.blog.repository.CategoryRepository;
import by.bsuir.blog.repository.PostRepository;
import by.bsuir.blog.repository.UserRepository;
import by.bsuir.blog.repository.exception.PostRepositoryException;
import by.bsuir.blog.repository.exception.RepositoryException;
import by.bsuir.blog.repository.exception.UserRepositoryException;
import by.bsuir.blog.repository.impl.CategoryRepositoryImpl;
import by.bsuir.blog.repository.impl.PostRepositoryImpl;
import by.bsuir.blog.repository.impl.UserRepositoryImpl;
import by.bsuir.blog.service.PostCommentService;
import by.bsuir.blog.service.PostService;
import by.bsuir.blog.service.TagService;
import by.bsuir.blog.service.exception.PostCommentServiceException;
import by.bsuir.blog.service.exception.PostServiceException;
import by.bsuir.blog.service.exception.TagServiceException;
import by.bsuir.blog.service.exception.ValidationException;
import by.bsuir.blog.service.util.ValidationUtil;

public class PostServiceImpl
        implements PostService {

    private static PostService instance;

    public static PostService getInstance() {
        if (instance == null) {
            synchronized (PostServiceImpl.class) {
                if (instance == null) {
                    instance = new PostServiceImpl();
                }
            }
        }
        return instance;
    }

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final PostCommentService postCommentService;
    private final CategoryRepository categoryRepository;
    private final TagService tagService;

    private PostServiceImpl() {
        this.postRepository = PostRepositoryImpl.getInstance();
        this.userRepository = UserRepositoryImpl.getInstance();
        this.categoryRepository = CategoryRepositoryImpl.getInstance();

        this.postCommentService = PostCommentServiceImpl.getInstance();
        this.tagService = TagServiceImpl.getInstance();
    }

    @Override
    public List<Post> getAll() throws PostServiceException {
        List<Post> posts = new ArrayList<>();
        try {
            for (PostEntity p : this.postRepository.getAll()) {
                Post post = this.convertToPost(p);
                post.setUserLogin(
                        this.userRepository.find(p.getUserId()).get().getLogin());
                posts.add(post);
            }
        } catch (RepositoryException | ValidationException e) {
            throw new PostServiceException(e);
        }
        return posts;
    }

    @Override
    public List<Post> latestPost() throws PostServiceException {
        List<Post> posts = new ArrayList<>();
        try {
            for (PostEntity p : this.postRepository.latestPost()) {
                Post post = this.convertToPost(p);
                post.setUserLogin(
                        this.userRepository.find(p.getUserId()).get().getLogin());
                posts.add(post);
            }
        } catch (RepositoryException | ValidationException e) {
            throw new PostServiceException(e);
        }
        return posts;
    }

    @Override
    public List<Post> postWithUserComment(String login) throws ValidationException, PostServiceException {
        ValidationUtil.isValidLogin(login);

        List<Post> posts = new ArrayList<>();
        try {
            for (PostEntity p : this.postRepository.getPostWithUserComment(login)) {
                Post post = new Post();
                post.setTitle(p.getTitle());
                post.setComments(
                        this.postCommentService.findByPostAndUser(p.getId(), login));
                posts.add(post);
            }
        } catch (PostRepositoryException | PostCommentServiceException e) {
            throw new PostServiceException(e);
        }
        return posts;
    }

    @Override
    public List<Post> userPost(String login) throws ValidationException, PostServiceException {
        ValidationUtil.isValidLogin(login);

        List<Post> posts = new ArrayList<>();
        try {
            UserEntity user = this.userRepository.getByLogin(login).get();
            for (PostEntity p : this.postRepository.getByUserId(user.getId())) {
                Post post = this.convertToPost(p);
                post.setUserLogin(user.getLogin());
                posts.add(post);
            }
        } catch (RepositoryException e) {
            throw new PostServiceException(e);
        }

        return posts;
    }

    @Override
    public Optional<Post> postBySlug(String slug) throws ValidationException, PostServiceException {
        ValidationUtil.isZeroLength(slug);

        Post post = null;
        try {
            Optional<PostEntity> eOptional = this.postRepository.getBySlug(slug);
            if (!eOptional.isPresent())
                return Optional.empty();
            PostEntity entity = eOptional.get();
            post = this.convertToPost(entity);
            post.setUserLogin(
                    this.userRepository.find(entity.getUserId()).get().getLogin());
            post.setComments(
                    this.postCommentService.postComment(entity.getId()));
        } catch (RepositoryException | PostCommentServiceException e) {
            throw new PostServiceException(e);
        }
        return Optional.of(post);
    }

    @Override
    public void update(Post post) throws ValidationException, PostServiceException {
        ValidationUtil.isPresented(post);

        PostEntity entity = this.convertToPostEntity(post);

        try {
            this.postRepository.add(entity);
        } catch (RepositoryException e) {
            throw new PostServiceException(e);
        }
    }

    @Override
    public void save(Post post) throws ValidationException, PostServiceException {
        ValidationUtil.isPresented(post);
        PostEntity entity = this.convertToPostEntity(post);

        entity.setPublishedAt(
                new Timestamp(Instant.now().toEpochMilli()));

        try {
            this.postRepository.add(entity);
        } catch (RepositoryException e) {
            throw new PostServiceException(e);
        }
    }

    @Override
    public void delete(long postId) throws ValidationException, PostServiceException {
        ValidationUtil.isZeroOrLess(postId);
        try {
            this.postRepository.removeById(postId);
        } catch (RepositoryException e) {
            throw new PostServiceException(e);
        }
    }

    private Post convertToPost(PostEntity entity) throws ValidationException, PostServiceException {
        Post post = new Post();

        post.setId(entity.getId());
        post.setTitle(entity.getTitle());
        post.setSlug(entity.getSlug());
        post.setContent(entity.getContent());

        post.setPublished(entity.isPublished());
        post.setPublishedAt(entity.getPublishedAt());
        post.setUpdateAt(entity.getUpdateAt());
        post.setCreatedAt(entity.getCreatedAt());

        try {
            post.setCategory(
                    this.categoryRepository.find(entity.getCategoryId()).get());
            post.setTags(
                    this.tagService.getPostTag(post.getId()));
        } catch (RepositoryException | TagServiceException e) {
            throw new PostServiceException(e);
        }
        return post;
    }

    private PostEntity convertToPostEntity(Post post) throws ValidationException, PostServiceException {
        PostEntity entity = new PostEntity();

        entity.setId(post.getId());
        entity.setTitle(post.getTitle());
        entity.setSlug(post.getSlug());
        entity.setContent(post.getContent());

        entity.setPublished(post.isPublished());
        entity.setPublishedAt(post.getPublishedAt());
        entity.setUpdateAt(post.getUpdateAt());
        entity.setCreatedAt(post.getCreatedAt());

        try {
            entity.setUserId(
                    this.userRepository.getByLogin(post.getUserLogin()).get().getId());
        } catch (UserRepositoryException e) {
            throw new PostServiceException(e);
        }
        entity.setCategoryId(
                post.getCategory().getId());

        return entity;
    }

    @Override
    public List<Post> tagPost(String tag) throws ValidationException, PostServiceException {
        ValidationUtil.isZeroLength(tag);

        List<Post> posts = new ArrayList<>();
        try {
            for (PostEntity p : this.postRepository.getByTag(tag)) {
                Post post = this.convertToPost(p);
                post.setUserLogin(
                        this.userRepository.find(p.getUserId()).get().getLogin());
                posts.add(post);
            }
        } catch (RepositoryException e) {
            throw new PostServiceException(e);
        }
        return posts;
    }

    @Override
    public List<Post> categoryPost(String categoryTitle) throws ValidationException, PostServiceException {
        ValidationUtil.isZeroLength(categoryTitle);

        List<Post> posts = new ArrayList<>();
        try {
            for (PostEntity p : this.postRepository.getAllByCategorySlug(categoryTitle)) {
                Post post = this.convertToPost(p);
                post.setUserLogin(
                        this.userRepository.find(p.getUserId()).get().getLogin());
                posts.add(post);
            }
        } catch (RepositoryException e) {
            throw new PostServiceException(e);
        }
        return posts;
    }

}
