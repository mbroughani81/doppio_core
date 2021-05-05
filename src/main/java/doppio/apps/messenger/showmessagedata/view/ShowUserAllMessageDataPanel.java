package doppio.apps.messenger.showmessagedata.view;

import doppio.apps.messenger.listener.PrivateChatClickInvoker;
import doppio.apps.messenger.listener.PrivateChatClickListener;
import doppio.apps.messenger.model.GroupChat;
import doppio.apps.messenger.model.PrivateChat;
import doppio.apps.messenger.showmessagedata.Listener.ShowUserAllMessageDataPanelListener;
import doppio.apps.messenger.view.component.ChatListPanel;

import javax.swing.*;
import java.awt.*;

public class ShowUserAllMessageDataPanel extends JPanel implements PrivateChatClickInvoker {

    ShowUserAllMessageDataPanelListener showUserAllMessageDataPanelListener;

    ChatListPanel chatListPanel;

    PrivateChatClickListener privateChatClickListener;

    public ShowUserAllMessageDataPanel(ShowUserAllMessageDataPanelListener showUserAllMessageDataPanelListener) {
        this.showUserAllMessageDataPanelListener = showUserAllMessageDataPanelListener;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.CYAN);
        this.setOpaque(true);

        chatListPanel = new ChatListPanel();
        chatListPanel.setPrivateChatClickListener(new PrivateChatClickListener() {
            @Override
            public void run(int privateChatId) {
//                System.out.println("Yahooo !  " + privateChatId + " showuserallmessagedatapanel const");
                checkPrivateClickListener(privateChatId);
            }
        });
        this.add(chatListPanel, BorderLayout.CENTER);
        for (PrivateChat privateChat : this.showUserAllMessageDataPanelListener.getPrivateChats()) {
            chatListPanel.addPrivateChat(privateChat);
        }
        for (GroupChat groupChat : this.showUserAllMessageDataPanelListener.getGroupChats()) {
            chatListPanel.addGroupChat(groupChat);
        }

    }

    @Override
    public void setPrivateChatClickListener(PrivateChatClickListener listener) {
        this.privateChatClickListener = listener;
    }

    @Override
    public void checkPrivateClickListener(int privateChatId) {
//        if (privateChatClickListener != null) {
//            privateChatClickListener.
//        }
        privateChatClickListener.run(privateChatId);
    }
}
