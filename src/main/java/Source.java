public enum Source
{
    REDDIT(true),
    TWITTER(false);

    boolean isReddit;

    Source(boolean isReddit)
    {
        this.isReddit = isReddit;
    }
}
