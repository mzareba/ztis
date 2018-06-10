package api;

import connector.RedditService;
import model.ListingResponse;
import model.PostResponse;
import model.RedditPost;

import java.util.List;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class RedditApi {
    private final RedditService redditService;

    public RedditApi(final RedditService redditService) {
        this.redditService = redditService;
    }

    public List<RedditPost> getNewPosts(final String subreddit, final int limit, final String after) {
        final ListingResponse listingResponse = redditService.getListing(subreddit, limit, after);
        return listingResponse.getData().getChildren()
                .stream()
                .map(PostResponse::getData)
                .collect(toImmutableList());
    }
}
