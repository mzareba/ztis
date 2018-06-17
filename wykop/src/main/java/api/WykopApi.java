package api;

import connector.WykopConnector;
import model.WykopItem;
import model.WykopResponse;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class WykopApi
{
    private final WykopConnector wykopConnector;

    public WykopApi(WykopConnector wykopConnector) {
        this.wykopConnector = wykopConnector;
    }

    public List<WykopItem> getPosts(String topic, int page) {
        WykopResponse response = wykopConnector.getPosts(topic,page);
        if (response == null) {
            return new LinkedList<WykopItem>();
        }
        return response.getItems();
    }
}
