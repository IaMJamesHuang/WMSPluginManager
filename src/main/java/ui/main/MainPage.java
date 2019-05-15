package ui.main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static ui.main.WindowParam.MAIN_WINDOW_HEIGHT;
import static ui.main.WindowParam.MAIN_WINDOW_WIDTH;

public class MainPage extends JFrame{


    public MainPage() {
        super();
        this.setSize(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);    //禁止改变窗口大小
        this.setTitle("仓储管理平台插件管理系统");

        /*
         * 监听窗体关闭事件
         */
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }});

        this.add(new MainTab(), BorderLayout.CENTER);

    }

    public static void showMainPage() {
        MainPage mainPage = new MainPage();
        mainPage.setVisible(true);
    }

    public static void main(String[] args) {
        showMainPage();
    }

}
