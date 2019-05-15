package ui.access;

import po.Plugin;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class AccessHeader extends JPanel {

    private static final int COLS = 6;

    private List<Plugin> pluginList;

    public AccessHeader(List<Plugin> pluginList) {
        super();
        this.pluginList = pluginList;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1,COLS));
        initItem();
    }

    private void initItem() {
        removeAll();
        add(new JLabel("", SwingConstants.CENTER));
        for (int i = 0; i < pluginList.size(); i++) {
            add(new JLabel(pluginList.get(i).getNick(), SwingConstants.CENTER));
        }
        for (int i = 0; i < COLS - pluginList.size() - 1; i++) {
            add(new JLabel(""));
        }
    }

}
