package ui.access;

import javax.swing.*;
import java.awt.*;

public class AccessTitle extends JPanel {


    public AccessTitle() {
        super();
        setLayout(new GridLayout(1,1));
        add(new JLabel("用户-插件权限表", SwingConstants.CENTER));
    }

}
