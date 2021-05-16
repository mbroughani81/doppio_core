package doppio.apps.sociallist.controller;

import doppio.apps.authentication.model.User;
import doppio.apps.sociallist.model.*;
import doppio.controller.AbstractController;
import doppio.event.AddToBlockedEvent;
import doppio.event.AddToFollowerEvent;
import doppio.event.NewFollowRequestEvent;
import doppio.event.NewSystemNotificationEvent;

import java.util.LinkedList;

public class SocialListController extends AbstractController {
    public void addToBlocked(AddToBlockedEvent event) {
        BlockList blockList = context.Blocklists.get(event.getBlocker().getBlockListId());
        blockList.getList().add(event.getBlocked().getId());
        context.Blocklists.update(blockList);
    }

    public void addToFollower(AddToFollowerEvent event) {
        FollowerList followerList = context.FollowerLists.get(event.getFollowd().getFollowersListId());
        followerList.getList().add(event.getFollower().getId());
        FollowingList followingList = context.FollowingLists.get(event.getFollower().getFollowingListId());
        followingList.getList().add(event.getFollowd().getId());
        context.FollowingLists.update(followingList);
        context.FollowerLists.update(followerList);
    }

    public void sentRequest(NewFollowRequestEvent event) {
        FollowRequestNotification request = new FollowRequestNotification(event.getFollower().getId(), event.getFollowed().getId());
        int id = context.FollowRequests.add(request);
        request.setId(id);
        int notificationBoxFollowedId = event.getFollowed().getNotificationBoxId();
        NotificationBox n1 = context.NotificationBoxes.get(notificationBoxFollowedId);
        n1.getFollowRequestIds().add(request.getId());
        context.NotificationBoxes.update(n1);
        int notificationBoxFollowerId = event.getFollower().getNotificationBoxId();
        NotificationBox n2 = context.NotificationBoxes.get(notificationBoxFollowerId);
        n2.getFollowRequestIds().add(request.getId());
        context.NotificationBoxes.update(n2);
    }

    public LinkedList<FollowRequestNotification> getUserRequests(int userId) {
        LinkedList<FollowRequestNotification> res = new LinkedList<>();
        for (FollowRequestNotification followRequestNotification : context.FollowRequests.all()) {
            if (followRequestNotification.getFollowerId() == userId) {
                res.add(followRequestNotification);
            }
        }
        return res;
    }

    public LinkedList<FollowRequestNotification> getUserRequestInbox(int userId) {
        LinkedList<FollowRequestNotification> res = new LinkedList<>();
        for (FollowRequestNotification followRequestNotification : context.FollowRequests.all()) {
            if (followRequestNotification.getFollowingId() == userId) {
                res.add(followRequestNotification);
            }
        }
        return res;
    }

    public LinkedList<SystemNotification> getUserSystemNotifications(int userId) {
        LinkedList<SystemNotification> res = new LinkedList<>();
        for (SystemNotification systemNotification : context.SystemNotifications.all()) {
            if (systemNotification.getUserId() == userId)
                res.add(systemNotification);
        }
        return res;
    }

    public void addSystemNotification(NewSystemNotificationEvent event) {
        SystemNotification notification = new SystemNotification(event.getUserId(), event.getText());
        context.SystemNotifications.add(notification);
    }

    public FollowingList getFollowingList(User user) {
        return context.FollowingLists.get(user.getFollowingListId());
    }

    public FollowerList getFollowersList(User user) {
        return context.FollowerLists.get(user.getFollowersListId());
    }

    public BlockList getBlocked(User user) {
        return context.Blocklists.get(user.getBlockListId());
    }

    public void clearBlackListDB() {
        context.Blocklists.clear();
    }
    public void clearFollowingListDB() {
        context.FollowingLists.clear();
    }
    public void clearFollowerListDB() {
        context.FollowerLists.clear();
    }
    public void clearNotificationBoxDB() {
        context.NotificationBoxes.clear();
    }
    public void clearFollowRequestDB() {
        context.FollowRequests.clear();
    }

}
