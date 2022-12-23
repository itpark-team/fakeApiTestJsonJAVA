package org.example.api;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Post;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FakeApiWorker {
    private HttpClient httpClient;
    private Gson gson;

    public FakeApiWorker() {
        httpClient = HttpClient.newHttpClient();
        gson = new Gson();
    }

    public Post getPostById(int id) throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + id))
                .build();
        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String jsonString = (String) httpResponse.body();

        Post post = gson.fromJson(jsonString, Post.class);
        return post;
    }

    public List<Post> getAllPosts() throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .build();
        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String jsonString = (String) httpResponse.body();

        List<Post> posts = gson.fromJson(jsonString, new TypeToken<List<Post>>() {
        }.getType());
        return posts;
    }

    public Post addNewPost(Post addedPost) throws Exception {
        String requestJsonString = gson.toJson(addedPost);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestJsonString, StandardCharsets.UTF_8))
                .build();

        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseJsonString = (String) httpResponse.body();

        Post post = gson.fromJson(responseJsonString, Post.class);
        return post;
    }

    public Post updatePostById(int id, Post updatedPost) throws Exception {
        String requestJsonString = gson.toJson(updatedPost);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestJsonString, StandardCharsets.UTF_8))
                .build();

        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseJsonString = (String) httpResponse.body();

        Post post = gson.fromJson(responseJsonString, Post.class);
        return post;
    }

    public boolean deletePostById(int id) throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + id))
                .DELETE()
                .build();

        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int code = httpResponse.statusCode();

        return code == 200;
    }
}
