package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonDeserialize(builder = WykopItem.Builder.class)
public class WykopItem {
    private final String id;
    private final Date date;
    private final int vote_count;
    private final int comment_count;


    private WykopItem(Builder builder) {
        id = builder.id;
        date = builder.date;
        vote_count = builder.vote_count;
        comment_count = builder.comment_count;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId()
    {
        return id;
    }

    public Date getDate()
    {
        return date;
    }

    public int getVote_count()
    {
        return vote_count;
    }

    public int getComment_count()
    {
        return comment_count;
    }

    public static final class Builder {
        private String id;
        private Date date;
        private int vote_count;
        private int comment_count;

        private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withDate(String date) {
            try
            {
                this.date = format.parse(date);
            }
            catch (ParseException e)
            {
                this.date = new Date();
            }
            return this;
        }

        public Builder withVote_count(int vote_count) {
            this.vote_count = vote_count;
            return this;
        }

        public Builder withComment_count(int comment_count) {
            this.comment_count = comment_count;
            return this;
        }

        public WykopItem build() {
            return new WykopItem(this);
        }
    }
}
