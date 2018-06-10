package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;

import java.util.Objects;

@JsonDeserialize(builder = RedditPost.Builder.class)
public class RedditPost {
    private final String subreddit;
    private final String title;
    private final String name; // post id
    private final int score; // probably ups - down
    private final long created;
    @JsonProperty("num_comments")
    private final int numComments;
    private final String permalink;

    private RedditPost(final Builder builder) {
        subreddit = builder.subreddit;
        title = builder.title;
        name = builder.name;
        score = builder.score;
        created = builder.created;
        numComments = builder.numComments;
        permalink = builder.permalink;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public long getCreated() {
        return created;
    }

    public int getNumComments() {
        return numComments;
    }

    public String getPermalink() {
        return permalink;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RedditPost redditPost = (RedditPost) o;
        return Objects.equals(permalink, redditPost.permalink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permalink);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("subreddit", subreddit)
                .add("title", title)
                .add("name", name)
                .add("score", score)
                .add("created", created)
                .add("numComments", numComments)
                .add("permalink", permalink)
                .toString();
    }


    public static final class Builder {
        private String subreddit;
        private String title;
        private String name;
        private int score;
        private long created;
        private int numComments;
        private String permalink;

        private Builder() {
        }

        public Builder withSubreddit(final String subreddit) {
            this.subreddit = subreddit;
            return this;
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withScore(final int score) {
            this.score = score;
            return this;
        }

        public Builder withCreated(final long created) {
            this.created = created;
            return this;
        }

        public Builder withNumComments(final int numComments) {
            this.numComments = numComments;
            return this;
        }

        public Builder withPermalink(final String permalink) {
            this.permalink = permalink;
            return this;
        }

        public RedditPost build() {
            return new RedditPost(this);
        }
    }
}
