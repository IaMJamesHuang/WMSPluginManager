package ui.access;

import po.User;
import ui.DataRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AccessPanel extends JPanel {

    private List<User> userList;

    public AccessPanel() {
        super();
        init();
    }

    private void init() {
        setLayout(new GridLayout(8,1));
        initHeader();
        initBody();
    }

    private void initHeader() {
        add(new AccessTitle());
        add(new AccessHeader(DataRepository.getPluginList()));
    }

    private void initBody() {
        userList = DataRepository.getUserList();
        for (User user : userList) {
            add(new AccessItem(user));
        }
    }

}
