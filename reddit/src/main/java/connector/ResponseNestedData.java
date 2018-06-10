package connector;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import post.IPost;

@JsonDeserialize(builder = ResponseNestedData.Builder.class)
public class ResponseNestedData
{
    private final ResponsePost data;

    private ResponseNestedData(final ResponseNestedData.Builder builder) {
        data = builder.data;
    }

    public IPost getResponseData() {
        return data;
    }

    public static final class Builder {
        private String kind;
        private ResponsePost data;

        private Builder() {
        }

        public ResponseNestedData.Builder withKind(final String kind) {
            this.kind = kind;
            return this;
        }

        public ResponseNestedData.Builder withData(final ResponsePost data) {
            this.data = data;
            return this;
        }

        public ResponseNestedData build() {
            return new ResponseNestedData(this);
        }
    }
}
