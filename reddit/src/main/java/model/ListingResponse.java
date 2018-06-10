package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;

import java.util.Objects;

@JsonDeserialize(builder = ListingResponse.Builder.class)
public class ListingResponse {
    private final String kind;
    private final ListingData data;

    private ListingResponse(final Builder builder) {
        kind = builder.kind;
        data = builder.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getKind() {
        return kind;
    }

    public ListingData getData() {
        return data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ListingResponse that = (ListingResponse) o;
        return Objects.equals(kind, that.kind) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, data);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("kind", kind)
                .add("data", data)
                .toString();
    }

    public static final class Builder {
        private String kind;
        private ListingData data;

        private Builder() {
        }

        public Builder withKind(final String kind) {
            this.kind = kind;
            return this;
        }

        public Builder withData(final ListingData data) {
            this.data = data;
            return this;
        }

        public ListingResponse build() {
            return new ListingResponse(this);
        }
    }
}
