import java.util.Date;

public class Post
{
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

    public Post(String topic, Source source, Date date, boolean originalPost, int likes,
                    int responses)
    {
        this.topic = topic;
        this.source = source;
        this.date = date;
        this.originalPost = originalPost;
        this.likes = likes;
        this.responses = responses;
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
