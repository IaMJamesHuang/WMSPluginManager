package ui.login;


import java.awt.*;

import javax.swing.*;


public class Login extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String LOG_TITLE = "插件管理中心";
    public static final int WINDOW_WIDTH = 430;
    public static final int WINDOW_HEIGHT = 330;

    private static Login login;

    public static void dissmiss() {
        if (login != null) {
            login.setVisible(false);
        }
    }

    public static void login() {
        if (login != null) {
            login.setVisible(true);
            return;
        }
        login = new Login();
        login.setTitle(LOG_TITLE);
        login.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        login.setLocationRelativeTo(null);

        login.setUndecorated(true);   //设置frame边框不可见
        login.setResizable(false);    //禁止改变窗口大小

        BorderLayout border_layout = new BorderLayout();
        login.setLayout(border_layout);

        CreatePanel createPanel = new CreatePanel();

        /**
         *
         * 北部面板
         */
        JPanel panel_north = createPanel.CreateNorthPanel();
        login.add(panel_north, BorderLayout.NORTH);

        /**
         * 中部面板
         */
        JPanel panel_center = createPanel.CrateCenterPanel();
        login.add(panel_center, BorderLayout.CENTER);


        /**
         * 南部面板
         */
        JPanel panel_south = createPanel.CreateSouthPanel();
        login.add(panel_south, BorderLayout.SOUTH);


        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);

        createPanel.username.requestFocus();
    }

    public static void main(String[] args) {
        login();
    }

}

