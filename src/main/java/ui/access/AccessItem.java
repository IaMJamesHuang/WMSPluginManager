package ui.access;

import po.Plugin;
import po.User;
import ui.DataRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class AccessItem extends JPanel {

    private final static int COLS = 6;

    private User user;
    private List<Plugin> pluginList;
    private List<JCheckBox> checkBoxList;
    private JLabel alter;


    public AccessItem(User user) {
        super();
        this.user = user;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1,COLS));
        initBody();
    }

    private void initBody() {
        pluginList = DataRepository.getPluginList();
        checkBoxList = new ArrayList<>();
        add(new JLabel(user.getNick(), SwingConstants.CENTER));
        for (Plugin plugin : pluginList) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox.setEnabled(false);
            if (DataRepository.hasAccess(user, plugin)) {
                checkBox.setSelected(true);
            }
            add(checkBox);
        }
        for (int i = 0; i < COLS - pluginList.size() - 2; i++) {
            add(new JLabel(""));
        }
        alter = new JLabel("修改");
        alter.setForeground(Color.BLUE);
        add(alter);
    }

}
