package app;

import api.RedditApi;
import connector.RedditService;
import db.MongoConnector;
import mapper.reddit.RedditPostMapper;
import model.RedditPost;
import post.Post;

import java.net.UnknownHostException;
import java.util.List;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class App {


    public static void main(String[] args) {
        final RedditApi redditApi = new RedditApi(new RedditService());
        final MongoConnector dbConnector = new MongoConnector();
        final RedditPostMapper redditPostMapper = new RedditPostMapper();

        try {
            dbConnector.connect();
        } catch (final UnknownHostException e) {
            System.exit(1);
        }

        final List<RedditPost> newRedditPosts = redditApi.getNewPosts("bitcoin", 100, null);
        final List<Post> mappedPosts = newRedditPosts.stream()
                .map(post -> redditPostMapper.mapToDomainPost(post, true))
                .collect(toImmutableList());

        dbConnector.savePosts(mappedPosts);
    }
}
