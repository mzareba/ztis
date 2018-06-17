package services.reddit;

import api.RedditApi;
import api.RedditResponse;
import com.google.common.collect.Iterables;
import db.MongoConnector;
import mapper.reddit.RedditPostMapper;
import model.RedditPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import post.Post;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import static api.RedditApi.MAX_LIMIT;
import static com.google.common.collect.ImmutableList.toImmutableList;

public class RedditService {

    private static final Logger logger = LoggerFactory.getLogger(RedditService.class);
    private final MongoConnector dbConnector;
    private final RedditApi redditApi;
    private final RedditPostMapper redditPostMapper;

    public RedditService(final MongoConnector dbConnector, final RedditApi redditApi, final RedditPostMapper redditPostMapper) {
        this.dbConnector = dbConnector;
        this.redditApi = redditApi;
        this.redditPostMapper = redditPostMapper;
    }

    public void getRedditPostsSinceDateAndSave(final Date since, final String subreddit) {
        connect();
        Date currentDate = new Date();
        String nextPageId = null;
        while (currentDate.after(since)) {
            final RedditResponse redditResponse = redditApi.getPostsWithNextPageId(subreddit, MAX_LIMIT, nextPageId);
            if (nextPageId != null && redditResponse.getNextPageId() == null) {
                logger.info("Reached limit of visible posts for subreddit: /{}.", subreddit);
                break;
            } else {
                nextPageId = redditResponse.getNextPageId();
            }

            final List<Post> mappedPosts = redditResponse.getPosts().stream()
                    .filter(post -> getPostCreationDate(post).after(since))
                    .map(post -> redditPostMapper.mapToDomainPost(post, true))
                    .collect(toImmutableList());
            if (mappedPosts.size() == 0) {
                break;
            }
            dbConnector.savePosts(mappedPosts);
            currentDate = Iterables.getLast(mappedPosts).getCreationDate();
        }
    }

    private void connect() {
        try {
            dbConnector.connect();
        } catch (final UnknownHostException e) {
            logger.error("Unknown database host.", e);
            System.exit(1);
        }
    }

    private Date getPostCreationDate(final RedditPost post) {
        return new Date(post.getCreated() * 1000);
    }
}
