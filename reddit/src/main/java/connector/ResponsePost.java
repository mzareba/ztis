package connector;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import post.IPost;
import java.util.Date;

@JsonDeserialize(builder = ResponsePost.Builder.class)
public class ResponsePost implements IPost
{
    private final String permalink;

    private final String subreddit;

    private final Date created;

    private final int score;

    private final int num_comments;

    private ResponsePost(Builder builder) {
        permalink = builder.permalink;
        subreddit = builder.subreddit;
        created = builder.created;
        score = builder.score;
        num_comments = builder.num_comments;
    }

    @Override
    public DBObject toDbObject()
    {
        DBObject document = new BasicDBObject();
        document.put("_id", permalink);
        document.put("topic", subreddit);
        document.put("date", created);
        document.put("likes", score);
        document.put("responses", num_comments);
        return document;
    }

    public static final class Builder
    {
        private String permalink;
        private String subreddit;
        private Date created;
        private int score;
        private int num_comments;

        private Builder()
        {
        }

        public static Builder aResponsePost()
        {
            return new Builder();
        }

        public Builder withPermalink(String permalink)
        {
            this.permalink = permalink;
            return this;
        }

        public Builder withSubreddit(String subreddit)
        {
            this.subreddit = subreddit;
            return this;
        }

        public Builder withCreated(Long created)
        {
            this.created = new Date(created * 1000);
            return this;
        }

        public Builder withScore(int score)
        {
            this.score = score;
            return this;
        }

        public Builder withNum_comments(int num_comments)
        {
            this.num_comments = num_comments;
            return this;
        }

        public ResponsePost build()
        {
            return new ResponsePost(this);
        }
    }
}
