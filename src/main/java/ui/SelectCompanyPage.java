package ui;

import po.Company;
import ui.login.Login;
import ui.main.MainPage;
import ui.main.WindowParam;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class SelectCompanyPage extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList<String> list;

    private List<Company> mData;

    public SelectCompanyPage(List<Company> data) {
        mData = data;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //initList
        list.setListData(getStringArrFromCompany(mData));
    }

    public static String[] getStringArrFromCompany(List<Company> companies) {
        String[] result = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            result[i] = companies.get(i).getName();
        }
        return result;
    }

    private void onOK() {
        // add your code here
        int index = list.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "系统提示", "请选择公司", JOptionPane.ERROR_MESSAGE);
        } else {
            Company company = mData.get(index);
            //跳到下个页面
            DataRepository.setCompany(company);
            dispose();
            MainPage.showMainPage();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        Login.login();
        dispose();
    }

    public static void showPage(List<Company> data) {
        Login.dissmiss();
        SelectCompanyPage dialog = new SelectCompanyPage(data);
        dialog.setTitle("选择公司");
        dialog.setSize(WindowParam.WINDOW_WIDTH, WindowParam.WINDOW_HEIGHT);
        dialog.setLocationRelativeTo(null);

//        dialog.setUndecorated(true);   //设置frame边框不可见
        dialog.setResizable(false);    //禁止改变窗口大小
        dialog.setVisible(true);
    }

}
