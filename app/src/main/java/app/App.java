package app;

import api.RedditApi;
import connector.RedditConnector;
import db.MongoConnector;
import mapper.reddit.RedditPostMapper;
import mapper.reddit.SubredditLoader;
import services.reddit.RedditService;

import java.util.Date;

public class App {

    private static final Date START_DATE = new Date(118, 4, 28);

    public static void main(String[] args) {
        final RedditService redditService = new RedditService(new MongoConnector(), new RedditApi(new RedditConnector()), new RedditPostMapper());
        final SubredditLoader subredditLoader = new SubredditLoader();
        subredditLoader.loadSubreddits();
        subredditLoader.getSubreddits().forEach(subreddit -> redditService.getRedditPostsSinceDateAndSave(START_DATE, subreddit));
    }
}
