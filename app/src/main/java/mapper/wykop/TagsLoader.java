package mapper.wykop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class TagsLoader
{
    private static final Logger logger = LoggerFactory.getLogger(TagsLoader.class);
    private static final String TAGS_FILE_PATH = "./tags";
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void loadTags() {
        try (Stream<String> stream = Files.lines(Paths.get(TAGS_FILE_PATH))) {

            tags = stream.collect(toImmutableList());

        } catch (final IOException e) {
            logger.error("No file with tags.", e);
            e.printStackTrace();
        }
    }
}
