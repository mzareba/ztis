package connector;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import model.ListingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


public class RedditService {

    private static final Logger logger = LoggerFactory.getLogger(RedditService.class);
    private static final String REDDIT_URL = "https://www.reddit.com/r/";
    private static final String CATEGORY = "new";
    private final Client client;

    public RedditService() {
        client = ClientBuilder.newClient().register(new JacksonJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    public ListingResponse getListing(final String subreddit, final int limit, final String after) {
        final int checkedLimit = limit > 100 ? 100 : limit;
        logger.info("Getting newest {} posts from /{} subreddit.", checkedLimit, subreddit);
        final WebTarget target = client.target(mapSubredditToUrl(subreddit, checkedLimit, after));
        return target.request(MediaType.APPLICATION_JSON_TYPE).get(ListingResponse.class);
    }

    private String mapSubredditToUrl(final String subreddit, final int limit, final String after) {
        final StringBuilder urlBuilder = new StringBuilder()
                .append(REDDIT_URL)
                .append(subreddit)
                .append(String.format("/%s.json", CATEGORY));
        addParametersToUrl(urlBuilder, limit, after);
        return urlBuilder.toString();
    }

    private void addParametersToUrl(final StringBuilder urlBuilder, final int limit, final String after) {
        urlBuilder.append(String.format("?limit=%d", limit));
        if (after != null) {
            urlBuilder.append(String.format("&after=%s", after));
        }
    }

}
