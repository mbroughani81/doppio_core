package doppio.apps.browser.view;

import doppio.config.BrowserConfig;
import doppio.listener.StringInvoker;
import doppio.listener.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AppsMenuPanel extends JPanel implements ActionListener, StringInvoker {
    BrowserConfig browserConfig = new BrowserConfig();

    JButton personalPageAppButton;
    JButton timelineAppButton;
    JButton explorerAppButton;
    JButton messengerAppButton;
    JButton settingAppButton;

    LinkedList<StringListener> stringListeners;

    public AppsMenuPanel() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(browserConfig.getAppsMenuPanelWidth(), browserConfig.getAppsMenuPanelHeight()));
        this.setBackground(Color.ORANGE);
        this.setOpaque(true);
        GridBagConstraints gbc = new GridBagConstraints();

        this.personalPageAppButton = new JButton(browserConfig.getPersonalPageAppButtonText());
        this.personalPageAppButton.addActionListener(this);
        this.timelineAppButton = new JButton(browserConfig.getTimelineAppButtonText());
        this.timelineAppButton.addActionListener(this);
        this.explorerAppButton = new JButton(browserConfig.getExplorerAppButtonText());
        this.explorerAppButton.addActionListener(this);
        this.messengerAppButton = new JButton(browserConfig.getMessengerAppButtonText());
        this.messengerAppButton.addActionListener(this);
        this.settingAppButton = new JButton(browserConfig.getSettingAppButtonText());
        this.settingAppButton.addActionListener(this);

        gbc.weightx = 0.1;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(this.personalPageAppButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(this.timelineAppButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(this.explorerAppButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(this.messengerAppButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(this.settingAppButton, gbc);

        stringListeners = new LinkedList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.personalPageAppButton) {
            checkListeners("personalPageAppClicked");
        }
        if (e.getSource() == this.timelineAppButton) {
            checkListeners("timelineAppClicked");
        }
        if (e.getSource() == this.messengerAppButton) {
            checkListeners("messengerAppClicked");
        }
        if (e.getSource() == this.explorerAppButton) {
            checkListeners("explorerAppClicked");
        }
        if (e.getSource() == this.settingAppButton) {
            checkListeners("settingAppClicked");
        }
    }

    @Override
    public void checkListeners(String s) {
        for (StringListener listener : stringListeners)
            listener.run(s);
    }

    @Override
    public void addListener(StringListener listener) {
        stringListeners.add(listener);
    }
}
