package connector;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import post.IPost;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonDeserialize(builder = ResponseData.Builder.class)
public class ResponseData
{
    private final List<ResponseNestedData> children;

    private ResponseData(Builder builder) {
        children = builder.children;
    }

    public List<IPost> getPosts() {
        return children.stream().map(ResponseNestedData::getResponseData).collect(Collectors.toList());
    }

    public static final class Builder {
        private List<ResponseNestedData> children;

        private Builder() {
        }

        public Builder withChildren(List<ResponseNestedData> children) {
            this.children = children;
            return this;
        }

        public ResponseData build() {
            return new ResponseData(this);
        }
    }
}
