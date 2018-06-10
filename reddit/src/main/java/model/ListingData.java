package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = ListingData.Builder.class)
public class ListingData {
    private final int dist;
    private final List<PostResponse> children;
    private final String after;

    private ListingData(final Builder builder) {
        dist = builder.dist;
        children = builder.children;
        after = builder.after;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getDist() {
        return dist;
    }

    public List<PostResponse> getChildren() {
        return children;
    }

    public String getAfter() {
        return after;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ListingData that = (ListingData) o;
        return dist == that.dist &&
                Objects.equals(children, that.children) &&
                Objects.equals(after, that.after);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dist, children, after);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dist", dist)
                .add("children", children)
                .add("after", after)
                .toString();
    }

    public static final class Builder {
        private int dist;
        private List<PostResponse> children;
        private String after;

        private Builder() {
        }

        public Builder withDist(final int dist) {
            this.dist = dist;
            return this;
        }

        public Builder withChildren(final List<PostResponse> children) {
            this.children = children;
            return this;
        }

        public Builder withAfter(final String after) {
            this.after = after;
            return this;
        }

        public ListingData build() {
            return new ListingData(this);
        }
    }
}
