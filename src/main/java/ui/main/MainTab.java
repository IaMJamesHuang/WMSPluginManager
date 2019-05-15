package ui.main;

import ui.plugin.PluginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainTab extends JPanel {

    public MainTab() {
        super(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent panel1 = makeTextPanel("权限管理");
        tabbedPane.addTab("权限管理", null, panel1, "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        JComponent panel2 = makePluginPanel();
        tabbedPane.addTab("插件管理", null, panel2, "Does nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        add(tabbedPane);
    }

    private JComponent makePluginPanel() {
        return new PluginPanel();
    }

    private JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }


}
