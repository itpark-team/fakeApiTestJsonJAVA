package org.example;

import org.example.api.FakeApiWorker;
import org.example.model.Post;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        FakeApiWorker fakeApiWorker = new FakeApiWorker();

//        Post post = fakeApiWorker.getPostById(1);
//        System.out.println(post.toString());

//        List<Post> posts = fakeApiWorker.getAllPosts();
//        for (Post post:posts) {
//            System.out.println(post);
//        }

//        Post addedPost = Post.builder()
//                .userId(999)
//                .bodya("бодя")
//                .title("my title")
//                .build();
//        Post post = fakeApiWorker.addNewPost(addedPost);
//        System.out.println(post);

//        Post updatedPost = Post.builder()
//                .userId(999)
//                .bodya("бодя")
//                .title("my title")
//                .build();
//        Post post = fakeApiWorker.updatePostById(30, updatedPost);
//        System.out.println(post);

        boolean isDeleted = fakeApiWorker.deletePostById(1);
        System.out.println(isDeleted);

    }
}
