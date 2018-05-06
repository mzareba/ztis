package app;

import connector.PostsResponse;
import connector.RedditService;

public class App {

    private final RedditService redditService;

    public App() {
        this.redditService = new RedditService();
    }

    public static void main(String[] args) {
        final App app = new App();
        final PostsResponse response = app.redditService.getPosts("bitcoin");
        System.out.println(response.getKind());
    }
}
