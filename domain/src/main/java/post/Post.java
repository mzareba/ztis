package post;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;
import java.util.Objects;

public class Post {
    private String id;
    private String topic;
    private Source source;
    private Date creationDate;
    private boolean originalPost;
    private int likes;
    private int responses;

    private Post(final Builder builder) {
        id = builder.id;
        topic = builder.topic;
        source = builder.source;
        creationDate = builder.creationDate;
        originalPost = builder.originalPost;
        likes = builder.likes;
        responses = builder.responses;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public Source getSource() {
        return source;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isOriginalPost() {
        return originalPost;
    }

    public int getLikes() {
        return likes;
    }

    public int getResponses() {
        return responses;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("topic", topic)
                .add("source", source)
                .add("creationDate", creationDate)
                .add("originalPost", originalPost)
                .add("likes", likes)
                .add("responses", responses)
                .toString();
    }

    public DBObject toDBObject() {
        DBObject document = new BasicDBObject();
        document.put("_id", id);
        document.put("topic", topic);
        document.put("isReddit", source.isReddit);
        document.put("creationDate", creationDate);
        document.put("originalPost", originalPost);
        document.put("likes", likes);
        document.put("responses", responses);
        return document;
    }

    public static final class Builder {
        private String id;
        private String topic;
        private Source source;
        private Date creationDate;
        private boolean originalPost;
        private int likes;
        private int responses;

        private Builder() {
        }

        public Builder withId(final String id) {
            this.id = id;
            return this;
        }

        public Builder withTopic(final String topic) {
            this.topic = topic;
            return this;
        }

        public Builder withSource(final Source source) {
            this.source = source;
            return this;
        }

        public Builder withCreationDate(final Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder withOriginalPost(final boolean originalPost) {
            this.originalPost = originalPost;
            return this;
        }

        public Builder withLikes(final int likes) {
            this.likes = likes;
            return this;
        }

        public Builder withResponses(final int responses) {
            this.responses = responses;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}