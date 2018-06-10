package connector;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


public class RedditService {

    private static final String url = "https://www.reddit.com";
    private final Client client;

    public RedditService() {
        client = ClientBuilder.newClient().register(new JacksonJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    public PostsResponse getPosts(final String subreddit) {
        final WebTarget target = client.target(mapSubredditToUrl(subreddit));
        return target.request(MediaType.APPLICATION_JSON_TYPE).get(PostsResponse.class);
    }

    private String mapSubredditToUrl(final String subreddit) {
        return String.format("%s%s.json?limit=100", url, subreddit);
    }

}
