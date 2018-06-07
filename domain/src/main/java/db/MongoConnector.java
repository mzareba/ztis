package db;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import post.IPost;
import post.Source;

import java.net.UnknownHostException;
import java.util.List;

public class MongoConnector
{
    private static DBCollection collection;

    public MongoConnector() throws UnknownHostException
    {
        collection = new MongoClient("localhost", 27017)
                        .getDB("ztis")
                        .getCollection("posts");
    }

    public void insert (IPost post, Source source, boolean isOriginalPost) {
        DBObject document = post.toDbObject();
        document.put("isReddit", source.isReddit);
        document.put("originalPost", isOriginalPost);
        collection.save(document);
    }
}
