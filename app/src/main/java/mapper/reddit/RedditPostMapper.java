package mapper.reddit;

import model.RedditPost;
import post.Post;

import java.util.Date;

import static post.Source.REDDIT;

public class RedditPostMapper {

    public Post mapToDomainPost(final RedditPost redditPost, final boolean isOriginal) {
        return Post.builder()
                .withId(redditPost.getPermalink())
                .withTopic(redditPost.getTitle())
                .withSource(REDDIT)
                .withLikes(redditPost.getScore())
                .withOriginalPost(isOriginal)
                .withResponses(redditPost.getNumComments())
                .withCreationDate(new Date(redditPost.getCreated() * 1000))
                .build();
    }
}
