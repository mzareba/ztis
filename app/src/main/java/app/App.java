package app;

import api.RedditApi;
import api.WykopApi;
import connector.RedditConnector;
import connector.WykopConnector;
import db.MongoConnector;
import mapper.reddit.RedditPostMapper;
import mapper.reddit.SubredditLoader;
import mapper.wykop.TagsLoader;
import mapper.wykop.WykopPostMapper;
import services.reddit.RedditService;

import java.util.Date;
import services.wykop.WykopService;

public class App {

    private static final Date START_DATE = new Date(118, 4, 28);

    public static void main(String[] args) {
        final RedditService redditService = new RedditService(new MongoConnector(), new RedditApi(new RedditConnector()), new RedditPostMapper());
        final WykopService wykopService = new WykopService(new MongoConnector(), new WykopApi(new WykopConnector()), new WykopPostMapper());
        final SubredditLoader subredditLoader = new SubredditLoader();
        final TagsLoader tagsLoader = new TagsLoader();

        subredditLoader.loadSubreddits();
        tagsLoader.loadTags();

        subredditLoader.getSubreddits().forEach(subreddit -> redditService.getRedditPostsSinceDateAndSave(START_DATE, subreddit));
        tagsLoader.getTags().forEach(tag -> wykopService.getWykopPostsSinceDateAndSave(START_DATE, tag));
    }
}
