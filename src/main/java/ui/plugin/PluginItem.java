package ui.plugin;

import dto.UploadPluginDto;
import net.RequestManagement;
import po.Plugin;
import ui.FileChooseUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PluginItem extends JPanel{

    private Plugin plugin;

    private JLabel nick;

    private JLabel name;

    private JLabel version;

    private JLabel time;




    public PluginItem(Plugin plugin) {
        super();
        this.plugin = plugin;
        init();
    }


    private void init() {
        setLayout(new GridLayout(1,5));
        nick = new JLabel(plugin.getNick(), SwingConstants.CENTER);
        name = new JLabel(plugin.getName(), SwingConstants.CENTER);
        version = new JLabel(plugin.getVersion(), SwingConstants.CENTER);
        time = new JLabel(String.valueOf(plugin.getTime()), SwingConstants.CENTER);
        add(nick);
        add(name);
        add(version);
        add(time);
        JLabel upload = new JLabel("上传", SwingConstants.CENTER);
        upload.setForeground(Color.BLUE);
        upload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File file = FileChooseUtil.chooseFile(PluginItem.this);
                if (file != null) {
                    UploadPluginDto dto = RequestManagement.uploadPlugin(file, plugin.getName(), plugin.getVersion(), plugin.getNick());
                    if (dto != null && dto.getResult() != null) {
                        plugin = dto.getResult();
                        time.setText(String.valueOf(plugin.getTime()));
                    }
                }
            }
        });
        add(upload);
    }
}
