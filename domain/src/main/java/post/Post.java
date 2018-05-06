package post;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;

public class Post
{
    // post identifier - url
    private String id;

    // hashtag or subreddit
    private String topic;

    // is reddit or twitter post
    private Source source;

    private Date date;

    // is original post or a response/retweet
    private boolean originalPost;

    // amount of likes
    private int likes;

    // amount of responses
    private int responses;

    public Post()
    {
    }

    public Post(String id, String topic, Source source, Date date, boolean originalPost, int likes,
                    int responses)
    {
        this.id = id;
        this.topic = topic;
        this.source = source;
        this.date = date;
        this.originalPost = originalPost;
        this.likes = likes;
        this.responses = responses;
    }

    public DBObject toDBObject() {
        DBObject document = new BasicDBObject();
        document.put("_id", id);
        document.put("topic", topic);
        document.put("isReddit", source.isReddit);
        document.put("date", date);
        document.put("originalPost", originalPost);
        document.put("likes", likes);
        document.put("responses", responses);
        return document;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public Source getSource()
    {
        return source;
    }

    public void setSource(Source source)
    {
        this.source = source;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public boolean isOriginalPost()
    {
        return originalPost;
    }

    public void setOriginalPost(boolean originalPost)
    {
        this.originalPost = originalPost;
    }

    public int getLikes()
    {
        return likes;
    }

    public void setLikes(int likes)
    {
        this.likes = likes;
    }

    public int getResponses()
    {
        return responses;
    }

    public void setResponses(int responses)
    {
        this.responses = responses;
    }
}
