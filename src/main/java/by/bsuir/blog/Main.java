package by.bsuir.blog;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import by.bsuir.blog.dto.Category;
import by.bsuir.blog.dto.Post;
import by.bsuir.blog.dto.PostComment;
import by.bsuir.blog.dto.User;
import by.bsuir.blog.dto.UserInfo;
import by.bsuir.blog.entities.PostCommentEntity;
import by.bsuir.blog.entities.PostEntity;
import by.bsuir.blog.entities.UserEntity;
import by.bsuir.blog.mapper.Constructor;
import by.bsuir.blog.mapper.CriteriaBuilder;
import by.bsuir.blog.mapper.CriteriaDelete;
import by.bsuir.blog.mapper.CriteriaInsert;
import by.bsuir.blog.mapper.CriteriaSelect;
import by.bsuir.blog.mapper.CriteriaUpdate;
import by.bsuir.blog.mapper.Metadata;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.impl.CriteriaBuilderImpl;
import by.bsuir.blog.mapper.impl.CriteriaDeleteImpl;
import by.bsuir.blog.mapper.impl.CriteriaInsertImpl;
import by.bsuir.blog.mapper.impl.CriteriaSelectImpl;
import by.bsuir.blog.mapper.impl.CriteriaUpdateImpl;
import by.bsuir.blog.metadata.BlogMetadata;
import by.bsuir.blog.repository.PostRepository;
import by.bsuir.blog.repository.UserRepository;
import by.bsuir.blog.repository.impl.PostRepositoryImpl;
import by.bsuir.blog.repository.impl.UserRepositoryImpl;
import by.bsuir.blog.service.PostCommentService;
import by.bsuir.blog.service.PostService;
import by.bsuir.blog.service.UserService;
import by.bsuir.blog.service.impl.PostCommentServiceImpl;
import by.bsuir.blog.service.impl.PostServiceImpl;
import by.bsuir.blog.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Metadata metadata = BlogMetadata.newInstance();
        // Table table = metadata.entityTable(PostEntity.class);

        // PostEntity e = new PostEntity();
        // e.setContent("content");
        // e.setSlug("slug");
        // e.setTitle("title");
        // e.setCreatedAt(new Timestamp(Instant.now().toEpochMilli()));

        // CriteriaInsert ci = CriteriaInsertImpl.getCriteria(table);
        // ci.values(e);
        // ci.query();
        
        
        // CriteriaBuilder cb = CriteriaBuilderImpl.newInstance();
        // CriteriaDelete cd = CriteriaDeleteImpl.criteria(table);
        // cd.where(
        //     cb.equal(table.getColumn("p_id"), 1)
        // ).query();
        
        // CriteriaBuilder qwe = CriteriaBuilderImpl.newInstance();
        // CriteriaSelect<PostEntity> cs = CriteriaSelectImpl.criteria(table);
        // cs.select(
        //     cs.where(
        //         qwe.equal(
        //             table.getColumn("p_id"), 1))).query();

        // CriteriaUpdate cu = CriteriaUpdateImpl.criteria(table);
        // cu.set(e).where(qwe.equal(table.getColumn("p_id"), 1)).query();

        // UserRepository userRepository = new DefaultUserRepository();
        // UserEntity userEntity = userRepository.get(1).get();
        // UserEntity userEntity2 = userRepository.get(2).get();
        // System.out.println(userEntity);
        // System.out.println(userEntity2);

        // PostRepository postRepository = new DefaultPostRepository();
        // PostEntity postEntity = postRepository.get(1).get();
        // PostEntity postEntity2 = postRepository.get(2).get();
        // System.out.println(postEntity);
        // System.out.println(postEntity2);

        // Iterable<PostEntity> postEntity3  = postRepository.getAllByCategoryTitle("Nature");
        // System.out.println(postEntity3);

        // User u = new User();
        // u.setLogin("fushiguro");
        // u.setUserRole("user");
        // UserInfo userInfo = new UserInfo();
        // userInfo.setName("Andrei");
        // userInfo.setSurname("Tokar");
        // userInfo.setEmail("andrei@vector.gmail.com");
        // u.setUserInfo(userInfo);


        // UserService userService = new DefaultUserService();

        // // userService.save(u, "bober4ik");
        // Optional<User> u = userService.authenticate("fushiguro", "bober4ik");

        // if(!u.isPresent())
        //     System.out.println("not now ");
        // else
        //     System.out.println(u.get());

        // PostService postService = new DefaultPostService();

        // List<Post> posts = postService.getAll();

        // System.out.println(posts);

        // String str = "bcs";

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        System.out.println(formatter.format(date));

        ExecutorService e = Executors.newCachedThreadPool();

    }
}
