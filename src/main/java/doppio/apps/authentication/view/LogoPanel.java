package doppio.apps.authentication.view;

import doppio.config.AuthenticationConfig;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    AuthenticationConfig authenticationConfig = new AuthenticationConfig();

    ImageIcon imageIcon;
    JLabel logoLabel;

    public LogoPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(authenticationConfig.getLogoPanelWidth(), authenticationConfig.getLogoPanelHeight()));
        this.setBackground(Color.PINK);
        this.setOpaque(true);

        logoLabel = new JLabel();
        this.add(logoLabel, BorderLayout.CENTER);

        imageIcon = new ImageIcon("src/main/resources/images/logo.png");
        logoLabel.setIcon(imageIcon);
    }
}
