package doppio.apps.explorer.tweetlist.view;

import doppio.apps.explorer.tweetlist.listener.TweetClickInvoker;
import doppio.apps.explorer.tweetlist.listener.TweetClickListener;
import doppio.apps.explorer.view.SingleTweetLabel;
import doppio.apps.post.model.Tweet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class TweetListPanel extends JPanel implements TweetClickInvoker {

    LinkedList<Tweet> tweets;

    TweetClickListener tweetClickListener;

    public TweetListPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.MAGENTA);
        this.setOpaque(true);

        tweets = new LinkedList<>();
        tweetClickListener = null;
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        GridBagConstraints gbc = new GridBagConstraints();

        this.removeAll();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (Tweet tweet1 : tweets) {
            TweetClickAction tweetClickAction = new TweetClickAction(tweet1);
            SingleTweetLabel label = new SingleTweetLabel(tweet1);
            label.addMouseListener(tweetClickAction);
            this.add(label, gbc);
            gbc.gridy++;
        }

        this.repaint();
        this.revalidate();
    }

    @Override
    public void setTweetClickListener(TweetClickListener listener) {
        this.tweetClickListener = listener;
    }

    @Override
    public void checkTweetClickListener(int tweetId) {
        tweetClickListener.run(tweetId);
    }

    class TweetClickAction implements MouseListener {

        Tweet tweet;

        public TweetClickAction(Tweet tweet) {
            this.tweet = tweet;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
//        System.out.println("tweet is clicked mouseclocktweetclockaction tweetlistpanel");
//        System.out.println(tweet.getText() + " mouseclocktweetclockaction tweetlistpanel");
            checkTweetClickListener(tweet.getId());
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
}
