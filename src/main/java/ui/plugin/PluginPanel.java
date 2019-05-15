package ui.plugin;

import po.Plugin;
import ui.DataRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PluginPanel extends JPanel {

    private JLabel error;

    private JLabel add;

    public PluginPanel() {
        super();
        init();
    }

    private void init() {
        setLayout(new GridLayout(8,1));
        add(new PluginHeader());
        initError();
        initItem();
        initAdd();
    }

    private void initAdd() {
        add = new JLabel("新增插件", SwingConstants.CENTER);
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PluginAddDialog.showDialog(PluginPanel.this);
            }
        });
        add.setForeground(Color.BLUE);
        add(add);
    }

    private void initError() {
        error = new JLabel("获取插件信息失败，点击重试");
        error.setForeground(Color.RED);
        error.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initItem();
            }
        });
    }

    private void initItem() {
        List<Plugin> pluginList = DataRepository.getPluginList();
        if (pluginList == null) {
            error.setVisible(true);
        }else {
            error.setVisible(false);
            for (Plugin plugin : pluginList) {
                add(new PluginItem(plugin));
            }
        }
    }

    public void addItem(Plugin plugin) {
        remove(add);
        add(new PluginItem(plugin));
        add(add);
    }

}
