package api;

import com.google.common.base.MoreObjects;
import model.RedditPost;

import java.util.List;
import java.util.Objects;

public class RedditResponse {
    private final String nextPageId;
    private final List<RedditPost> posts;

    public RedditResponse(final String nextPageId, final List<RedditPost> posts) {
        this.nextPageId = nextPageId;
        this.posts = posts;
    }

    public String getNextPageId() {
        return nextPageId;
    }

    public List<RedditPost> getPosts() {
        return posts;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RedditResponse that = (RedditResponse) o;
        return Objects.equals(nextPageId, that.nextPageId) &&
                Objects.equals(posts, that.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextPageId, posts);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nextPageId", nextPageId)
                .add("posts", posts)
                .toString();
    }
}
