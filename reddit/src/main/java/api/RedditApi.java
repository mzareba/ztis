package api;

import connector.RedditConnector;
import model.ListingResponse;
import model.PostResponse;
import model.RedditPost;

import java.util.List;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class RedditApi {
    public static final int MAX_LIMIT = 100;
    private final RedditConnector redditConnector;

    public RedditApi(final RedditConnector redditConnector) {
        this.redditConnector = redditConnector;
    }

    public RedditResponse getPostsWithNextPageId(final String subreddit, final int limit, final String after) {
        final ListingResponse listingResponse = redditConnector.getListing(subreddit, limit, after);
        final List<RedditPost> posts = listingResponse.getData().getChildren().stream().map(PostResponse::getData).collect(toImmutableList());
        return new RedditResponse(listingResponse.getData().getAfter(), posts);
    }
}
