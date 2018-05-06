package db;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import post.Post;

import java.net.UnknownHostException;

public class MongoConnector
{
    private static DBCollection collection;

    public MongoConnector() throws UnknownHostException
    {
        collection = new MongoClient("localhost", 27017)
                        .getDB("ztis")
                        .getCollection("posts");
    }

    public void insert(Post post) {
        collection.save(post.toDBObject());
    }
}
