package doppio.apps.sociallist.view.eventitemlist;

import doppio.apps.sociallist.view.InboxRequestLabel;
import doppio.apps.sociallist.view.listener.NotificationLabelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class NotificationItemList extends JPanel {

    LinkedList<JLabel> items;

    public NotificationItemList() {
        this.setLayout(new GridBagLayout());

        items = new LinkedList<>();
    }

    public void addSystemNotification(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(300, 50));
        label.setBackground(Color.MAGENTA);
        label.setOpaque(true);
        items.add(label);

        updatePanel();
    }

    public void addInboxRequestNotification(String text, NotificationLabelListener listener) {
        JLabel label = new InboxRequestLabel(text, listener);
        items.add(label);

        updatePanel();
    }

    private void updatePanel() {
        removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        for (JLabel label : items) {
            add(label, gbc);
            gbc.gridy++;
        }
        revalidate();
        repaint();
    }
}
