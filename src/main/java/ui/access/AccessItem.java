package ui.access;

import net.RequestManagement;
import po.Plugin;
import po.UploadAccessBean;
import po.User;
import po.UserAccessBean;
import ui.DataRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
            checkBoxList.add(checkBox);
            add(checkBox);
        }
        for (int i = 0; i < COLS - pluginList.size() - 2; i++) {
            add(new JLabel(""));
        }
        alter = new JLabel("修改");
        alter.setForeground(Color.BLUE);
        alter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ("修改".equals(alter.getText())) {
                    alter.setText("保存");
                    alter.setForeground(Color.RED);
                    enableAllCheckBox(true);
                } else {
                    alterUserAccess();
                    alter.setText("修改");
                    alter.setForeground(Color.BLUE);
                    enableAllCheckBox(false);
                }
            }
        });
        add(alter);
    }

    private void alterUserAccess() {
        List<UserAccessBean> list = new ArrayList<>();
        for (int i = 0; i < pluginList.size(); i++) {
            UserAccessBean bean = new UserAccessBean();
            bean.setUser_id(user.getId());
            bean.setPlugin_id(pluginList.get(i).getId());
            bean.setAuthority(checkBoxList.get(i).isSelected() ? 1 : 0);
            bean.setNick(user.getNick());
            bean.setName(pluginList.get(i).getName());
            list.add(bean);
        }
        UploadAccessBean uploadAccessBean = new UploadAccessBean();
        uploadAccessBean.setResult(list);
        RequestManagement.uploadAccess(uploadAccessBean);
    }

    private void enableAllCheckBox(boolean flag) {
        for (JCheckBox checkBox : checkBoxList) {
            checkBox.setEnabled(flag);
        }
    }

}
