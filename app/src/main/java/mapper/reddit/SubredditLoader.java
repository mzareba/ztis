package mapper.reddit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class SubredditLoader {
    private static final Logger logger = LoggerFactory.getLogger(SubredditLoader.class);
    private static final String SUBREDDITS_FILE_PATH = "./subreddits";
    private List<String> subreddits;

    public List<String> getSubreddits() {
        return subreddits;
    }

    public void loadSubreddits() {
        try (Stream<String> stream = Files.lines(Paths.get(SUBREDDITS_FILE_PATH))) {

            subreddits = stream.collect(toImmutableList());

        } catch (final IOException e) {
            logger.error("No file with subreddits.", e);
            e.printStackTrace();
        }
    }

}
