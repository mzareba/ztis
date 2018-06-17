package mapper.wykop;

import model.WykopItem;
import post.Post;

import static post.Source.WYKOP;

public class WykopPostMapper {

    public Post mapToDomainPost(WykopItem wykopItem, String topic) {
        return Post.builder()
                .withId(wykopItem.getId())
                .withTopic(topic)
                .withSource(WYKOP)
                .withOriginalPost(true)
                .withLikes(wykopItem.getVote_count())
                .withResponses(wykopItem.getComment_count())
                .withCreationDate(wykopItem.getDate())
                .build();
    }
}
