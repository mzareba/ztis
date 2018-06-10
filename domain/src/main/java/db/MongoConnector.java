package db;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import post.Post;

import java.net.UnknownHostException;
import java.util.List;

public class MongoConnector
{
    private static final Logger logger = LoggerFactory.getLogger(MongoConnector.class);
    private static final String DB_NAME = "ztis";
    private static final String COLLECTION_NAME = "posts";
    private static DBCollection collection;

    public void connect() throws UnknownHostException {
        collection = new MongoClient("localhost", 27017)
                        .getDB(DB_NAME)
                        .getCollection(COLLECTION_NAME);
    }

    public void savePosts(final List<Post> posts) {
        logger.info("Saving {} posts to database.", posts.size());
        posts.stream().map(Post::toDBObject).forEach(collection::save);
    }

    public void savePost(Post post) {
        collection.save(post.toDBObject());
    }
}
