package connector;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize(builder = PostsResponse.Builder.class)
public class PostsResponse {
    private final String kind;
    private final Object data;

    private PostsResponse(final Builder builder) {
        kind = builder.kind;
        data = builder.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getKind() {
        return kind;
    }

    public Object getData() {
        return data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PostsResponse that = (PostsResponse) o;
        return Objects.equals(kind, that.kind) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, data);
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("kind", kind)
                .add("data", data)
                .toString();
    }

    public static final class Builder {
        private String kind;
        private Object data;

        private Builder() {
        }

        public Builder withKind(final String kind) {
            this.kind = kind;
            return this;
        }

        public Builder withData(final Object data) {
            this.data = data;
            return this;
        }

        public PostsResponse build() {
            return new PostsResponse(this);
        }
    }
}
