package doppio.apps.explorer.explorerpanel.listener;

import doppio.apps.authentication.model.User;
import doppio.apps.post.controller.PostController;
import doppio.apps.post.model.Tweet;

import java.util.LinkedList;

public class ExplorerPanelListener  {
    PostController postController = new PostController();

    public LinkedList<Tweet> getTweets() {
        return postController.getPublicTweet();
    }

    public Tweet getSourceTweet(Tweet tweet) {
        if (tweet.getSourceId() == -1)
            return null;
        return postController.getTweet(tweet.getSourceId());
    }
}
