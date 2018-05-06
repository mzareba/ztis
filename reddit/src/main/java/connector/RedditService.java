package connector;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


public class RedditService {

    private static final String url = "https://www.reddit.com/r/";
    private final Client client;

    public RedditService() {
        client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
    }

    public PostsResponse getPosts(final String subreddit) {
        final WebTarget target = client.target(mapSubredditToUrl(subreddit));
        return target.request(MediaType.APPLICATION_JSON_TYPE).get(PostsResponse.class);
    }

    private String mapSubredditToUrl(final String subreddit) {
        return String.format("%s%s.json", url, subreddit);
    }

}
