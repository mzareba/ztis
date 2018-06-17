package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(builder = WykopResponse.Builder.class)
public class WykopResponse {
    private List<WykopItem> items;

    private WykopResponse(Builder builder) {
        items = builder.items;
    }

    public List<WykopItem> getItems()
    {
        return items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<WykopItem> items;

        private Builder() {
        }

        public Builder withItems(List<WykopItem> items) {
            this.items = items;
            return this;
        }

        public WykopResponse build() {
            return new WykopResponse(this);
        }
    }
}
