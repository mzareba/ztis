package post;

import java.util.Date;
public class PostBuilder
{
    private String id;
    private String topic;
    private Source source;
    private Date date;
    private boolean originalPost;
    private int likes;
    private int responses;

    public PostBuilder()
    {
    }

    public PostBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PostBuilder withTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public PostBuilder withSource(Source source) {
        this.source = source;
        return this;
    }

    public PostBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public PostBuilder withOriginalPost(boolean originalPost) {
        this.originalPost = originalPost;
        return this;
    }

    public PostBuilder withLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public PostBuilder withResponses(int responses) {
        this.responses = responses;
        return this;
    }

    public Post build() {
        return new Post(id, topic, source, date, originalPost, likes, responses);
    }
}
