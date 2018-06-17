package post;

public enum Source
{
    REDDIT(true),
    WYKOP(false);

    public boolean isReddit;

    Source(boolean isReddit)
    {
        this.isReddit = isReddit;
    }
}
