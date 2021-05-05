package doppio.apps.browser.listener;

import doppio.apps.browser.view.MainPanel;
import doppio.apps.messenger.view.MessengerPanel;
import doppio.apps.personalpage.view.PersonalPagePanel;
import doppio.listener.StringListener;

import java.awt.*;

public class MainPanelToAppsMenuPanelListener implements StringListener {

    MainPanel mainPanel;

    public MainPanelToAppsMenuPanelListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void run(String s) {
//        System.out.println("run in MainPanelToAppsMenuPanelListener");
        if (s.equals("personalPageAppClicked")) {
            PersonalPagePanel personalPagePanel = new PersonalPagePanel();
            personalPagePanel.addListener(new MainPanelToPersonalPagePanelListener(mainPanel));
            BorderLayout layout = (BorderLayout) mainPanel.getLayout();
            mainPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
            mainPanel.add(personalPagePanel, BorderLayout.CENTER);
            mainPanel.repaint();
            mainPanel.revalidate();
        }
        if (s.equals("messengerAppClicked")) {
            MessengerPanel messengerPanel = new MessengerPanel();
//            messengerPanel.se
            messengerPanel.setPrivateChatClickListener(new MainPanelToMessengerPanelListener(mainPanel));
            BorderLayout layout = (BorderLayout) mainPanel.getLayout();
            mainPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
            mainPanel.add(messengerPanel, BorderLayout.CENTER);
            mainPanel.repaint();
            mainPanel.revalidate();
        }
    }
}
