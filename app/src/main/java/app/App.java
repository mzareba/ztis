package app;

import connector.PostsResponse;
import connector.RedditService;
import db.MongoConnector;
import post.Post;
import post.PostBuilder;
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

        final PostsResponse response = app.redditService.getPosts("/r/fcbayern");

        HashMap<String, Object> data = (LinkedHashMap)response.getData();
        List<HashMap> rawPosts = (ArrayList<HashMap>)data.get("children");
        List<Post> posts = new ArrayList<>();

        for(HashMap post : rawPosts) {
            HashMap<String, Object> postData = (LinkedHashMap) post.get("data");
            posts.add(new PostBuilder()
                            .withId((String) postData.get("permalink"))
                            .withTopic((String) postData.get("subreddit"))
                            .withSource(Source.REDDIT)
                            .withDate(new Date(Double.valueOf(postData.get("created").toString()).longValue() * 1000))
                            .withOriginalPost(true)
                            .withLikes(Integer.valueOf(postData.get("score").toString()))
                            .withResponses(Integer.valueOf(postData.get("num_comments").toString()))
                            .build());
        }

        for (Post post : posts) {
            dbConnector.insert(post);
        }
    }
}
