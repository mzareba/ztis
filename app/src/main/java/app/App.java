package app;

import connector.PostsResponse;
import connector.RedditService;
import db.MongoConnector;
import post.IPost;
import post.Source;

import java.net.UnknownHostException;
import java.util.*;

public class App {

    private final RedditService redditService;

    public App() {
        this.redditService = new RedditService();
    }

    public static void main(String[] args) {
        final App app = new App();
        MongoConnector dbConnector = null;

        try
        {
            dbConnector = new MongoConnector();
        }
        catch (UnknownHostException e)
        {
            System.exit(1);
        }

        final PostsResponse response = app.redditService.getPosts("/r/gadgets");

        List<IPost> posts = response.getPosts();
//        List<Post> posts = ResponseParser.parseResponse(response, true);
//        List<Post> comments = new ArrayList<>();
//
//        for (Post post : posts) {
//            if (post.getResponses() > 0) {
//                PostsResponse commentsResponse = app.redditService.getPosts(post.getId());
//                comments.addAll(ResponseParser.parseResponse(commentsResponse, false));
//            }
//        }
//
//        posts.addAll(comments);
//
        for (IPost post : posts) {
            dbConnector.insert(post, Source.REDDIT, true);
        }
    }
}
