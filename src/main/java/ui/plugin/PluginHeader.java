package ui.plugin;

import javax.swing.*;
import java.awt.*;

public class PluginHeader extends JPanel {

    public PluginHeader() {
        super();
        init();
    }


    private void init() {
        setLayout(new GridLayout(1,5));
        add(new JLabel("插件名称", SwingConstants.CENTER));
        add(new JLabel("插件代号", SwingConstants.CENTER));
        add(new JLabel("插件版本", SwingConstants.CENTER));
        add(new JLabel("时间戳", SwingConstants.CENTER));
        add(new JLabel("操作", SwingConstants.CENTER));
    }

}
