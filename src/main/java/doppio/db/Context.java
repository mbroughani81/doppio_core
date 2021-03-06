package doppio.db;

import doppio.apps.authentication.model.Profile;
import doppio.apps.authentication.model.User;
import doppio.apps.messenger.model.*;
import doppio.apps.post.model.Tweet;
import doppio.apps.sociallist.model.*;
import doppio.apps.timeline.model.LikedTweetList;
import doppio.apps.timeline.model.MutedUserList;
import doppio.apps.timeline.model.ReportedTweetList;
import doppio.model.Session;

public class Context {

    /*
    * new model instantiates show have id = -1
    * */
    public DBSet<Tweet> Tweets = new TweetDB();
    public DBSet<User> Users = new UserDB();
    public DBSet<Profile> Profiles = new ProfileDB();
    public DBSet<BlockList> Blocklists = new BlockListDB();
    public DBSet<FollowingList> FollowingLists = new FollowingListDB();
    public DBSet<FollowerList> FollowerLists = new FollowerListDB();
    public DBSet<MessageData> MessageDatas = new MessageDataDB();
    public DBSet<Chat> Chats = new ChatDB();
    public DBSet<Session> Sessions = new SessionDB();
    public DBSet<Pm> Pms = new PmDB();
    public DBSet<FollowRequestNotification> FollowRequests = new FollowRequestDB();
    public DBSet<NotificationBox> NotificationBoxes = new NotificationBoxDB();
    public DBSet<UserType> UserTypes = new UserTypeDB();
    public DBSet<SystemNotification> SystemNotifications = new SystemNotificationDB();
    public DBSet<LikedTweetList> LikedTweetLists = new LikedTweetListDB();
    public DBSet<ReportedTweetList> ReportedTweetLists = new ReportedTweetListDB();
    public DBSet<MutedUserList> MutedUserLists = new MutedUserListDB();
}
