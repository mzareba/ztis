package post;

public enum Source
{
    REDDIT(true),
    TWITTER(false);

    public boolean isReddit;

    Source(boolean isReddit)
    {
        this.isReddit = isReddit;
    }
}
