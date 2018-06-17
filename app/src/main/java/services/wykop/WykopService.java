package services.wykop;

import api.WykopApi;
import com.google.common.collect.Iterables;
import db.MongoConnector;
import mapper.wykop.WykopPostMapper;
import model.WykopItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import post.Post;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class WykopService
{
    private static final Logger logger = LoggerFactory.getLogger(WykopService.class);
    private final MongoConnector dbConnector;
    private final WykopApi wykopApi;
    private final WykopPostMapper wykopPostMapper;

    public WykopService(MongoConnector dbConnector, WykopApi wykopApi, WykopPostMapper wykopPostMapper) {
        this.dbConnector = dbConnector;
        this.wykopApi = wykopApi;
        this.wykopPostMapper = wykopPostMapper;
    }

    public void getWykopPostsSinceDateAndSave(Date since, String tag) {
        connect();
        Date currentDate = new Date();
        int page = 1;
        while (currentDate.after(since)) {
            List<WykopItem> wykopResponse = wykopApi.getPosts(tag, page);
            if (wykopResponse.isEmpty()) {
                logger.info("Reached limit of visible posts for tag: /{}.", tag);
                break;
            }
            page++;
            final List<Post> mappedResults = wykopResponse.stream()
                            .map(post -> wykopPostMapper.mapToDomainPost(post, tag))
                            .collect(toImmutableList());
            dbConnector.savePosts(mappedResults);
            currentDate = Iterables.getLast(mappedResults).getCreationDate();
        }
    }

    private void connect() {
        try {
            dbConnector.connect();
        } catch (final UnknownHostException e) {
            logger.error("Unknown database host.", e);
            System.exit(1);
        }
    }
}
