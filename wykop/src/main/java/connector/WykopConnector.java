package connector;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import db.MongoConnector;
import model.WykopResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class WykopConnector
{

    private static final Logger logger = LoggerFactory.getLogger(WykopConnector.class);
    private static final String BASE_WYKOP_URL = "http://a.wykop.pl/tag/index/";
    private static final String APP_KEY = "";
    private final Client client;

    public WykopConnector() {
        client = ClientBuilder.newClient().register(new JacksonJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
    }

    public WykopResponse getPosts(String topic, int page) {
        final WebTarget target = client.target(prapareUrl(topic, page));
        try {
            return target.request(MediaType.APPLICATION_JSON_TYPE).get(WykopResponse.class);
        } catch (Exception e) {
            return null;
        }

    }

    private String prapareUrl(String topic, int page) {
        final StringBuilder urlBulder = new StringBuilder()
                .append(BASE_WYKOP_URL)
                .append(topic)
                .append("/appkey,")
                .append(APP_KEY)
                .append(",page,")
                .append(page);
        return urlBulder.toString();
    }
}
